package org.goalteam.tunelint.view.lint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
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
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.go_to

@Suppress("ktlint:standard:function-naming")
@Composable
fun LintView(
    messages: MutableState<List<Status>>,
    bottom: Dp,
    highlight: (Status, Color) -> Unit,
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
                Row {
                    it.apply {
                        first.text(second)
                        GoToButton(highlight)
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun Pair<Status, Color>.GoToButton(highlight: (Status, Color) -> Unit) {
    StyledButton(
        onClick = { highlight(first, second) },
        modifier = Modifier.requiredSize(20.dp).offset(y = 8.dp),
        colors = selectableButtonColors(),
    ) {
        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Res.drawable.go_to),
                contentDescription = "go to",
            )
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
