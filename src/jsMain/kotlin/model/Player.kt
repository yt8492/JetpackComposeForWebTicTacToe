package model

enum class Player {
    X, O;

    fun nextPlayer(): Player {
        return when (this) {
            X -> O
            O -> X
        }
    }
}