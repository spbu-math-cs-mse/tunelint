package org.goalteam.tunelint.view.sheetsettings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.unit.dp

@Composable
fun SelectorRadioButtons(
    selectedOptionNumber: Int,
    options: List<String>,
    onValueChange: (String) -> Unit,
) {
    Row {
        Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Choose clef:")
        options.forEachIndexed { i, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            ) {
                RadioButton(
                    selected = (selectedOptionNumber == i),
                    onClick = { onValueChange(option) },
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
