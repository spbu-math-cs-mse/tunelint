package org.goalteam.tunelint.view.viewable.impl

import org.goalteam.tunelint.view.viewable.ImmutableMeasureViewable
import org.goalteam.tunelint.view.viewable.MeasureViewable

class ImmutableMeasureViewableImpl(
    private val measureViewable: MeasureViewable,
) : ImmutableMeasureViewable by measureViewable
