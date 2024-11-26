package org.goalteam.tunelint.view.musicsheet

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun MusicSheetView(vm: RedactorScreenViewModel) {
    val melody = vm.melody

    melody.view(vm, GeometryData(20, 20, 50, 50))
}
