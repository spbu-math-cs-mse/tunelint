package org.goalteam.tunelint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.lint.LintView
import org.goalteam.tunelint.view.menu.Menu
import org.goalteam.tunelint.view.musicsheet.MusicSheetView
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
                Column(
                    modifier =
                        Modifier
                            .padding(all = 50.dp),
                ) {
                    MusicSheetView(vm.value)
                    if (lint.value.isNotEmpty()) {
                        LintView(lint)
                    }
                }
            }
        }
    }
}
