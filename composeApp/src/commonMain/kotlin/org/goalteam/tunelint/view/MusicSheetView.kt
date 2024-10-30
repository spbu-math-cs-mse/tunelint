package org.goalteam.tunelint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

fun Note.letter(): String {
    val letters = listOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
    return letters[value() % 12]
}

@Composable
fun StaffLine() {
    Box(
        modifier =
            Modifier
                .size(height = 20.dp, width = 500.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .size(height = 5.dp, width = 500.dp)
                    .background(Color.Black)
                    .align(Alignment.Center),
        )
    }
}

@Composable
fun NoteView(note: Note) {
    Box(
        modifier =
            Modifier
                .size(
                    height = 90.dp,
                    width = 40.dp,
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(top = 75.dp - note.value().dp * 10)
                    .size(width = 30.dp, height = 30.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.Red),
        ) {
            Text(
                text = note.letter(),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
fun MusicSheetView(vm: RedactorScreenViewModel) {
    // TODO: create MusicSheetView
    Column(
        modifier =
            Modifier
                .background(Color.White)
                .padding(5.dp, 5.dp),
    ) {
        Text(text = "this is sheet view stub")
        Box(
            modifier =
                Modifier
                    .size(height = 150.dp, width = 500.dp),
        ) {
            Column {
                repeat(5) {
                    StaffLine()
                }
            }
            Row(
                modifier =
                    Modifier
                        .padding(10.dp),
            ) {
                vm.state.forEach {
                    NoteView(it)
                }
            }
        }
        Button(onClick = { vm.interactionEvent() }) {
            Text("add note")
        }
    }
}
