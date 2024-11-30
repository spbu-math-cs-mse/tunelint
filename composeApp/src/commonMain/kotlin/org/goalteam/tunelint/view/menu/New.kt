package org.goalteam.tunelint.view.menu

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import org.goalteam.tunelint.musicsheet.MusicSheet

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun NewButton(handle: (MusicSheet) -> Unit) {
    val show = remember { mutableStateOf(false) }
    Button(
        onClick = {
            show.value = true
        },
        colors = MenuColors(),
        shape = menuButtonShape(),
        elevation = menuButtonElevation(),
    ) {
        Text("New", color = Color.Black)
        KeyShortcut(Key.N, ctrl = true)
    }
    if (show.value) {
        SaveAsDialog(hide = { show.value = false }) {
            val sheet = MusicSheet(it)
            sheet.save(listOf())
            handle(sheet)
        }
    }
}
