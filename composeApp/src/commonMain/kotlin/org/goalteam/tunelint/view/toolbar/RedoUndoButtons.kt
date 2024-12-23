package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.UndoRedoAvailable
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.redo
import tunelint.composeapp.generated.resources.undo

@Composable
fun undoRedoButtons(
    vm: RedactorScreenViewModel,
    buttonDiameter: Dp,
    iconSize: Dp,
    buttonTip: @Composable (String, @Composable () -> Unit) -> Unit,
) {
    var enableUndo: Boolean by remember { mutableStateOf(false) }
    var enableRedo: Boolean by remember { mutableStateOf(false) }
    var currentMode: Mode by remember { mutableStateOf(Mode.AddNote) }

    val availabilityListener =
        object : Notifiable<UndoRedoAvailable> {
            override fun notify(notification: UndoRedoAvailable): Boolean {
                enableUndo = notification.undoAvailable
                enableRedo = notification.redoAvailable
                return true
            }
        }

    val modeListener =
        object : Notifiable<Unit> {
            override fun notify(notification: Unit): Boolean {
                currentMode = vm.interactor.getMode()
                return true
            }
        }

    vm.musicSheet.persistenceManager.subscribe(availabilityListener)
    vm.musicSheet.persistenceManager.synchronize(availabilityListener)
    vm.interactor.subscribe(modeListener)
    vm.interactor.synchronize(modeListener)

    buttonTip("Undo") {
        StyledButton(
            onClick = { vm.musicSheet.persistenceManager.undo() },
            enabled = enableUndo,
            modifier = Modifier.size(buttonDiameter),
            colors = undoRedoButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.undo),
                contentDescription = "undo",
                modifier = Modifier.size(iconSize),
            )
        }
    }
    buttonTip("Redo") {
        StyledButton(
            onClick = { vm.musicSheet.persistenceManager.redo() },
            enabled = enableRedo,
            modifier = Modifier.size(buttonDiameter),
            colors = undoRedoButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.redo),
                contentDescription = "redo",
                modifier = Modifier.size(iconSize),
            )
        }
    }
}
