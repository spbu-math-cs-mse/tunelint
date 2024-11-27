package org.goalteam.tunelint.view.menu
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.filesupport.ParserFactory
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun Menu(
    vm: () -> RedactorScreenViewModel,
    update: (RedactorScreenViewModel) -> Unit,
) {
    Row(modifier = Modifier.padding(0.dp, 0.dp)) {
        NewButton { update(RedactorScreenViewModel(it)) }
        SaveButton { vm().musicSheet }
        LoadButton { if (it != null) update(RedactorScreenViewModel(it)) }
        ExportButton(ParserFactory().xml()) { vm().musicSheet }
    }
}
