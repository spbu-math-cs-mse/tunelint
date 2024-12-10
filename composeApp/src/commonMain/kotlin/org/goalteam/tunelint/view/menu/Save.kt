package org.goalteam.tunelint.view.menu

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.style.StyledButton

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SaveButton(sheet: () -> MusicSheet) =
    StyledButton(
        onClick = {
            sheet().save(emptyList())
        },
        enabled = sheet().modified(),
    ) {
        Text("Save", color = Color.Black)
        KeyShortcut(Key.S, ctrl = true)
    }
