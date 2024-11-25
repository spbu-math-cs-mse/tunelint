package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.white_note_head

@Composable
fun WhiteNoteHead() {
    Image(
        painter = painterResource(Res.drawable.white_note_head),
        contentDescription = null,
        modifier =
            Modifier
                .fillMaxSize(),
    )
}
