package com.csdev.designwaytest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.csdev.designwaytest.ui.screens.dogBreedList.DogBreedListScreen
import com.csdev.designwaytest.ui.screens.login.LoginScreen
import com.csdev.designwaytest.ui.screens.register.RegisterScreen
import com.csdev.designwaytest.ui.theme.DesignWayTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesignWayTestTheme(darkTheme = false) {

                val navController = rememberNavController()
                val route = remember(navController) { Route(navController) }

                NavHost(
                    navController = navController,
                    startDestination = Destination.LOGIN_SCREEN,
                    enterTransition = {
                        fadeIn(animationSpec = tween(1))
                    },
                    exitTransition = {
                        fadeOut(animationSpec = tween(1))
                    }
                ) {
                    composable(Destination.LOGIN_SCREEN) {
                        LoginScreen(
                            navigateToRegisterScreen = route.toRegisterUser,
                            navigateToDogBreedListScreen = route.toDogBreedList
                        )
                    }
                    composable(Destination.REGISTER_SCREEN) {
                        RegisterScreen(
                            navigateBack = route.toBack
                        )
                    }
                    composable(Destination.DOG_BREED_LIST_SCREEN) {
                        DogBreedListScreen(
                            navigateToLoginScreen = {},
                            navigateToDogBreedDetailsScreen = {}
                        )
                    }
                }

            }
        }
    }
}