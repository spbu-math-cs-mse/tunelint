package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.impl.UndoRedoAvailable
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun ToolbarView(vm: RedactorScreenViewModel) {
    val padding = 8.dp
    Row(modifier = Modifier.padding(0.dp, padding)) {
        var enableUndo: Boolean by remember { mutableStateOf(false) }
        var enableRedo: Boolean by remember { mutableStateOf(false) }

        class ToolbarUndoRedoListener : Notifiable<UndoRedoAvailable> {
            override fun notify(notification: UndoRedoAvailable): Boolean {
                enableUndo = notification.undoAvailable
                enableRedo = notification.redoAvailable
                return true
            }
        }
        val listener = ToolbarUndoRedoListener()
        vm.musicSheet.persistenceManager.subscribe(listener)
        vm.musicSheet.persistenceManager.synchronize(listener)
        Column(modifier = Modifier.padding(padding, 0.dp)) {
            Button(
                onClick = { vm.musicSheet.persistenceManager.undo() },
                enabled = enableUndo,
            ) {
                Text("Undo")
            }
        }
        Column(modifier = Modifier.padding(padding, 0.dp)) {
            Button(
                onClick = { vm.musicSheet.persistenceManager.redo() },
                enabled = enableRedo,
            ) {
                Text("Redo")
            }
        }
        Column(modifier = Modifier.padding(padding, 0.dp)) {
            Button(onClick = { vm.interactor.setMode(Mode.Add) }) {
                Text("Add")
            }
        }
        Column(modifier = Modifier.padding(padding, 0.dp)) {
            Button(onClick = { vm.interactor.setMode(Mode.Delete) }) {
                Text("Delete")
            }
        }
        val expanded = remember { mutableStateOf(false) }

        // List of items to show in the dropdown menu
        data class Value(
            val log: Int,
            val text: String,
        )
        val options =
            listOf(
                Value(-3, "Eighth"),
                Value(-2, "Quarter"),
                Value(-1, "Half"),
                Value(0, "Whole"),
            )

        val selectedOption = remember { mutableStateOf(Value(0, "Select value")) }

        Column(modifier = Modifier.padding(padding, 0.dp)) {
            Button(onClick = { expanded.value = !expanded.value }) {
                Text(text = selectedOption.value.text)
            }
            // The dropdown menu
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        selectedOption.value = option
                        expanded.value = false
                        vm.interactor.setValue(PrimaryNoteValue(option.log))
                    }) {
                        Text(text = option.text)
                    }
                }
            }
        }
    }
}
