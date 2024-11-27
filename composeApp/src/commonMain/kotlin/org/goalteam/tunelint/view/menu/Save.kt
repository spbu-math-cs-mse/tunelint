package org.goalteam.tunelint.view.menu

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import org.goalteam.tunelint.musicsheet.MusicSheet

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SaveButton(sheet: () -> MusicSheet) =
    Button(
        onClick = {
            sheet().save(emptyList())
        },
        colors = MenuColors(),
        shape = menuButtonShape(),
        elevation = menuButtonElevation(),
        enabled = sheet().modified(),
    ) {
        Text("Save", color = Color.Black)
        KeyShortcut(Key.S, ctrl = true)
    }
