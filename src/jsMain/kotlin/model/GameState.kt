package model

sealed class GameState {
    abstract val board: List<Player?>

    data class Playing(
        val turn: Player,
        override val board: List<Player?>
    ) : GameState() {
        fun calculateNextState(nextMove: Int): GameState {
            val nextBoard = board.toMutableList().also {
                it[nextMove] = turn
            }
            val lines = listOf(
                listOf(0, 1, 2),
                listOf(3, 4, 5),
                listOf(6, 7, 8),
                listOf(0, 3, 6),
                listOf(1, 4, 7),
                listOf(2, 5, 8),
                listOf(0, 4, 8),
                listOf(2, 4, 6)
            )
            val winner = lines.firstOrNull {
                val (a, b, c) = it
                nextBoard[a] != null &&
                    nextBoard[a] == nextBoard[b] &&
                    nextBoard[a] == nextBoard[c]
            }?.let {
                nextBoard[it[0]]
            }
            return if (winner != null || nextBoard.all { it != null }) {
                End(winner, nextBoard)
            } else {
                Playing(turn.nextPlayer(), nextBoard)
            }
        }
    }

    data class End(
        val winner: Player?,
        override val board: List<Player?>
    ) : GameState()
}
