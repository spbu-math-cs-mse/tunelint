package org.goalteam.tunelint.view.musicsheet.viewable.impl

import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMeasureViewable
import org.goalteam.tunelint.view.musicsheet.viewable.MeasureViewable

class ImmutableMeasureViewableImpl(
    private val measureViewable: MeasureViewable,
) : ImmutableMeasureViewable by measureViewable
