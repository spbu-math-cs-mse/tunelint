package org.goalteam.tunelint.view.menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SaveAsDialog(
    parent: Frame? = null,
    handle: (String) -> Unit,
) = AwtWindow(
    create = {
        SaveFileDialog(parent) { if (it != null) handle(it) }
    },
    dispose = FileDialog::dispose,
)

private class SaveFileDialog(
    parent: Frame? = null,
    private val handle: (String?) -> Unit,
) : FileDialog(parent, "Choose a file", SAVE) {
    override fun setVisible(visible: Boolean) {
        super.setVisible(visible)
        if (!visible && directory != null && file != null) {
            handle(directory + file)
        }
    }
}
