package org.goalteam.tunelint.view.toolbar

import androidx.compose.material.Button
import androidx.compose.runtime.*
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.pow

@Composable
fun NoteTypesButtons(vm: RedactorScreenViewModel) {
    data class Value(
        val log: Int,
        val text: String,
        val func: @Composable () -> Unit,
    )

    val options =
        listOf(
            Value(-4, "Sixteenth", {
                NoteIcon(-4)
            }),
            Value(-3, "Eighth", {
                NoteIcon(-3)
            }),
            Value(-2, "Quarter", {
                NoteIcon(-2)
            }),
            Value(-1, "Half", {
                NoteIcon(-1)
            }),
            Value(0, "Whole", {
                NoteIcon(0)
            }),
            Value(1, "Double", {
                NoteIcon(1)
            }),
        )

    val noteValue: PrimaryNoteValue = vm.interactor.getValue()
    val magicalShift = 8
    var currentOrder: Int by remember { mutableStateOf(-2) }
    val orderListener =
        object : Notifiable<Boolean> {
            override fun notify(notification: Boolean): Boolean {
                currentOrder = vm.interactor.getValue().order()
                println(11111111113333)
                return true
            }
        }
    vm.interactor.subscribe(orderListener)
    val selectedOption =
        remember {
            mutableStateOf(
                options.find {
                    2.0.pow(it.log.toDouble() + magicalShift) == noteValue.value().value.toDouble()
                } ?: Value(0, "Select option", {}),
            )
        }

    options.forEach { option ->
        Button(
            onClick = {
                selectedOption.value = option
                vm.interactor.setValue(PrimaryNoteValue(option.log))
                println(vm.interactor.getValue().order())
            },
            enabled = currentOrder != option.log,
            colors = editButtonColors(),
            elevation = editButtonElevation(),
        ) {
            // Text(text = option.text)
            option.func.invoke()
        }
    }
}
