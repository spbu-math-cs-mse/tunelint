package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun ModeButtons(
    padding: Dp,
    smallPadding: Dp,
    vm: RedactorScreenViewModel,
    currentMode: Mode,
    buttonDiameter: Dp,
) {
    Column(modifier = Modifier.padding(0.dp, padding)) {
        Column(modifier = Modifier.padding(0.dp, smallPadding)) {
            Button(
                onClick = { vm.interactor.setMode(Mode.AddNote) },
                enabled = currentMode != Mode.AddNote,
                shape = CutCornerShape(0.dp),
                modifier = Modifier.size(buttonDiameter),
                contentPadding = PaddingValues(0.dp),
                colors = editButtonColors(),
                elevation = editButtonElevation(),
            ) {
                Text("+", fontWeight = FontWeight.ExtraBold)
            }
        }
        Column(modifier = Modifier.padding(0.dp, smallPadding)) {
            Button(
                onClick = { vm.interactor.setMode(Mode.DeleteNote) },
                enabled = currentMode != Mode.DeleteNote,
                shape = CutCornerShape(0.dp),
                modifier = Modifier.size(buttonDiameter),
                contentPadding = PaddingValues(0.dp),
                colors = editButtonColors(),
                elevation = editButtonElevation(),
            ) {
                Text("－", fontWeight = FontWeight.ExtraBold)
            }
        }
        Column(modifier = Modifier.padding(0.dp, smallPadding)) {
            Button(
                onClick = { vm.interactor.setMode(Mode.AddMeasure) },
                enabled = currentMode != Mode.AddMeasure,
                shape = CutCornerShape(0.dp),
                modifier = Modifier.size(buttonDiameter),
                contentPadding = PaddingValues(0.dp),
                colors = editButtonColors(),
                elevation = editButtonElevation(),
            ) {
                Text(text = "M", fontWeight = FontWeight.ExtraBold)
            }
        }
        Column(modifier = Modifier.padding(0.dp, smallPadding)) {
            Button(
                onClick = {
                    vm.interactor.setMode(Mode.DeleteMeasure)
                },
                enabled = currentMode != Mode.DeleteMeasure,
                shape = CutCornerShape(0.dp),
                modifier = Modifier.size(buttonDiameter),
                contentPadding = PaddingValues(0.dp),
                colors = editButtonColors(),
                elevation = editButtonElevation(),
            ) {
                Text(text = "DM", fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
