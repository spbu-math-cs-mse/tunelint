package org.goalteam.tunelint.view.lint

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.lint.status.Warning

@Suppress("ktlint:standard:function-naming")
@Composable
fun LintView(messages: MutableState<List<Status>>) {
    Box(
        modifier =
            Modifier
                .border(
                    BorderStroke(1.dp, Color.LightGray),
                    RoundedCornerShape(4.dp),
                ).verticalScroll(
                    rememberScrollState(),
                ).requiredSize(width = 1000.dp, height = 400.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Column {
            statuses(messages.value).forEach {
                it.first.text(it.second)
            }
        }
    }
}

private fun statuses(statuses: Collection<Status>): List<Pair<Status, Color>> {
    val colors = statuses.map { it.messageColor() }
    return statuses.zip(colors)
}

@Composable
private fun Status.text(color: Color) {
    Text(
        color = color,
        text = message(),
        modifier = Modifier.background(Color.White).padding(5.dp),
    )
}

private fun Status.messageColor(): Color {
    if (this is Error) {
        return Color.Red
    }
    if (this is Warning) {
        return Color.Yellow
    }
    return Color.Black
}
