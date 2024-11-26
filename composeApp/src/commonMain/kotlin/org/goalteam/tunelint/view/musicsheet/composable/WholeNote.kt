package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.WholeNote

@Composable
fun WholeNote() {
    Image(
        painter = painterResource(Res.drawable.WholeNote),
        contentDescription = null,
        modifier =
            Modifier
                .fillMaxSize(),
    )
}
