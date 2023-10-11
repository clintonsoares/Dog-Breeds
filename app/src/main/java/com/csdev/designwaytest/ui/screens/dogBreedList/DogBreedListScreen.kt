package com.csdev.designwaytest.ui.screens.dogBreedList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csdev.designwaytest.R
import com.csdev.designwaytest.domain.model.DogBreedListItem
import com.csdev.designwaytest.ui.BaseComposeScreen
import com.csdev.designwaytest.ui.common.ToastAlerts
import com.csdev.designwaytest.ui.theme.Pink40
import com.csdev.designwaytest.ui.theme.Purple40
import com.csdev.designwaytest.ui.theme.PurpleGrey40
import com.csdev.designwaytest.utils.Utilities

@Composable
fun DogBreedListScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToDogBreedDetailsScreen: (String) -> Unit,
    viewModel: DogBreedListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var loaderMessage by remember { mutableStateOf<String?>(null) }
    var dogBreeds by remember { mutableStateOf(listOf<DogBreedListItem>()) }
    var showApiError by remember { mutableStateOf(false) }
    var apiErrorMessage by remember { mutableStateOf("") }

    // Launched effect runs only once as viewModel is initiated once on composable creation
    LaunchedEffect(viewModel) {
        if (Utilities.isInternetAvailable(context)) {
            viewModel.getDogBreeds()
        }
    }

    viewModel.isLoading.observeAsState().value?.let { isLoading = it }
    viewModel.loadingMessage.collectAsStateWithLifecycle().value?.let { loaderMessage = it }
    viewModel.dogBreeds.collectAsStateWithLifecycle().value.let {
        dogBreeds = it
    }

    viewModel.errorMessage.collectAsStateWithLifecycle().value?.let {
        showApiError = true
        apiErrorMessage = it
    }

    BaseComposeScreen(isLoading = isLoading, loaderMessage = loaderMessage) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Purple40
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                // Header
                Text(
                    text = stringResource(id = R.string.dog_breeds).uppercase(),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(vertical = 40.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            if (Utilities.isInternetAvailable(context)) {
                                viewModel.getDogBreeds()
                            }
                        }
                )
            }

            if (dogBreeds.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {

                    // dog breeds in a list
                    items(dogBreeds, itemContent = { item ->

                        // ui for items in the list
                        DogBreedCard(item = item) {
                            navigateToDogBreedDetailsScreen(item.reference_image_id)
                        }

                    })

                }
            } else {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // if no items in list
                    Text(
                        text = stringResource(id = R.string.no_data_found),
                        style = MaterialTheme.typography.titleMedium,
                        color = PurpleGrey40
                    )
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
fun DogBreedCard(
    item: DogBreedListItem,
    onClick: () -> Unit
) {
    // dog breed item card -> onclick: opens details page
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick() }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // dog breed name
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                // read more text and icon
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.read_more),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Pink40
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Rounded.KeyboardDoubleArrowRight,
                        contentDescription = "read more",
                        tint = Pink40
                    )
                }
            }
        }
    }

}