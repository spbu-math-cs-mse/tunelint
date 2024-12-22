package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.add_measure
import tunelint.composeapp.generated.resources.add_note
import tunelint.composeapp.generated.resources.add_note_rest
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
    val initial = remember { mutableStateOf(Mode.AddNote) }
    val mode = ModeListener(vm.interactor::getMode, initial)

    vm.interactor.subscribe(mode)
    vm.interactor.synchronize(mode)

    AddButton(
        vm = vm,
        buttonDiameter = buttonDiameter,
        iconSize = iconSize,
        currentMode = mode.current(),
        buttonTip = buttonTip,
    )

    buttonTip("Erase") {
        StyledButton(
            onClick = { vm.interactor.setMode(Mode.DeleteNote) },
            enabled = mode.current() != Mode.DeleteNote,
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
            enabled = mode.current() != Mode.AddMeasure,
            modifier = Modifier.size(buttonDiameter),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.add_measure),
                contentDescription = "plus and measure",
                modifier = Modifier.size(iconSize),
            )
        }
    }

    buttonTip("Delete measure") {
        StyledButton(
            onClick = {
                vm.interactor.setMode(Mode.DeleteMeasure)
            },
            enabled = mode.current() != Mode.DeleteMeasure,
            modifier = Modifier.size(buttonDiameter),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.delete_measure),
                contentDescription = "minus and measure",
                modifier = Modifier.size(iconSize),
            )
        }
    }
}

@Composable
fun AddButton(
    vm: RedactorScreenViewModel,
    buttonDiameter: Dp,
    iconSize: Dp,
    currentMode: Mode,
    buttonTip: @Composable (String, @Composable () -> Unit) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.wrapContentWidth().padding(0.dp, 0.dp),
    ) {
        buttonTip("Write") {
            StyledButton(
                onClick = { expanded = true },
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

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            buttonTip("Add note") {
                StyledButton(
                    onClick = {
                        expanded = false
                        vm.interactor.setMode(Mode.AddNote)
                    },
                    enabled = currentMode != Mode.AddNote,
                    colors = selectableButtonColors(),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.add_note),
                        contentDescription = "plus and note",
                        modifier = Modifier.size(iconSize).padding(0.dp, 0.dp),
                    )
                }
            }

            buttonTip("Add rest") {
                StyledButton(
                    onClick = {
                        expanded = false
                        vm.interactor.setMode(Mode.AddRest)
                    },
                    enabled = currentMode != Mode.AddRest,
                    colors = selectableButtonColors(),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.add_note_rest),
                        contentDescription = "plus and rest",
                        modifier = Modifier.size(iconSize),
                    )
                }
            }
        }
    }
}
