package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScoreUiState(
    val pointsA: Int = 0,
    val pointsB: Int = 0
) : Parcelable