package br.edu.ifsp.scl.sc3038432.pingpongscoreboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.PointButton
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.ResetScoreButton
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.Score
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.TitleBox
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.theme.PingPongScoreBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PingPongScoreBoardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PingPongScoreBoard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PingPongScoreBoard(modifier: Modifier = Modifier) {

    var pointsA by remember { mutableIntStateOf(0) }
    var pointsB by remember { mutableIntStateOf(0) }

    TitleBox()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically)) {

        Score( pointsPlayer1 = pointsA, pointsPlayer2 = pointsB )

        PointButton( onPointA = {pointsA ++}, onPointB = {pointsB ++})

        ResetScoreButton {
            pointsA = 0
            pointsB = 0
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PingPongScoreBoardPreview() {
    PingPongScoreBoardTheme {
        PingPongScoreBoard()
    }
}