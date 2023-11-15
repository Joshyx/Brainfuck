import kotlin.io.path.Path

fun main(args: Array<String>) {
    Interpreter.execute(Path(args.getOrElse(0) {
        throw IllegalArgumentException("A file needs to be given as the first parameter")
    }))
}