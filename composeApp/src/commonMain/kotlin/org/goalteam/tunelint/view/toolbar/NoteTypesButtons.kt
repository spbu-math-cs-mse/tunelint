package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import tunelint.composeapp.generated.resources.*
import tunelint.composeapp.generated.resources.Res
import kotlin.math.pow

@Suppress("ktlint:standard:function-naming")
@Composable
fun NoteTypesButtons(vm: RedactorScreenViewModel) {
    data class Value(
        val log: Int,
        val text: String,
        val func: @Composable () -> Unit,
    )

    val options =
        listOf(
            Value(-3, "Eighth") {
                NoteIcon(Res.drawable.eighth_note, "Eighth")
            },
            Value(-2, "Quarter") {
                NoteIcon(Res.drawable.quarter_note, "Quarter")
            },
            Value(-1, "Half") {
                NoteIcon(Res.drawable.half_note, "Half")
            },
            Value(0, "Whole") {
                NoteIcon(Res.drawable.WholeNote, "Whole", 10.dp)
            },
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
        StyledButton(
            onClick = {
                selectedOption.value = option
                vm.interactor.setValue(PrimaryNoteValue(option.log))
                println(vm.interactor.getValue().order())
            },
            enabled = currentOrder != option.log,
            modifier = Modifier.requiredSize(60.dp),
            colors = selectableButtonColors(),
        ) {
            option.func.invoke()
        }
    }
}
