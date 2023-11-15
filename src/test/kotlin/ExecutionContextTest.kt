import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.Exception

class ExecutionContextTest {

    @Test
    fun getMatchingClosedBracket() {
        assertEquals(
            2,
            ExecutionContext(listOf('[', '.', ']', '.')).getMatchingClosedBracket(0)
        )
        assertEquals(
            10,
            ExecutionContext(listOf('[', '.', ']', '.', '[', '.', '[', ',', ',', ']', ']', '.')).getMatchingClosedBracket(4)
        )
        assertThrows(Exception::class.java) {
            ExecutionContext(listOf('[', '.', '-', '.')).getMatchingClosedBracket(0)
        }
    }
    @Test
    fun getMatchingOpenBracket() {
        assertEquals(
            0,
            ExecutionContext(listOf('[', '.', ']', '.')).getMatchingOpenBracket(2)
        )
        assertEquals(
            4,
            ExecutionContext(listOf('[', '.', ']', '.', '[', '.', '[', ',', ',', ']', ']', '.')).getMatchingOpenBracket(10)
        )
        assertThrows(Exception::class.java) {
            ExecutionContext(listOf('[', '.', '-', '.')).getMatchingOpenBracket(0)
        }
    }
}