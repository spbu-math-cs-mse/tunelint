package org.goalteam.tunelint

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Tunelint",
            state = WindowState(placement = WindowPlacement.Maximized),
        ) {
            App()
        }
    }
