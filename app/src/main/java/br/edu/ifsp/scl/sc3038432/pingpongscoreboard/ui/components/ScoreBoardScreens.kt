package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.theme.PingPongScoreBoardTheme
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.viewmodel.ScoreBoardFlowViewModel
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.viewmodel.ScoreBoardStateViewModel

@Composable
private fun ScoreBoardContent(
    pointsA: Int,
    pointsB: Int,
    onPointA: () -> Unit,
    onPointB: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically)
    ) {
        Score( pointsPlayer1 = pointsA, pointsPlayer2 = pointsB )
        PointButton( onPointA = onPointA, onPointB = onPointB )
        ResetScoreButton( onReset = onReset )
    }
}

// remember
@Composable
fun ScoreBoardRememberScreen(modifier: Modifier = Modifier){
    var pointsA by remember { mutableIntStateOf(0) }
    var pointsB by remember { mutableIntStateOf(0) }

    ScoreBoardContent(
        pointsA = pointsA,
        pointsB = pointsB,
        onPointA = { pointsA++ },
        onPointB = { pointsB++ },
        onReset = { pointsA = 0; pointsB = 0 },
        modifier = modifier
    )
}

// ViewModel + mutableStateOf
@Composable
fun ScoreBoardStateScreen(
    modifier: Modifier = Modifier,
    vm: ScoreBoardStateViewModel = viewModel()
) {
    ScoreBoardContent(
        pointsA = vm.pointsA,
        pointsB = vm.pointsB,
        onPointA = vm::addPointA,
        onPointB = vm::addPointB,
        onReset = vm::reset,
        modifier = modifier
    )
}

// ViewModel + StateFlow
@Composable
fun ScoreBoardFlowScreen(
    modifier: Modifier = Modifier,
    vm: ScoreBoardFlowViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    ScoreBoardContent(
        pointsA = uiState.pointsA,
        pointsB = uiState.pointsB,
        onPointA = vm::addPointA,
        onPointB = vm::addPointB,
        onReset = vm::reset,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun ScoreBoardPreview(){
    PingPongScoreBoardTheme {
        ScoreBoardRememberScreen()
    }
}