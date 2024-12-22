package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.view.style.StyledTooltipArea
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalToolbarView(vm: RedactorScreenViewModel) {
    val padding = 4.dp
    val smallPadding = 0.dp
    val buttonDiameter = 40.dp
    val iconSize = 24.dp
    val tipOffset = DpOffset(buttonDiameter + 8.dp, -(buttonDiameter / 2) - (15.dp))
    val buttonTip: @Composable (String, @Composable () -> Unit) -> Unit =
        { tip, content ->
            StyledTooltipArea(
                hint = tip,
                tipOffset = tipOffset,
                modifier = Modifier.padding(0.dp, smallPadding),
                content = content,
            )
        }

    Column(modifier = Modifier.padding(padding)) {
        Column(modifier = Modifier.padding(0.dp, padding)) {
            undoRedoButtons(vm, buttonDiameter, iconSize, buttonTip)
        }
        Column(modifier = Modifier.padding(0.dp, padding)) {
            ModeButtons(vm, buttonDiameter, iconSize, buttonTip).compose()
        }
    }
}
