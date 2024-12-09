package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.CurrentMode
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.UndoRedoAvailable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun VerticalToolbarView(vm: RedactorScreenViewModel) {
    val padding = 4.dp
    val smallPadding = 0.dp
    val buttonDiameter = 40.dp
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

    Column(modifier = Modifier.padding(padding)) {
        RedoUndoButtons(padding, smallPadding, vm, enableUndo, buttonDiameter, enableRedo)
        ModeButtons(padding, smallPadding, vm, currentMode, buttonDiameter)
    }
}
