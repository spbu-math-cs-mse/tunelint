package org.goalteam.tunelint.view.viewable

import org.goalteam.tunelint.model.core.Melody

interface MelodyViewable :
    ImmutableMelodyViewable,
    Melody {
    val snapshot: ImmutableMelodyViewable

    override fun clone(): MelodyViewable
}
