package org.goalteam.tunelint.view.style

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StyledTooltipArea(
    hint: String,
    tipOffset: DpOffset = DpOffset.Zero,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val tipDelay = 750
    TooltipArea(
        modifier = modifier,
        tooltip = {
            Card(shape = CutCornerShape(3.dp)) {
                Box(modifier = Modifier.background(Color.LightGray)) {
                    Text(text = hint, modifier = Modifier.padding(4.dp))
                }
            }
        },
        tooltipPlacement = TooltipPlacement.ComponentRect(offset = tipOffset),
        delayMillis = tipDelay,
        content = content,
    )
}
