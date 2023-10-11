package com.csdev.designwaytest.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csdev.designwaytest.R
import com.csdev.designwaytest.ui.BaseComposeScreen
import com.csdev.designwaytest.ui.common.CustomPasswordTextField
import com.csdev.designwaytest.ui.common.CustomTextField
import com.csdev.designwaytest.ui.common.ToastAlerts
import com.csdev.designwaytest.ui.theme.ColorTextError
import com.csdev.designwaytest.ui.theme.ColorTextField
import com.csdev.designwaytest.ui.theme.Purple40
import com.csdev.designwaytest.ui.theme.Purple40Disabled

@Composable
fun LoginScreen(
    navigateToRegisterScreen: () -> Unit,
    navigateToDogBreedListScreen: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    var username by remember { mutableStateOf("") }
    var isUsernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var loaderMessage by remember { mutableStateOf<String?>(null) }
    var showLoginError by remember { mutableStateOf(false) }
    var loginErrorMessage by remember { mutableStateOf("") }

    LaunchedEffect(viewModel) {
        viewModel.checkUserLoggedIn()
    }
    viewModel.isLoading.observeAsState().value?.let { isLoading = it }
    viewModel.loadingMessage.collectAsStateWithLifecycle().value?.let { loaderMessage = it }
    viewModel.errorMessage.collectAsStateWithLifecycle().value?.let {
        showLoginError = true
        password = ""
        loginErrorMessage = it
    }
    viewModel.isUserLoggedIn.collectAsStateWithLifecycle().value.let { isLoggedIn ->
        if (isLoggedIn) navigateToDogBreedListScreen()
    }
    viewModel.isLoginSuccessful.collectAsStateWithLifecycle().value.let { isSuccess ->
        if (isSuccess) navigateToDogBreedListScreen()
    }

    BaseComposeScreen(isLoading = isLoading, loaderMessage = loaderMessage) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-40).dp)
        ) {

            Card(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
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
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header
                    Text(
                        text = stringResource(id = R.string.enter_login_details),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )

                    // text fields

                    // username
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    ) {
                        NormalTextField(
                            hint = stringResource(id = R.string.please_enter_username),
                            text = username,
                            onTextChanged = {
                                isUsernameError = false
                                username = it.trim()
                            },
                            isError = isUsernameError
                        )
                        // Error text
                        if (isUsernameError){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.please_enter_valid_username),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = ColorTextError
                                )
                            }
                        }
                    }

                    // password
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    ) {
                        PasswordTextField(
                            hint = stringResource(R.string.please_enter_password),
                            text = password,
                            isVisible = isPasswordVisible,
                            isError = isPasswordError,
                            onTextChanged = {
                                isPasswordError = false
                                password = it
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            onVisibilityChanged = { isPasswordVisible = it }
                        )
                        // Error text
                        if (isPasswordError){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.please_enter_valid_password),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = ColorTextError
                                )
                            }
                        }
                    }

                    // register and login buttons
                    Row(
                        modifier = Modifier
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // register button
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.register_now),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = { navigateToRegisterScreen() }
                                        )
                                    },
                                color = Purple40,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        // login button
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    focusManager.clearFocus()
                                    if (username.isBlank() || username.contains(" ")) {
                                        isUsernameError = true
                                    }
                                    if (password.isBlank()) {
                                        isPasswordError = true
                                    }
                                    if (!isUsernameError && !isPasswordError) {
                                        viewModel.onLoginClicked(username,password)
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Purple40
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.login),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                    }

                }
            }

        }
        if (showLoginError) {
            ToastAlerts.Error(message = loginErrorMessage, onDismiss = {
                showLoginError = false
            })
        }
    }

}

@Composable
private fun NormalTextField(
    hint: String,
    text: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onTextChanged: (String) -> Unit
) {
    val errorModifier = if (isError) Modifier.border(
        width = 1.dp,
        color = ColorTextError,
        shape = RoundedCornerShape(8.dp)
    ) else Modifier

    CustomTextField(
        text = text,
        onTextChanged = onTextChanged,
        hint = hint,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = ColorTextField,
                shape = RoundedCornerShape(8.dp)
            )
            .then(errorModifier)
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun PasswordTextField(
    hint: String,
    text: String,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Next
    ),
    onTextChanged: (String) -> Unit,
    onVisibilityChanged: (Boolean) -> Unit
) {
    val errorModifier = if (isError) Modifier.border(
        width = 1.dp,
        color = ColorTextError,
        shape = RoundedCornerShape(8.dp)
    ) else Modifier

    CustomPasswordTextField(
        hint = hint,
        text = text,
        onTextChanged = onTextChanged,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        isVisible = isVisible,
        isError = isError,
        visibilityIconSize = 20.dp,
        onVisibilityChanged = onVisibilityChanged,
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = ColorTextField,
                shape = RoundedCornerShape(8.dp)
            )
            .then(errorModifier)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        textStyle = MaterialTheme.typography.bodyMedium
    )
}