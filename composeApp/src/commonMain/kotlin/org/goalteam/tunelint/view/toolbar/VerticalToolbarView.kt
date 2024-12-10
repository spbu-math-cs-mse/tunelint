package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import tunelint.composeapp.generated.resources.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalToolbarView(vm: RedactorScreenViewModel) {
    val padding = 4.dp
    val smallPadding = 0.dp
    val buttonDiameter = 40.dp
    val iconSize = 24.dp
    val tipOffset = DpOffset(buttonDiameter + 8.dp, -(buttonDiameter / 2) - (15.dp))
    val tipDelay = 750
    val buttonTip: @Composable (String, @Composable () -> Unit) -> Unit =
        {
            tip, content ->
            TooltipArea(
                modifier = Modifier.padding(0.dp, smallPadding),
                tooltip = {
                    Card(shape = CutCornerShape(3.dp)) {
                        Box(modifier = Modifier.background(Color.LightGray)) {
                            Text(text = tip, modifier = Modifier.padding(4.dp))
                        }
                    }
                },
                tooltipPlacement = TooltipPlacement.ComponentRect(offset = tipOffset),
                delayMillis = tipDelay,
                content = content
            )
        }


    Column(modifier = Modifier.padding(padding)) {
        Column(modifier = Modifier.padding(0.dp, padding)) {
            undoRedoButtons(vm, buttonDiameter, iconSize, buttonTip)
        }
        Column(modifier = Modifier.padding(0.dp, padding)) {
            modeButtons(vm, buttonDiameter, iconSize, buttonTip)
        }
    }
}
