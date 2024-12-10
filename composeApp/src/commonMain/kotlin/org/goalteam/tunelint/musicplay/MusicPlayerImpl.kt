package org.goalteam.tunelint.musicplay
import org.billthefarmer.mididriver.MidiDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.Note

class MusicPlayerImpl : MusicPlayer{

    private val midiDriver = MidiDriver.getInstance()

    /**
     * Воспроизводит несколько нот одновременно с затуханием для каждой
     * @param note Номер ноты
     * @param duration Длительность звучания (в миллисекундах)
     * @param fadeOutTime Длительность затухания (в миллисекундах)
     */
    fun playNoteWithFadeOut(note: Int, duration: Long, fadeOutTime: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            midiDriver.write(byteArrayOf(0x90.toByte(), note.toByte(), 0x7F.toByte()))

            val fadeSteps = 10
            val delayTime = fadeOutTime / fadeSteps
            var velocity = 0x7F

            delay(duration - fadeOutTime)

            for (i in 0 until fadeSteps) {
                velocity -= (0x7F / fadeSteps)
                if (velocity < 0) velocity = 0
                midiDriver.write(byteArrayOf(0x90.toByte(), note.toByte(), velocity.toByte()))
                delay(delayTime)
            }

            stopNote(note)
        }
    }

    fun stopNote(note: Int) {
        midiDriver.write(byteArrayOf(0x80.toByte(), note.toByte(), 0x00.toByte()))
    }

    override fun play(melody: Melody) {
        val clef = melody.clef
        val bln = clef.bottomLineNote() ?: return
        val libraryOffset = 60
        CoroutineScope(Dispatchers.Default).launch {
            for (measure in melody.measures) {
                for (symbol in measure.symbols) {
                    if(symbol is Note){
                        val note: Note = symbol
                        val value = (bln + note.stage()).pitch()
                        playNoteWithFadeOut(value + libraryOffset, 1000, 700)
                    }
                }
            }
        }

    }


}