package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.CurrentMode
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.pow

@Composable
fun HorizontalToolbarView(vm: RedactorScreenViewModel) {
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

    val fixedHeight = 56.dp

    Row(modifier = Modifier.height(fixedHeight)) {
        when (currentMode) {
            Mode.Add -> HorizontalAddToolbarView(vm)
            Mode.Insert -> TODO("Not implemented yet")
            Mode.Delete -> HorizontalDeleteToolbarView(vm)
        }
    }
}

@Composable
fun HorizontalAddToolbarView(vm: RedactorScreenViewModel) {
    val padding = 8.dp
    Row(modifier = Modifier.padding(0.dp, padding)) {
        data class Value(
            val log: Int,
            val text: String,
        )

        val expanded = remember { mutableStateOf(false) }

        val options =
            listOf(
                Value(-4, "Sixteenth"),
                Value(-3, "Eighth"),
                Value(-2, "Quarter"),
                Value(-1, "Half"),
                Value(0, "Whole"),
                Value(1, "Double"),
            )

        val noteValue: PrimaryNoteValue = vm.interactor.getValue()
        val magicalShift = 8
        val selectedOption =
            remember {
                mutableStateOf(
                    options.find {
                        2.0.pow(it.log.toDouble() + magicalShift) == noteValue.value().value.toDouble()
                    } ?: Value(0, "Select option"),
                )
            }

        Column(modifier = Modifier.padding(padding, 0.dp)) {
            Button(
                onClick = { expanded.value = !expanded.value },
                colors = undoRedoButtonColors(),
                elevation = editButtonElevation(),
            ) {
                Text(text = selectedOption.value.text)
            }
            DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption.value = option
                            expanded.value = false
                            vm.interactor.setValue(PrimaryNoteValue(option.log))
                        },
                    ) {
                        Text(text = option.text)
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalDeleteToolbarView(vm: RedactorScreenViewModel) {
}
