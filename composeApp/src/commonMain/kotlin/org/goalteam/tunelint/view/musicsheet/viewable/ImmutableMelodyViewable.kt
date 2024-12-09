package org.goalteam.tunelint.view.musicsheet.viewable

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.view.musicsheet.ExternalEvaluatableGeometryData
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

interface ImmutableMelodyViewable : ImmutableMelody {
    @Composable
    fun view(
        vm: RedactorScreenViewModel,
        geometryData: ExternalEvaluatableGeometryData,
    )
}
