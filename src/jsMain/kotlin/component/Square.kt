package component

import androidx.compose.runtime.Composable
import model.Player
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

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