package org.goalteam.tunelint.view.musicsheet

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun MusicSheetView(vm: RedactorScreenViewModel) {
    val melody = vm.melody

    BoxWithConstraints(
        modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth(),
    ) {
        val width = with(LocalDensity.current) { constraints.maxWidth.toDp() }
        melody.view(
            vm,
            ExternalEvaluatableGeometryDataImpl(
                verticalStep = 20.0,
                upperMargin = 50.0,
                minimalHorizontalStep = 20.0,
                lowerMargin = 50.0,
                fullWidth = width.value.toDouble(),
                leftMargin = 50.0,
                rightMargin = 50.0,
            ),
        )
    }
}
