package org.goalteam.tunelint

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
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

    MaterialTheme {
        Column {
            Menu(vm.value) { vm.value = it }
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                HorizontalToolbarView(vm.value)
            }
            Row {
                VerticalToolbarView(vm.value)
                Workbench(vm, vm.value.lint)
            }
        }
    }
}
