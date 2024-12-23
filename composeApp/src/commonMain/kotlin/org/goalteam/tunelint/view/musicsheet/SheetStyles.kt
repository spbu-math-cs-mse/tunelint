package org.goalteam.tunelint.view.musicsheet

import androidx.compose.ui.Modifier
import java.util.IdentityHashMap

class SheetStyles(
    private val styles: IdentityHashMap<Any, Modifier> = IdentityHashMap(),
) {
    fun find(element: Any?): Modifier = styles.getOrDefault(element, Modifier)
}
