import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class InterpreterTest {

    @Test
    fun execute() {
        Interpreter.execute(Path("src/test/resources/code.bf"))
    }
}