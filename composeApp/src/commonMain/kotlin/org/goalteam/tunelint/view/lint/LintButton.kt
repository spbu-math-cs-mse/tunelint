package org.goalteam.tunelint.view.lint

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.lint.Rules
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.style.StyledButton
import org.goalteam.tunelint.view.style.StyledTooltipArea
import org.goalteam.tunelint.view.style.selectableButtonColors
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.lint

@Suppress("ktlint:standard:function-naming")
@Composable
fun LintButton(
    sheet: MusicSheet,
    handle: (List<Status>) -> Unit,
) {
    StyledTooltipArea(hint = "Lint") {
        StyledButton(
            onClick = {
                handle(Rules(sheet.contents()).all().map { it.check() })
            },
            modifier = Modifier.padding(0.dp, 8.dp).requiredSize(60.dp),
            colors = selectableButtonColors(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.lint),
                contentDescription = "lint",
                modifier = Modifier.size(28.dp),
            )
        }
    }
}
