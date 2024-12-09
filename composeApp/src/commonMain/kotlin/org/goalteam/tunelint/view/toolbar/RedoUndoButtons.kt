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
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun RedoUndoButtons(
    padding: Dp,
    smallPadding: Dp,
    vm: RedactorScreenViewModel,
    enableUndo: Boolean,
    buttonDiameter: Dp,
    enableRedo: Boolean,
) {
    Column(modifier = Modifier.padding(0.dp, padding)) {
        Column(modifier = Modifier.padding(0.dp, smallPadding)) {
            Button(
                onClick = { vm.musicSheet.persistenceManager.undo() },
                enabled = enableUndo,
                shape = CutCornerShape(0.dp),
                modifier = Modifier.size(buttonDiameter),
                contentPadding = PaddingValues(0.dp),
                colors = undoRedoButtonColors(),
                elevation = editButtonElevation(),
            ) {
                Text("\u27F2")
            }
        }
        Column(modifier = Modifier.padding(0.dp, smallPadding)) {
            Button(
                onClick = { vm.musicSheet.persistenceManager.redo() },
                enabled = enableRedo,
                shape = CutCornerShape(0.dp),
                modifier = Modifier.size(buttonDiameter),
                contentPadding = PaddingValues(0.dp),
                colors = undoRedoButtonColors(),
                elevation = editButtonElevation(),
            ) {
                Text("\u27F3")
            }
        }
    }
}
