package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.black_note_head

@Composable
fun BlackNoteHead() {
    Box {
        Image(
            painter = painterResource(Res.drawable.black_note_head),
            contentDescription = null,
            modifier =
                Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterEnd),
        )
    }
}
