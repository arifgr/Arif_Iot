# Arif IOT

Arif IOT is an app for controlling smart devices of Arif IOT Corp.


## Usage

The home screen shows your devices. You can see them or add a new device. If you want to see the properties of a device, click on it.

The "add a new device screen" shows the three devices that Arif IOT Corp produces. The devices are the Arif Smart Light Bulb, Arif Smart Door Lock, and Arif Smart Heater.

The "Device Properties Screen" shows the device's type name, device's name, device's working state, and if it is a heater its temperature. You can change the working state, name, and temperature on the screen. Also, you can delete the device.

## Technical Details of the App

The Android application has been built with Jetpack Compose. Events and States are used to manage user interactions, observations, and data transfers. Model - View - View Model architecture and Usecase - Repository pattern of clean architecture is used. You can see the data, domain, and presentation layers.

Hilt library is used for dependency injection.
Room local database library is used for data storage and database requests because it is a prototype of an IoT application.