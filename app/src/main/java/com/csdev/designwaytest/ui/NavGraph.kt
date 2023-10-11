package com.csdev.designwaytest.ui

import androidx.navigation.NavHostController

object Destination {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val DOG_BREED_LIST_SCREEN = "dog_breed_list"
    const val DOG_BREED_DETAILS_SCREEN = "dog_breed_details"
}

object DestinationArgs {
    const val DogBreedId = "dog_breed_id"
}

class Route(navController: NavHostController) {
    val toBack: () -> Unit = {
        navController.popBackStack()
    }
    val toRegisterUser: () -> Unit = {
        navController.navigate(Destination.REGISTER_SCREEN)
    }
    val toDogBreedList: () -> Unit = {
        navController.navigate(Destination.DOG_BREED_LIST_SCREEN)
        navController.clearBackStack(Destination.DOG_BREED_LIST_SCREEN)
    }
}