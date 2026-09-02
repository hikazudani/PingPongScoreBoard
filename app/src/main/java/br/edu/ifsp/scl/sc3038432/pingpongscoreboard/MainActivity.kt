package br.edu.ifsp.scl.sc3038432.pingpongscoreboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.ScoreBoardFlowScreen
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.ScoreBoardRememberScreen
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.ScoreBoardStateScreen
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components.TitleBox
import br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.theme.PingPongScoreBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PingPongScoreBoardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        TitleBox()
                        //ScoreBoardRememberScreen() //remember
                        //ScoreBoardStateScreen() // ViewModel + mutableStateOf
                        ScoreBoardFlowScreen() // ViewModel + StateFlow
                    }
                }
            }
        }
    }
}
