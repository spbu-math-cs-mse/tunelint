package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.UndoRedoAvailable
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
    buttonTip : @Composable (String, @Composable () -> Unit) -> Unit,
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
        object : Notifiable<Boolean> {
            override fun notify(notification: Boolean): Boolean {
                currentMode = vm.interactor.getMode()
                return true
            }
        }

    vm.musicSheet.persistenceManager.subscribe(availabilityListener)
    vm.musicSheet.persistenceManager.synchronize(availabilityListener)
    vm.interactor.subscribe(modeListener)
    vm.interactor.synchronize(modeListener)

    buttonTip("Undo"){
        Button(
            onClick = { vm.musicSheet.persistenceManager.undo() },
            enabled = enableUndo,
            shape = CutCornerShape(0.dp),
            modifier = Modifier.size(buttonDiameter),
            contentPadding = PaddingValues(0.dp),
            colors = undoRedoButtonColors(),
            elevation = editButtonElevation(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.undo),
                contentDescription = "undo",
                modifier = Modifier.size(iconSize),
            )
        }
    }
    buttonTip("Redo"){
        Button(
            onClick = { vm.musicSheet.persistenceManager.redo() },
            enabled = enableRedo,
            shape = CutCornerShape(0.dp),
            modifier = Modifier.size(buttonDiameter),
            contentPadding = PaddingValues(0.dp),
            colors = undoRedoButtonColors(),
            elevation = editButtonElevation(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.redo),
                contentDescription = "redo",
                modifier = Modifier.size(iconSize),
            )
        }
    }
}
