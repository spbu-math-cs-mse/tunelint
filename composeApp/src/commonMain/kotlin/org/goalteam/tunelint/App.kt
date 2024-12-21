package org.goalteam.tunelint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.Workbench
import org.goalteam.tunelint.view.menu.Menu
import org.goalteam.tunelint.view.toolbar.HorizontalToolbarView
import org.goalteam.tunelint.view.toolbar.VerticalToolbarView
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val vm = mutableStateOf(RedactorScreenViewModel(MusicSheet("")))
    val lint = mutableStateOf(listOf<Status>())
    MaterialTheme {
        Column {
            Menu(vm.value) { vm.value = it }
            HorizontalToolbarView(vm.value) {
                lint.value = it
            }
            Row {
                VerticalToolbarView(vm.value)
                Workbench(vm, lint)
            }
        }
    }
}
