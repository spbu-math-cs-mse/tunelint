package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun HorizontalToolbarView(vm: RedactorScreenViewModel) {
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

    val fixedHeight = 56.dp

    Row(modifier = Modifier.height(fixedHeight)) {
        when (currentMode) {
            Mode.AddNote -> HorizontalAddToolbarView(vm)
            Mode.InsertNote -> TODO("Not implemented yet")
            Mode.DeleteNote -> HorizontalDeleteToolbarView(vm)
            Mode.AddMeasure -> HorizontalDeleteToolbarView(vm)
            Mode.DeleteMeasure -> HorizontalDeleteToolbarView(vm)
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
fun HorizontalDeleteToolbarView(vm: RedactorScreenViewModel) {
}
