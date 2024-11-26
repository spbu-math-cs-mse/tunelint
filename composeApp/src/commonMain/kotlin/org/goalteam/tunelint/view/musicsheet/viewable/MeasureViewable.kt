package org.goalteam.tunelint.view.musicsheet.viewable

import org.goalteam.tunelint.model.core.Measure

interface MeasureViewable :
    ImmutableMeasureViewable,
    Measure {
    override fun clone(): MeasureViewable

    val snapshot: ImmutableMeasureViewable
}
