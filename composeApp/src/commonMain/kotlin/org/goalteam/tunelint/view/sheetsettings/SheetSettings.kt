package org.goalteam.tunelint.view.sheetsettings

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.log2
import kotlin.math.roundToInt

@Composable
fun SheetSettings(vm: RedactorScreenViewModel) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedClef by remember { mutableStateOf(vm.melody.clef) }
    var number1 by remember {
        mutableStateOf(
            TextFieldValue(
                vm.melody.timeSignature.count
                    .toString(),
            ),
        )
    }
    var number2 by remember {
        mutableStateOf(
            TextFieldValue(
                vm.melody.timeSignature.primary
                    .denominator()
                    .toString(),
            ),
        )
    }

    StyledButton(
        onClick = { isDialogOpen = true },
        enabled = true,
    ) {
        Text("  Sheet settings", color = Color.Black)
    }

    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            confirmButton = {
                TextButton(onClick = {
                    val num1 = number1.text.toIntOrNull() ?: 0
                    val num2 = number2.text.toIntOrNull() ?: 0
                    vm.melody.setTimeSignature(
                        TimeSignature(
                            num1,
                            PrimaryNoteValue(-log2(num2.toFloat()).roundToInt()),
                        ),
                    )
                    isDialogOpen = false
                    vm.melody.setClef(selectedClef)
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    isDialogOpen = false
                    selectedClef = vm.melody.clef
                }) {
                    Text("Cancel")
                }
            },
            title = { Text("Melody settings") },
            text = {
                Column {
                    SelectorRadioButtons(selectedClef.type.ordinal, listOf("G", "C", "F"), { optionName ->
                        selectedClef = Clef(Clef.ClefType.valueOf(optionName))
                    })
                    OutlinedTextField(
                        value = number1,
                        onValueChange = { number1 = it },
                        label = { Text("Time signature numerator") },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = number2,
                        onValueChange = { number2 = it },
                        label = { Text("Time signature denominator") },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
        )
    }
}
