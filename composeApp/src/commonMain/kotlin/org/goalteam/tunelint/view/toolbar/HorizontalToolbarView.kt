package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.view.lint.LintButton
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun HorizontalToolbarView(vm: RedactorScreenViewModel) {
    val initial = remember { mutableStateOf(Mode.AddNote) }
    val mode = ModeListener(vm.interactor::getMode, initial)

    vm.interactor.subscribe(mode)
    vm.interactor.synchronize(mode)

    val fixedHeight = 56.dp

    Row(modifier = Modifier.height(fixedHeight)) {
        LintButton(vm)
        when (mode.current()) {
            Mode.AddNote -> HorizontalAddToolbarView(vm)
            Mode.DeleteNote -> HorizontalDeleteToolbarView(vm)
            Mode.AddMeasure -> HorizontalDeleteToolbarView(vm)
            Mode.DeleteMeasure -> HorizontalDeleteToolbarView(vm)
            Mode.AddRest -> HorizontalAddRestToolbarView(vm)
            Mode.ChangeAccidental -> HorizontalAccidentalToolbarView(vm)
        }
    }
}

@Composable
fun HorizontalAddToolbarView(vm: RedactorScreenViewModel) {
    val padding = 8.dp
    Row(modifier = Modifier.padding(0.dp, padding)) {
        NoteTypesButtons(vm)
    }
}

@Composable
fun HorizontalAddRestToolbarView(vm: RedactorScreenViewModel) {
    val padding = 8.dp
    Row(modifier = Modifier.padding(0.dp, padding)) {
        RestTypesButtons(vm)
    }
}

@Composable
fun HorizontalDeleteToolbarView(vm: RedactorScreenViewModel) {
}

@Composable
fun HorizontalAccidentalToolbarView(vm: RedactorScreenViewModel) {
    val padding = 8.dp
    Row(modifier = Modifier.padding(0.dp, padding)) {
        AccidentalTypesButtons(vm)
    }
}
