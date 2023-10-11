package com.csdev.designwaytest.ui

import androidx.navigation.NavHostController

// Destination paths
object Destination {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val DOG_BREED_LIST_SCREEN = "dog_breed_list"
    const val DOG_BREED_DETAILS_SCREEN = "dog_breed_details"
}

// Destination arguments
object DestinationArgs {
    const val BreedReferenceId = "breed_reference_id"
}

// Destination routes
class Route(navController: NavHostController) {
    val toBack: () -> Unit = {
        navController.popBackStack()
    }
    val toRegisterUser: () -> Unit = {
        navController.navigate(Destination.REGISTER_SCREEN)
    }
    val toLogout: () -> Unit = {
        navController.navigate(Destination.LOGIN_SCREEN){
            popUpTo(Destination.DOG_BREED_LIST_SCREEN) {
                inclusive = true
            }
        }
    }
    val toDogBreedList: () -> Unit = {
        navController.navigate(Destination.DOG_BREED_LIST_SCREEN){
            popUpTo(Destination.LOGIN_SCREEN) {
                inclusive = true
            }
        }
    }
    val toDogBreedDetails: (String) -> Unit = { breedReferenceId ->
        navController.navigate("${Destination.DOG_BREED_DETAILS_SCREEN}/$breedReferenceId")
    }
}