package org.goalteam.tunelint.view.viewable

import org.goalteam.tunelint.model.core.Measure

interface MeasureViewable :
    ImmutableMeasureViewable,
    Measure {
    val snapshot: ImmutableMeasureViewable

    override fun clone(): MeasureViewable
}
