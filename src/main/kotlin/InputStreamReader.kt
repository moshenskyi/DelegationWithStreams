import java.io.Closeable

interface InputStreamReader<T> : Closeable {
    fun read(): T
    fun skip(amount: Int)
    fun reset()
    fun available(): Int
}

class CharStreamReader(private val input: String) : InputStreamReader<Char?> {
    private var currPos = 0
    private var closed = false

    override fun read(): Char? {
        ensureOpen()

        val length = input.length
        if (length == 0 || currPos == length) return null

        return input[currPos++]
    }

    override fun skip(amount: Int) {
        ensureOpen()

        val newPos = amount + currPos
        require(newPos < input.length) { "Skipped amount exceeds input length" }

        currPos = newPos
    }

    override fun reset() {
        ensureOpen()
        currPos = 0
    }

    override fun available(): Int = if (closed) 0 else input.length - currPos

    override fun close() {
        closed = true
    }

    private fun ensureOpen() = check(closed.not()) { "Stream is already closed" }
}
