import java.lang.Exception
import java.util.Scanner

class ExecutionContext(
    private val instructions: List<Char>
) {
    private val memory = mutableListOf<Int>()
    private var pointer: Int = 0

    fun start() {
        var i = 0
        while (i < instructions.size) {
            val instruction = instructions[i]
            i = doInstruction(instruction, i)

            i++
        }
    }

    private fun doInstruction(instruction: Char, currentIndex: Int): Int {
        if(instruction == '<') {
            pointer--
        } else if(instruction == '>') {
            pointer++
        } else if(instruction == '+') {
            setValue(getValue().inc())
        } else if(instruction == '-') {
            setValue(getValue().dec())
        } else if(instruction == '.') {
            print(getValue().toChar())
        } else if(instruction == ',') {
            setValue(Scanner(System.`in`).nextInt())
        } else if(instruction == '[') {
            if(getValue() == 0) return getMatchingClosedBracket(currentIndex) - 1
        } else if(instruction == ']') {
            if(getValue() != 0) return getMatchingOpenBracket(currentIndex) - 1
        }

        return currentIndex
    }

    private fun setValue(value: Int) {
        if(pointer < memory.size) {
            memory[pointer] = value
            return
        }

        (memory.size..<pointer).forEach {
            memory.add(it, 0)
        }
        memory.add(pointer, value)
    }
    private fun getValue(): Int {
        return memory.getOrElse(pointer) { 0 }
    }

    fun getMatchingClosedBracket(startIndex: Int): Int {
        val knownBrackets = mutableListOf<Int>()

        instructions.subList(startIndex, instructions.size).forEachIndexed { i, char ->
            if(char == ']' && knownBrackets.size % 2 != 0) {
                return startIndex + i
            }

            if(char == '[' || char == ']') {
                knownBrackets.add(i)
            }
        }
        throw Exception("Bracket at $startIndex has no corresponding closing bracket")
    }
    fun getMatchingOpenBracket(startIndex: Int): Int {
        val knownBrackets = mutableListOf<Int>()

        instructions.subList(0, startIndex).reversed().forEachIndexed { i, char ->
            if(char == '[' && knownBrackets.size % 2 == 0) {
                return startIndex - i - 1
            }

            if(char == '[' || char == ']') {
                knownBrackets.add(i)
            }
        }
        throw Exception("Bracket at $startIndex has no corresponding opening bracket")
    }
}