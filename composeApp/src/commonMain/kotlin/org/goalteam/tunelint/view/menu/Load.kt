package org.goalteam.tunelint.view.menu

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.AwtWindow
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.style.StyledButton
import java.awt.FileDialog
import java.awt.Frame

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun LoadButton(handle: (MusicSheet?) -> Unit) {
    val show = remember { mutableStateOf(false) }
    StyledButton(
        onClick = {
            show.value = true
        },
    ) {
        Text("Open", color = Color.Black)
    }
    if (show.value) {
        LoadDialog(hide = { show.value = false }) {
            if (it != null) {
                val sheet = MusicSheet(it)
                sheet.load(emptyList())
                handle(sheet)
            } else {
                handle(null)
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun LoadDialog(
    parent: Frame? = null,
    hide: () -> Unit,
    handle: (String?) -> Unit,
) = AwtWindow(
    create = { LoadFileDialog(parent, handle, hide) },
    dispose = {
        it.dispose()
        hide()
    },
)

private class LoadFileDialog(
    parent: Frame? = null,
    private val handle: (String?) -> Unit,
    private val hide: () -> Unit,
) : FileDialog(parent, "Choose a file", LOAD) {
    override fun setVisible(visible: Boolean) {
        super.setVisible(visible)
        if (!visible) {
            hide.invoke()
            if (directory != null && file != null) handle(directory + file)
        }
    }
}
