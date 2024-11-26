package org.goalteam.tunelint.view.menu

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.AwtWindow
import org.goalteam.tunelint.musicsheet.MusicSheet
import java.awt.FileDialog
import java.awt.Frame

@Suppress("ktlint:standard:function-naming")
@Composable
fun LoadButton(handle: (MusicSheet?) -> Unit) =
    Button(
        onClick = {
            LoadDialog {
                if (it != null) {
                    val sheet = MusicSheet(it)
                    sheet.load(emptyList())
                    handle(sheet)
                } else {
                    handle(null)
                }
            }
        },
    ) {
        Text("Open")
    }

@Suppress("ktlint:standard:function-naming")
@Composable
private fun LoadDialog(
    parent: Frame? = null,
    handle: (String?) -> Unit,
) = AwtWindow(
    create = { LoadFileDialog(parent, handle) },
    dispose = FileDialog::dispose,
)

private class LoadFileDialog(
    parent: Frame? = null,
    private val handle: (String?) -> Unit,
) : FileDialog(parent, "Choose a file", LOAD) {
    override fun setVisible(visible: Boolean) {
        super.setVisible(visible)
        if (!visible) handle(file)
    }
}
