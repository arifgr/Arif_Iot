package com.arif.arifiot.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.presentation.device_detail.DeviceScreen
import com.arif.arifiot.presentation.device_insert.DeviceInsertScreen
import com.arif.arifiot.presentation.device_list.DeviceListScreen
import com.arif.arifiot.ui.theme.ArifIotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArifIotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DeviceListScreen.route
                    ) {
                        composable(
                            route = Screen.DeviceListScreen.route
                        ) {
                            DeviceListScreen(navController)
                        }
                        composable(
                            route = Screen.DeviceScreen.route + "?deviceId={deviceId}",
                            arguments = listOf(
                                navArgument(
                                    name = "deviceId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            DeviceScreen(navController)
                        }
                        composable(
                            route = Screen.DeviceInsertScreen.route
                        ) {
                            DeviceInsertScreen(navController)
                        }
                    }
                }
            }
        }
    }
}