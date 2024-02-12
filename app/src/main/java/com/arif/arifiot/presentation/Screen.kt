package com.arif.arifiot.presentation

sealed class Screen(val route: String) {
    object DeviceListScreen : Screen("device_list_screen")
    object DeviceScreen : Screen("device_screen")
    object DeviceInsertScreen : Screen("device_insert_screen")
}