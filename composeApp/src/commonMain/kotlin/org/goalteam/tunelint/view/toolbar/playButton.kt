package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.musicplay.MusicPlayer
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.play

@Composable
fun playButton(
    vm: RedactorScreenViewModel,
    player: MusicPlayer,
    buttonDiameter: Dp,
    iconSize: Dp,
    buttonTip : @Composable (String, @Composable () -> Unit) -> Unit,
){
    buttonTip("Play") {
        Button(
            onClick = { player.play(vm.melody)},
            shape = CutCornerShape(0.dp),
            modifier = Modifier.size(buttonDiameter),
            contentPadding = PaddingValues(0.dp),
            colors = editButtonColors(),
            elevation = editButtonElevation(),
        ) {
            Icon(
                painter = painterResource(Res.drawable.play),
                contentDescription = "player",
                modifier = Modifier.size(iconSize),
            )
        }
    }
}