package org.goalteam.tunelint.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.random.Random

fun randomPitch() = Random.nextInt(0, 9)

fun randomSymbol(): SymbolViewable {
    val isNote = Random.nextInt(0, 4) != 0

    if (isNote) return NoteViewable(MusicFactory().createNote(randomPitch(), 2))

    return RestViewable(MusicFactory().createRest(2))
}

fun randomMeasure() =
    MeasureViewable(
        MusicFactory().createMeasure(
            listOf(
                randomSymbol(),
                randomSymbol(),
                randomSymbol(),
                randomSymbol(),
            ),
        ),
    )

fun randomMelody(): MelodyViewable =
    MelodyViewable(
        MusicFactory().createMelody(
            "swag like ohio",
            listOf(
                randomMeasure(),
                randomMeasure(),
                randomMeasure(),
            ),
        ),
    )

@Composable
fun MusicSheetView(vm: RedactorScreenViewModel) {
    var ms by remember { mutableStateOf(randomMelody()) }

    Column {
        ms.view()
        Button(
            onClick = { ms = randomMelody() },
        ) {
            Text("generate random")
        }
    }
}
