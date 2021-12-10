package component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import model.GameState
import model.Player
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Ol
import org.jetbrains.compose.web.dom.Text

@Composable
fun Game() {
    val (state, setState) = remember {
        val initialBoard = List(9) {
            null
        }
        val initialGameState = GameState.Playing(Player.X, initialBoard)
        mutableStateOf(State(listOf(initialGameState), 0))
    }
    val history = state.gameStateHistory.slice(0..state.currentStep)
    val gameState = history.last()
    val statusText = when (gameState) {
        is GameState.Playing -> {
            "Next Player: ${gameState.turn}"
        }
        is GameState.End -> {
            val winner = gameState.winner
            if (winner == null) {
                "Draw"
            } else {
                "Winner: $winner"
            }
        }
    }
    Div(attrs = {
        classes("game")
    }) {
        Div {
            Board(gameState) { i ->
                if (gameState.board[i] != null) {
                    return@Board
                }
                val playing = gameState as? GameState.Playing
                if (playing != null) {
                    val nextHistory = history + playing.calculateNextState(i)
                    setState(
                        state.copy(
                            gameStateHistory = nextHistory,
                            currentStep = nextHistory.lastIndex
                        )
                    )
                }
            }
        }
        Div(attrs = {
            classes("game-info")
        }) {
            Text(statusText)
            Ol {
                state.gameStateHistory.forEachIndexed { i, _ ->
                    Li {
                        Button(attrs = {
                            onClick {
                                setState(
                                    state.copy(
                                        currentStep = i
                                    )
                                )
                            }
                        }) {
                            Text("Go to move #$i")
                        }
                    }
                }
            }
        }
    }
}

private data class State(
    val gameStateHistory: List<GameState>,
    val currentStep: Int
)
