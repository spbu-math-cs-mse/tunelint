package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.add_measure
import tunelint.composeapp.generated.resources.delete_measure
import tunelint.composeapp.generated.resources.eraser
import tunelint.composeapp.generated.resources.quill

@Composable
fun modeButtons(
    vm: RedactorScreenViewModel,
    buttonDiameter: Dp,
    iconSize: Dp,
    buttonTip: @Composable (String, @Composable () -> Unit) -> Unit,
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

    buttonTip("Write") {
        StyledButton(
            onClick = { vm.interactor.setMode(Mode.AddNote) },
            enabled = currentMode != Mode.AddNote,
            modifier = Modifier.size(buttonDiameter),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.quill),
                contentDescription = "quill",
                modifier = Modifier.size(iconSize),
            )
        }
    }

    buttonTip("Erase") {
        StyledButton(
            onClick = { vm.interactor.setMode(Mode.DeleteNote) },
            enabled = currentMode != Mode.DeleteNote,
            modifier = Modifier.size(buttonDiameter),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.eraser),
                contentDescription = "eraser",
                modifier = Modifier.size(iconSize),
            )
        }
    }

    buttonTip("Add measure") {
        StyledButton(
            onClick = { vm.interactor.setMode(Mode.AddMeasure) },
            enabled = currentMode != Mode.AddMeasure,
            modifier = Modifier.size(buttonDiameter),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.add_measure),
                contentDescription = "add measure",
                modifier = Modifier.size(iconSize),
            )
        }
    }

    buttonTip("Delete measure") {
        StyledButton(
            onClick = {
                vm.interactor.setMode(Mode.DeleteMeasure)
            },
            enabled = currentMode != Mode.DeleteMeasure,
            modifier = Modifier.size(buttonDiameter),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.delete_measure),
                contentDescription = "delete measure",
                modifier = Modifier.size(iconSize),
            )
        }
    }
}
