package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.deselect
import tunelint.composeapp.generated.resources.eighth_note
import tunelint.composeapp.generated.resources.flat
import tunelint.composeapp.generated.resources.half_rest
import tunelint.composeapp.generated.resources.natural
import tunelint.composeapp.generated.resources.quarterrest
import tunelint.composeapp.generated.resources.sharp
import tunelint.composeapp.generated.resources.whole_rest

@Composable
fun AccidentalTypesButtons(vm: RedactorScreenViewModel) {

    val options = listOf(
        AccidentalChoiceValue(null) {
            NoteIcon(Res.drawable.deselect, "None")
        },
        AccidentalChoiceValue(Accidental.Flat()) {
            NoteIcon(Res.drawable.flat, "Flat")
        },
        AccidentalChoiceValue(Accidental.Sharp()) {
            NoteIcon(Res.drawable.sharp, "Sharp")
        },
        AccidentalChoiceValue(Accidental.Natural()) {
            NoteIcon(Res.drawable.natural, "Natural")
        },
    )

    var currentAccidental: Accidental? by remember { mutableStateOf(null) }
    val accidentalListener =
        object : Notifiable<Unit> {
            override fun notify(notification: Unit): Boolean {
                currentAccidental = vm.interactor.getAccidental()
                return true
            }
        }

    vm.interactor.subscribe(accidentalListener)
    vm.interactor.synchronize(accidentalListener)

    options.forEach { option ->
        StyledButton(
            onClick = {
                      vm.interactor.setAccidental(option.accidental)
            },
            enabled = currentAccidental != option.accidental,
            modifier = Modifier.requiredSize(60.dp),
            colors = selectableButtonColors(),
        ) {
            option.func.invoke()
        }
    }
}

data class AccidentalChoiceValue(
    val accidental: Accidental?,
    val func: @Composable () -> Unit,
)