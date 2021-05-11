package component

import androidx.compose.runtime.Composable
import androidx.compose.web.elements.Button
import androidx.compose.web.elements.Text
import model.Player

@Composable
fun Square(
    player: Player?,
    onClick: () -> Unit
) {
    Button(attrs = {
        onClick { onClick() }
        classes("square")
    }) {
        Text(player?.toString() ?: "")
    }
}