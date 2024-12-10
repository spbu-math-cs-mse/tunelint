package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.goalteam.tunelint.lint.Rule
import org.goalteam.tunelint.lint.Rules
import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.lint.status.Warning
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.StyledTooltipArea
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.lint

@Suppress("ktlint:standard:function-naming")
@Composable
fun LintButton(sheet: MusicSheet) {
    val rules = remember { mutableStateOf(Rules(sheet.contents())) }
    val show = remember { mutableStateOf(false) }
    Area(show)
    if (show.value) {
        LintDialog(
            statuses(rules.value),
            hide = {
                rules.value = Rules(sheet.contents())
                show.value = false
            },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun Area(show: MutableState<Boolean>) {
    StyledTooltipArea(hint = "Lint") {
        StyledButton(
            onClick = {
                show.value = true
            },
            modifier = Modifier.padding(0.dp, 8.dp),
        ) {
            Icon(
                painter = painterResource(Res.drawable.lint),
                contentDescription = "lint",
                modifier = Modifier.size(28.dp),
            )
        }
    }
}

private fun statuses(rules: Rules): List<Pair<Status, Color>> {
    val statuses = rules.all().map(Rule::check)
    val colors = statuses.map { it.messageColor() }
    return statuses.zip(colors)
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun LintDialog(
    statuses: Collection<Pair<Status, Color>>,
    hide: () -> Unit,
) = Dialog(properties = DialogProperties(usePlatformDefaultWidth = false), onDismissRequest = hide) {
    Column(modifier = Modifier.background(Color.White)) {
        statuses.forEach { (status, color) -> status.text(color) }
    }
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
