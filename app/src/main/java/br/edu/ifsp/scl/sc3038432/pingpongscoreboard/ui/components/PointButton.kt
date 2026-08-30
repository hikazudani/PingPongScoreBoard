package br.edu.ifsp.scl.sc3038432.pingpongscoreboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PointButton(
    onPointA: () -> Unit,
    onPointB: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onPointA,
            modifier = Modifier.weight(1f)
        ) {
            Text("+ 1")
        }

        Button(
            onClick = onPointB,
            modifier = Modifier.weight(1f)
        ) {
            Text("+ 1")
        }
    }
}

@Composable
@Preview
private fun PointButtonPreview(){
    PointButton({}, {})
}