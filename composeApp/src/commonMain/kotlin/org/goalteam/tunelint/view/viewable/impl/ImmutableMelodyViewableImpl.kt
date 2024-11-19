package org.goalteam.tunelint.view.viewable.impl

import org.goalteam.tunelint.view.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.view.viewable.MelodyViewable

class ImmutableMelodyViewableImpl(
    melodyViewable: MelodyViewable,
) : ImmutableMelodyViewable by melodyViewable
