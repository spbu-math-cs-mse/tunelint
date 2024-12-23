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
    val options =
        listOf(
            NoteChoiceValue(-3) {
                NoteIcon(Res.drawable.eighth_note, "Eighth")
            },
            NoteChoiceValue(-2) {
                NoteIcon(Res.drawable.quarter_note, "Quarter")
            },
            NoteChoiceValue(-1) {
                NoteIcon(Res.drawable.half_note, "Half")
            },
            NoteChoiceValue(0) {
                NoteIcon(Res.drawable.WholeNote, "Whole", 10.dp)
            },
        )

    typeButtons(vm, options){
        vm.interactor.setValue(it)
    }
}

@Composable
fun RestTypesButtons (vm: RedactorScreenViewModel) {
    val options =
        listOf(
            NoteChoiceValue(-3) {
                NoteIcon(Res.drawable.eighth_rest, "Eighth")
            },
            NoteChoiceValue(-2) {
                NoteIcon(Res.drawable.quarterrest, "Quarter")
            },
            NoteChoiceValue(-1) {
                NoteIcon(Res.drawable.half_rest, "Half")
            },
            NoteChoiceValue(0) {
                NoteIcon(Res.drawable.whole_rest, "Whole")
            },
        )

    typeButtons(vm, options){
        vm.interactor.setValue(it)
    }
}

@Composable
fun typeButtons(
    vm: RedactorScreenViewModel,
    options: List<NoteChoiceValue>,
    onClick: (PrimaryNoteValue) -> Unit
) {
    var currentOrder: Int by remember { mutableStateOf(-2) }
    val orderListener =
        object : Notifiable<Unit> {
            override fun notify(notification: Unit): Boolean {
                currentOrder = vm.interactor.getValue().order()
                return true
            }
        }

    vm.interactor.subscribe(orderListener)

    options.forEach { option ->
        StyledButton(
            onClick = {
                onClick(PrimaryNoteValue(option.log))
            },
            enabled = currentOrder != option.log,
            modifier = Modifier.requiredSize(60.dp),
            colors = selectableButtonColors(),
        ) {
            option.func.invoke()
        }
    }
}

data class NoteChoiceValue(
    val log: Int,
    val func: @Composable () -> Unit,
)