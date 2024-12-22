package org.goalteam.tunelint.view.menu

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.style.StyledButton
import kotlin.io.path.Path
import kotlin.io.path.isRegularFile

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SaveButton(
    sheet: () -> MusicSheet,
    handle: (MusicSheet) -> Unit,
) {
    val show = remember { mutableStateOf(false) }
    StyledButton(
        onClick = {
            if (Path(sheet().location()).isRegularFile()) {
                sheet().save(emptyList())
            } else {
                show.value = true
            }
        },
    ) {
        Text("Save", color = Color.Black)
        KeyShortcut(Key.S, ctrl = true)
    }
    if (show.value) {
        SaveAsDialog(hide = { show.value = false }) {
            val new = MusicSheet(it, sheet().contents().clone())
            new.save(listOf())
            handle(new)
        }
    }
}
