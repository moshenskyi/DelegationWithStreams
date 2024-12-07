import java.io.Closeable

interface InputStream<T> : Closeable {
    fun read(): T
    fun skip(amount: Int)
    fun reset()
    fun available(): Int
}

class CharStreamReader(private val input: String) : InputStream<Char?> {
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
        if (newPos >= input.length) throw IllegalArgumentException("Skipped amount exceeds input length")

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

    private fun ensureOpen() {
        if (closed) throw IllegalStateException("Stream is already closed")
    }
}
