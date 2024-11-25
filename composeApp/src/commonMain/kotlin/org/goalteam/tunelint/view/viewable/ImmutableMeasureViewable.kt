package org.goalteam.tunelint.view.viewable

import org.goalteam.tunelint.model.core.ImmutableMeasure

interface ImmutableMeasureViewable :
    ImmutableMeasure,
    Viewable {
    fun horizontalSteps(): Int
}
