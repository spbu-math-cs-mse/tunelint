package org.goalteam.tunelint

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.musicsheetcontainer.MusicSheetImpl
import org.goalteam.tunelint.view.MusicSheetView
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.io.path.Path

@Composable
@Preview
fun App() {
    MaterialTheme {
        // TODO: create main view
        val container = MusicSheetImpl(Path(""))
        val vm = RedactorScreenViewModel(container)
        Column {
            Text(text = "this is main ui stub")
            MusicSheetView(vm)
        }
    }
}
