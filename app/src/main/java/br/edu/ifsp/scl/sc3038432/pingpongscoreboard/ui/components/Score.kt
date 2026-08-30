package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Score(
    pointsPlayer1: Int,
    pointsPlayer2: Int,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PlayerPanel( name = "Player 1", points = pointsPlayer1, modifier = Modifier.weight(1f) )
        PlayerPanel( name = "Player 2", points = pointsPlayer2, modifier = Modifier.weight(1f) )
    }
}

@Preview
@Composable
fun ScorePreview(){
    Score( pointsPlayer1 = 42, pointsPlayer2 = 41)
}