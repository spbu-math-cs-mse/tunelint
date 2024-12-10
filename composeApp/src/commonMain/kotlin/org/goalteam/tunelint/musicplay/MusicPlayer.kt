package org.goalteam.tunelint.musicplay

import org.goalteam.tunelint.model.core.Melody

interface MusicPlayer {
    fun play(melody: Melody)
}