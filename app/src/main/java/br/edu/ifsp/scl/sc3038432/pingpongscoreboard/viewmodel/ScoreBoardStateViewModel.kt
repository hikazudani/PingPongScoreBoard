package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class ScoreBoardStateViewModel : ViewModel() {

    var pointsA by mutableIntStateOf(0)
        private set

    var pointsB by mutableIntStateOf(0)
        private set

    fun addPointA() { pointsA++ }

    fun addPointB() { pointsB++ }

    fun reset() {
        pointsA = 0
        pointsB = 0
    }
}