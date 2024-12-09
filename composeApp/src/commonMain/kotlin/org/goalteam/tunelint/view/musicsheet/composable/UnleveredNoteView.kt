package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.viewable.NoteViewable
import org.goalteam.tunelint.view.musicsheet.viewable.hasFlag
import org.goalteam.tunelint.view.musicsheet.viewable.hasStem
import org.goalteam.tunelint.view.musicsheet.viewable.horizontalSteps
import org.goalteam.tunelint.view.musicsheet.viewable.isStemDown
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.note_flag
import tunelint.composeapp.generated.resources.note_stem

@Composable
fun NoteStem(
    geometryData: InternalGeometryData,
    note: NoteViewable,
) {
    Box(
        modifier =
            Modifier
                .requiredSize(
                    width = 1.15 * geometryData.verticalStep,
                    height = geometryData.verticalStep * 6,
                ).rotate(if (note.isStemDown()) 180.0F else 0.0F),
    ) {
        if (note.hasStem()) {
            Image(
                painter = painterResource(Res.drawable.note_stem),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(
                            width = 1.0 / 7 * geometryData.verticalStep,
                            height = 3.0 * geometryData.verticalStep,
                        ).align(Alignment.TopEnd),
            )
        }
    }
}

@Composable
fun NoteHead(
    geometryData: InternalGeometryData,
    note: NoteViewable,
) {
    Box(
        modifier =
            Modifier
                .size(
                    width = 1.2 * geometryData.verticalStep,
                    height = geometryData.verticalStep,
                ).rotate(if (note.isStemDown()) 180.0F else 0.0F),
    ) {
        if (note.primaryValue() >= PrimaryNoteValue.Whole) {
            WholeNote()
        } else if (note.primaryValue() == PrimaryNoteValue.Half) {
            WhiteNoteHead()
        } else {
            BlackNoteHead()
        }
    }
}

@Composable
fun NoteFlags(
    geometryData: InternalGeometryData,
    note: NoteViewable,
) {
    Box(
        modifier =
            Modifier
                .requiredSize(
                    width = (1.0 + 16.0 / 7) * geometryData.verticalStep,
                    height = 5.75 * geometryData.verticalStep,
                ).rotate(if (note.isStemDown()) 180.0F else 0.0F),
    ) {
        if (note.hasFlag()) {
            Image(
                painter = painterResource(Res.drawable.note_flag),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(
                            width = 8.0 / 7 * geometryData.verticalStep,
                            height = 18.0 / 7 * geometryData.verticalStep,
                        ).align(Alignment.TopEnd),
            )
        }
    }
}

@Composable
fun UnleveredNoteView(
    geometryData: InternalGeometryData,
    note: NoteViewable,
) {
    Box(
        modifier =
            Modifier
                .size(
                    height = geometryData.verticalStep,
                    width = note.horizontalSteps() * geometryData.horizontalStep,
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .offset(
                        x =
                            note.stepsBeforeMiddle() * geometryData.horizontalStep -
                                0.6 * geometryData.verticalStep,
                    ), // .border(width = 2.dp, color = Color.Red, shape = RectangleShape),
        ) {
            NoteHead(geometryData, note)
        }

        Box(
            modifier =
                Modifier
                    .offset(
                        x =
                            note.stepsBeforeMiddle() * geometryData.horizontalStep -
                                0.575 * geometryData.verticalStep,
                    ), // .border(width = 2.dp, color = Color.Red, shape = RectangleShape),
        ) {
            NoteStem(geometryData, note)
        }

        Box(
            modifier =
                Modifier.offset(
                    x =
                        note.stepsBeforeMiddle() * geometryData.horizontalStep -
                            (0.5 + 8.0 / 7) * geometryData.verticalStep,
                ),
        ) {
            NoteFlags(geometryData, note)
        }
    }
}
