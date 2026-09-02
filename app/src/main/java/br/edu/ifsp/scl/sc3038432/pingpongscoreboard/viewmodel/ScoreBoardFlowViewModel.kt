package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.model.ScoreUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScoreBoardFlowViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScoreUiState())
    val uiState: StateFlow<ScoreUiState> = _uiState.asStateFlow()

    fun addPointA() = _uiState.update { it.copy(pointsA = it.pointsA + 1) }
    fun addPointB() = _uiState.update { it.copy(pointsB = it.pointsB + 1) }
    fun reset() = _uiState.update { ScoreUiState() }
}