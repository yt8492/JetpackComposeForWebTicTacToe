package component

import androidx.compose.runtime.Composable
import androidx.compose.web.elements.Div
import model.GameState

@Composable
fun Board(
    state: GameState,
    onClick: (Int) -> Unit
) {
    (0..2).forEach { i ->
        Div(attrs = {
            classes("board-row")
        }) {
            (0..2).forEach { j ->
                val index = i * 3 + j
                Square(state.board[index]) {
                    onClick(index)
                }
            }
        }
    }
}
