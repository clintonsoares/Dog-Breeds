package com.csdev.designwaytest.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(@ApplicationContext private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
    private val gson = Gson()

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[LOGGED_IN] ?: false
        }

    suspend fun setUserLoggedIn(loggedIn : Boolean) {
        context.dataStore.edit { preferences ->
            preferences[LOGGED_IN] = loggedIn
        }
    }

    companion object {
        //DataStore name
        const val DATASTORE_NAME = "dog_breeds_prefs"
        //Keys to use for saving/retrieving the data from DataStore
        val LOGGED_IN = booleanPreferencesKey("logged_in")
    }
}