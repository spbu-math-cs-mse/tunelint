package org.goalteam.tunelint.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.changerequest.PersistentRequestFactory
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.Symbol
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.random.Random

fun randomLength() = PrimaryNoteValue(Random.nextInt(-2, 1))

fun randomPitch() = Random.nextInt(0, 9)

fun randomNote() = MusicFactory().createNote(randomPitch(), randomLength())

fun randomRest() = MusicFactory().createRest(PrimaryNoteValue(-2))

fun randomSymbol(): Symbol {
    val isRest = Random.nextInt(0, 4) == 0

    if (isRest) return randomRest()

    return randomNote()
}

fun randomWhole() = MusicFactory().createNote(randomPitch(), PrimaryNoteValue(0))

fun randomHalf() = MusicFactory().createNote(randomPitch(), PrimaryNoteValue(-1))

fun randomQuarter() = MusicFactory().createNote(randomPitch(), PrimaryNoteValue(-2))

fun requestOf(symbol: Symbol) =
    PersistentRequestFactory()
        .addSymbol(0, 0, symbol)

fun randomRequest(): PersistentRequest =
    PersistentRequestFactory()
        .addSymbol(0, 0, randomSymbol())

@Composable
fun MusicSheetView(vm: RedactorScreenViewModel) {
    val melody = vm.melody

    vm.musicSheet.persistenceManager.notify(requestOf(randomHalf()))
    vm.musicSheet.persistenceManager.notify(requestOf(randomQuarter()))
    vm.musicSheet.persistenceManager.notify(requestOf(randomRest()))

    Column {
        melody.view()
        Button(onClick = {
            vm.musicSheet.persistenceManager.notify(randomRequest())
            println(
                melody.measures
                    .first()
                    .symbols.size,
            )
        }) {
            Text("RANDOM")
        }
    }
}
