package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.model.ScoreUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScoreBoardSavedViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private companion object {
        const val SCORE_KEY = "score"
    }

    private val _uiState = MutableStateFlow(
        savedStateHandle[SCORE_KEY] ?: ScoreUiState()
    )
    val uiState: StateFlow<ScoreUiState> = _uiState.asStateFlow()

    fun addPointA() {
        _uiState.update { it.copy(pointsA = it.pointsA + 1) }
        persist()
    }

    fun addPointB() {
        _uiState.update { it.copy(pointsB = it.pointsB +1 ) }
        persist()
    }

    fun reset() {
        _uiState.update { ScoreUiState() }
        persist()
    }

    private fun persist() {
        savedStateHandle[SCORE_KEY] = _uiState.value
    }

}