package org.goalteam.tunelint.view.toolbar

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun MeasureButton(vm: RedactorScreenViewModel) {
    Button(
        onClick = {
            vm.interactor.setMode(Mode.AddMeasure)
        },
        colors = selectableButtonColors(),
        elevation = editButtonElevation(),
    ) {
        Text(text = "M")
    }
}
