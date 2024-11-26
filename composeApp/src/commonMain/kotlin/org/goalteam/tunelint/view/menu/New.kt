package org.goalteam.tunelint.view.menu

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.goalteam.tunelint.musicsheet.MusicSheet

@Suppress("ktlint:standard:function-naming")
@Composable
fun NewButton(handle: (MusicSheet) -> Unit) =
    Button(
        onClick = {
            SaveAsDialog {
                val sheet = MusicSheet(it)
                sheet.save(listOf())
                handle(sheet)
            }
        },
    ) {
        Text("Open")
    }
