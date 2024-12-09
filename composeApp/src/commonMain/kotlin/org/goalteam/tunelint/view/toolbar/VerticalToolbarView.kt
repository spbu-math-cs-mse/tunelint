package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.CurrentMode
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.UndoRedoAvailable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.eraser
import tunelint.composeapp.generated.resources.quill
import tunelint.composeapp.generated.resources.redo
import tunelint.composeapp.generated.resources.undo

@Composable
fun VerticalToolbarView(vm: RedactorScreenViewModel) {
    val padding = 4.dp
    val smallPadding = 0.dp
    val buttonDiameter = 40.dp
    val iconSize = 24.dp
    val tipOffset = DpOffset(16.dp, -(8.dp))
    val tipDelay = 750

    Column(modifier = Modifier.padding(padding)) {
        Column(modifier = Modifier.padding(0.dp, padding)) {
            undoRedoButtons(vm, buttonDiameter, smallPadding, iconSize, tipOffset, tipDelay)
        }
        Column(modifier = Modifier.padding(0.dp, padding)) {
            modeButtons(vm, buttonDiameter, smallPadding, iconSize, tipOffset, tipDelay)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun undoRedoButtons(
    vm: RedactorScreenViewModel,
    buttonDiameter: Dp,
    smallPadding: Dp,
    iconSize: Dp,
    tipOffset: DpOffset,
    tipDelay: Int,
) {
    var enableUndo: Boolean by remember { mutableStateOf(false) }
    var enableRedo: Boolean by remember { mutableStateOf(false) }

    val availabilityListener =
        object : Notifiable<UndoRedoAvailable> {
            override fun notify(notification: UndoRedoAvailable): Boolean {
                enableUndo = notification.undoAvailable
                enableRedo = notification.redoAvailable
                return true
            }
        }

    vm.musicSheet.persistenceManager.subscribe(availabilityListener)
    vm.musicSheet.persistenceManager.synchronize(availabilityListener)

    TooltipArea(
        modifier = Modifier.padding(0.dp, smallPadding),
        tooltip = { Text("undo") },
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = tipOffset),
        delayMillis = tipDelay,
    ) {
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
    TooltipArea(
        modifier = Modifier.padding(0.dp, smallPadding),
        tooltip = { Text("redo") },
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = tipOffset),
        delayMillis = tipDelay,
    ) {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun modeButtons(
    vm: RedactorScreenViewModel,
    buttonDiameter: Dp,
    smallPadding: Dp,
    iconSize: Dp,
    tipOffset: DpOffset,
    tipDelay: Int,
) {
    var currentMode: Mode by remember { mutableStateOf(Mode.Add) }

    val modeListener =
        object : Notifiable<CurrentMode> {
            override fun notify(notification: CurrentMode): Boolean {
                currentMode = notification.mode
                return true
            }
        }
    vm.interactor.subscribe(modeListener)
    vm.interactor.synchronize(modeListener)

    TooltipArea(
        modifier = Modifier.padding(0.dp, smallPadding),
        tooltip = { Text("write") },
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = tipOffset),
        delayMillis = tipDelay,
    ) {
        Button(
            onClick = { vm.interactor.setMode(Mode.Add) },
            enabled = currentMode != Mode.Add,
            shape = CutCornerShape(0.dp),
            modifier = Modifier.size(buttonDiameter),
            contentPadding = PaddingValues(0.dp),
            colors = editButtonColors(),
            elevation = editButtonElevation(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.quill),
                contentDescription = "quill",
                modifier = Modifier.size(iconSize),
            )
        }
    }
    TooltipArea(
        modifier = Modifier.padding(0.dp, smallPadding),
        tooltip = { Text("erase") },
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = tipOffset),
        delayMillis = tipDelay,
    ) {
        Button(
            onClick = { vm.interactor.setMode(Mode.Delete) },
            enabled = currentMode != Mode.Delete,
            shape = CutCornerShape(0.dp),
            modifier = Modifier.size(buttonDiameter),
            contentPadding = PaddingValues(0.dp),
            colors = editButtonColors(),
            elevation = editButtonElevation(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.eraser),
                contentDescription = "eraser",
                modifier = Modifier.size(iconSize),
            )
        }
    }
}