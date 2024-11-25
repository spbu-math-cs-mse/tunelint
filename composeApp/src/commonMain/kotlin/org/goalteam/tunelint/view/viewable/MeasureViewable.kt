package org.goalteam.tunelint.view.viewable

import org.goalteam.tunelint.model.core.Measure

interface MeasureViewable :
    ImmutableMeasureViewable,
    Measure {
    override fun clone(): MeasureViewable

    val snapshot: ImmutableMeasureViewable
}
