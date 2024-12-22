package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.add_measure
import tunelint.composeapp.generated.resources.add_note
import tunelint.composeapp.generated.resources.add_note_rest
import tunelint.composeapp.generated.resources.delete_measure
import tunelint.composeapp.generated.resources.eraser

class ModeButtons(
    private val vm: RedactorScreenViewModel,
    private val diameter: Dp,
    private val icon: Dp,
    private val tip: @Composable (String, @Composable () -> Unit) -> Unit,
) {
    @Composable
    fun compose() {
        val initial = remember { mutableStateOf(Mode.AddNote) }
        val mode = ModeListener(vm.interactor::getMode, initial)

        vm.interactor.subscribe(mode)
        vm.interactor.synchronize(mode)

        ModeSelectionButton(
            "Add note",
            Mode.AddNote,
            Res.drawable.add_note,
            "plus and note",
            mode,
        )
        ModeSelectionButton(
            "Add rest",
            Mode.AddRest,
            Res.drawable.add_note_rest,
            "plus and rest",
            mode,
        )
        ModeSelectionButton(
            "Erase",
            Mode.DeleteNote,
            Res.drawable.eraser,
            "eraser",
            mode,
        )
        ModeSelectionButton(
            "Add measure",
            Mode.AddMeasure,
            Res.drawable.add_measure,
            "plus and measure",
            mode,
        )
        ModeSelectionButton(
            "Delete measure",
            Mode.DeleteMeasure,
            Res.drawable.delete_measure,
            "minus and measure",
            mode,
        )
    }

    @Suppress("ktlint:standard:function-naming")
    @Composable
    private fun ModeSelectionButton(
        text: String,
        mode: Mode,
        res: DrawableResource,
        description: String,
        current: ModeListener,
    ) = tip(text) {
        StyledButton(
            onClick = {
                vm.interactor.setMode(mode)
            },
            enabled = current.current() != mode,
            modifier = Modifier.size(diameter),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(res),
                contentDescription = description,
                modifier = Modifier.size(icon),
            )
        }
    }
}
