package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ResetScoreButton(
    onReset: () -> Unit
) {
    Button(
        onClick = onReset
    ) {
        Text("Reset Score")
    }
}

@Preview
@Composable
private fun ResetScoreButtonPreview() {
    ResetScoreButton {}
}