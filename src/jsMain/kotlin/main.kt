import androidx.compose.web.renderComposable
import component.Game

fun main() {
    renderComposable(rootElementId = "root") {
        Game()
    }
}