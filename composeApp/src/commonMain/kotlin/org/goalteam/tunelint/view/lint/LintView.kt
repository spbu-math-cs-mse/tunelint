package org.goalteam.tunelint.view.lint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.lint.status.Warning

@Suppress("ktlint:standard:function-naming")
@Composable
fun LintView(
    messages: MutableState<List<Status>>,
    bottom: Dp,
) {
    Box(
        modifier =
            Modifier
                .verticalScroll(
                    rememberScrollState(),
                ).fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .requiredHeight(bottom)
                .background(Color.White),
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
        return Color(0xffffa500)
    }
    return Color.Black
}
