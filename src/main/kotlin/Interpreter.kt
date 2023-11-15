import java.nio.file.Path
import kotlin.io.path.readText

class Interpreter {
    companion object {
        private val validTokens = listOf('>', '<', '+', '-', '.', ',', '[', ']')

        fun execute(file: Path) {
            val instructions = getInstructions(file)

            ExecutionContext(instructions).start()
        }

        private fun getInstructions(file: Path): List<Char> {
            return file.readText().toCharArray().toList().filter {
                validTokens.contains(it)
            }
        }
    }
}