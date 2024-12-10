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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.eraser
import tunelint.composeapp.generated.resources.quill

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
    var currentMode: Mode by remember { mutableStateOf(Mode.AddNote) }

    val modeListener =
        object : Notifiable<Boolean> {
            override fun notify(notification: Boolean): Boolean {
                currentMode = vm.interactor.getMode()
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
            onClick = { vm.interactor.setMode(Mode.AddNote) },
            enabled = currentMode != Mode.AddNote,
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
            onClick = { vm.interactor.setMode(Mode.DeleteNote) },
            enabled = currentMode != Mode.DeleteNote,
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

    TooltipArea(
        modifier = Modifier.padding(0.dp, smallPadding),
        tooltip = { Text("AddMeasure") },
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = tipOffset),
        delayMillis = tipDelay,
    ) {
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

    TooltipArea(
        modifier = Modifier.padding(0.dp, smallPadding),
        tooltip = { Text("AddMeasure") },
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = tipOffset),
        delayMillis = tipDelay,
    ) {
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
