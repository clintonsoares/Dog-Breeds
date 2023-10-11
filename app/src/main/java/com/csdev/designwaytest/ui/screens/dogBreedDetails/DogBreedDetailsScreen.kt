package com.csdev.designwaytest.ui.screens.dogBreedDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.csdev.designwaytest.R
import com.csdev.designwaytest.domain.model.DogBreedDetails
import com.csdev.designwaytest.ui.BaseComposeScreen
import com.csdev.designwaytest.ui.common.ToastAlerts
import com.csdev.designwaytest.ui.theme.ColorBlack80F
import com.csdev.designwaytest.ui.theme.Pink40
import com.csdev.designwaytest.ui.theme.Purple40
import com.csdev.designwaytest.ui.theme.PurpleGrey40
import com.csdev.designwaytest.utils.Utilities

@Composable
fun DogBreedDetailsScreen(
    breedReferenceId: String,
    navigateBack: () -> Unit,
    viewModel: DogBreedDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var loaderMessage by remember { mutableStateOf<String?>(null) }
    var dogBreedDetails by remember { mutableStateOf<DogBreedDetails?>(null) }
    var showApiError by remember { mutableStateOf(false) }
    var apiErrorMessage by remember { mutableStateOf("") }
    var showEnlargedImage by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel) {
        if (Utilities.isInternetAvailable(context)) {
            viewModel.getDogBreedDetails(breedReferenceId)
        }
    }
    viewModel.isLoading.observeAsState().value?.let { isLoading = it }
    viewModel.loadingMessage.collectAsStateWithLifecycle().value?.let { loaderMessage = it }
    viewModel.errorMessage.collectAsStateWithLifecycle().value?.let {
        showApiError = true
        apiErrorMessage = it
    }
    viewModel.dogBreedDetails.collectAsStateWithLifecycle().value.let {
        dogBreedDetails = it
    }

    BaseComposeScreen(isLoading = isLoading, loaderMessage = loaderMessage) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(top = 16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // back button
                IconButton(onClick = navigateBack) {
                    Icon(
                        Icons.Rounded.ArrowBackIos,
                        "back arrow"
                    )
                }
                Text(
                    text = stringResource(id = R.string.home),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.offset(y = (-2).dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Dog breed details
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                dogBreedDetails?.let { breedDetails ->
                    Card(
                        modifier = Modifier
                            .padding(top = 60.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            // name row
                            breedDetails.name?.let {
                                BreedDetailRow(
                                    keyString = stringResource(id = R.string.name) + ":",
                                    valueString = it
                                )
                            }

                            // group row
                            breedDetails.breedGroup?.let {
                                BreedDetailRow(
                                    keyString = stringResource(id = R.string.group) + ":",
                                    valueString = it
                                )
                            }

                            // origin row
                            breedDetails.origin?.let {
                                BreedDetailRow(
                                    keyString = stringResource(id = R.string.origin) + ":",
                                    valueString = it
                                )
                            }

                            // lifeSpan row
                            breedDetails.lifeSpan?.let {
                                BreedDetailRow(
                                    keyString = stringResource(id = R.string.lifespan) + ":",
                                    valueString = it
                                )
                            }

                            // bredFor row
                            breedDetails.bredFor?.let {
                                BreedDetailRow(
                                    keyString = stringResource(id = R.string.bred_for) + ":",
                                    valueString = it
                                )
                            }

                            // temperament row
                            breedDetails.temperament?.let {
                                BreedDetailRow(
                                    keyString = stringResource(id = R.string.temperament) + ":",
                                    valueString = it
                                )
                            }

                            // weight data
                            breedDetails.weight?.let {
                                BreedDimensionRow(
                                    dimensionString = stringResource(id = R.string.weight),
                                    imperialValue = it.imperial ?: "-",
                                    metricValue = it.metric ?: "-"
                                )
                            }

                            // height data
                            breedDetails.height?.let {
                                BreedDimensionRow(
                                    dimensionString = stringResource(id = R.string.height),
                                    imperialValue = it.imperial ?: "-",
                                    metricValue = it.metric ?: "-"
                                )
                            }

                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .size(120.dp)
                            .clip(CircleShape)
                            .clickable {
                                showEnlargedImage = true
                            }
                            .shadow(8.dp)
                            .background(
                                PurpleGrey40
                            )
                            .align(Alignment.TopEnd)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(breedDetails.imageUrl)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.ic_img_placeholder),
                            error = painterResource(R.drawable.ic_img_placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                } ?: run {
                    Card(
                        modifier = Modifier
                            .padding(top = 60.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.error_fetching_data)
                            )
                            Text(
                                text = stringResource(id = R.string.refresh_and_try_again)
                            )
                            IconButton(
                                onClick = {
                                    if (Utilities.isInternetAvailable(context)) {
                                        viewModel.getDogBreedDetails(breedReferenceId)
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.Rounded.Refresh,
                                    "re-call api",
                                    tint = Purple40
                                )
                            }
                        }
                    }
                }

            }

        }
        if (showEnlargedImage) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ColorBlack80F)
                        .padding(8.dp)
                ) {
                    // close button
                    IconButton(onClick = { showEnlargedImage = false }) {
                        Icon(
                            Icons.Rounded.ArrowBackIos,
                            "close image arrow",
                            tint = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = 40.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(dogBreedDetails?.imageUrl)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.ic_img_placeholder),
                            error = painterResource(R.drawable.ic_img_placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
        if (showApiError) {
            ToastAlerts.Error(message = apiErrorMessage, onDismiss = {
                showApiError = false
            })
        }
    }

}

@Composable
private fun BreedDetailRow(
    keyString: String,
    valueString: String,
    modifier: Modifier = Modifier
) {
    // name row
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = keyString,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = valueString,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun BreedDimensionRow(
    dimensionString: String,
    imperialValue: String,
    metricValue: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 1.dp,
            color = Pink40
        )
        Text(
            text = dimensionString,
            style = MaterialTheme.typography.titleMedium,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.imperial),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.metric),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = imperialValue,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = metricValue,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}