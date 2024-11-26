package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.CurrentMode
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.UndoRedoAvailable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun VerticalToolbarView(vm: RedactorScreenViewModel) {
    val padding = 8.dp
    val smallPadding = 2.dp
    val buttonDiameter = 28.dp
    var enableUndo: Boolean by remember { mutableStateOf(false) }
    var enableRedo: Boolean by remember { mutableStateOf(false) }
    var currentMode: Mode by remember { mutableStateOf(Mode.Add) }

    val availabilityListener = object : Notifiable<UndoRedoAvailable> {
        override fun notify(notification: UndoRedoAvailable) {
            enableUndo = notification.undoAvailable
            enableRedo = notification.redoAvailable
        }
    }

    val modeListener = object : Notifiable<CurrentMode> {
        override fun notify(notification: CurrentMode) {
            currentMode = notification.mode
        }
    }

    vm.musicSheet.persistenceManager.subscribe(availabilityListener)
    vm.musicSheet.persistenceManager.synchronize(availabilityListener)
    vm.interactor.subscribe(modeListener)
    vm.interactor.synchronize(modeListener)

    Column(modifier = Modifier.padding(padding)) {
        Column(modifier = Modifier.padding(0.dp, padding)) {
            Column(modifier = Modifier.padding(0.dp, smallPadding)) {
                Button(
                    onClick = { vm.musicSheet.persistenceManager.undo() },
                    enabled = enableUndo,
                    shape = CircleShape,
                    modifier = Modifier.size(buttonDiameter),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text("\u27F2")
                }
            }
            Column(modifier = Modifier.padding(0.dp, smallPadding)) {
                Button(
                    onClick = { vm.musicSheet.persistenceManager.redo() },
                    enabled = enableRedo,
                    shape = CircleShape,
                    modifier = Modifier.size(buttonDiameter),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text("\u27F3")
                }
            }
        }
        Column(modifier = Modifier.padding(0.dp, padding)) {
            Column(modifier = Modifier.padding(0.dp, smallPadding)) {
                Button(
                    onClick = { vm.interactor.setMode(Mode.Add) },
                    enabled = currentMode != Mode.Add,
                    shape = CircleShape,
                    modifier = Modifier.size(buttonDiameter),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF00BB00),
                        disabledBackgroundColor = Color(0xFF77BB77),
                        contentColor = Color.White
                    ),
                ) {
                    Text("+")
                }
            }
            Column(modifier = Modifier.padding(0.dp, smallPadding)) {
                Button(
                    onClick = { vm.interactor.setMode(Mode.Delete) },
                    enabled = currentMode != Mode.Delete,
                    shape = CircleShape,
                    modifier = Modifier.size(buttonDiameter),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFCC0000),
                        disabledBackgroundColor = Color(0xFFBB7777),
                        contentColor = Color.White
                    ),
                ) {
                    Text("-")
                }
            }
        }
    }
}
