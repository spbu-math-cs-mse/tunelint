package org.goalteam.tunelint.view.menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun SaveAsDialog(
    parent: Frame? = null,
    hide: () -> Unit,
    handle: (String) -> Unit,
) = AwtWindow(
    create = {
        SaveFileDialog(parent, hide) { if (it != null) handle(it) }
    },
    dispose = {
        it.dispose()
        hide()
    },
)

private class SaveFileDialog(
    parent: Frame? = null,
    private val hide: () -> Unit,
    private val handle: (String?) -> Unit,
) : FileDialog(parent, "Choose a file", SAVE) {
    override fun setVisible(visible: Boolean) {
        super.setVisible(visible)
        if (!visible) {
            hide.invoke()
            if (directory != null && file != null) handle(directory + file)
        }
    }
}
