package org.goalteam.tunelint.view.musicsheet.viewable.impl

import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.view.musicsheet.viewable.MelodyViewable

class ImmutableMelodyViewableImpl(
    melodyViewable: MelodyViewable,
) : ImmutableMelodyViewable by melodyViewable
