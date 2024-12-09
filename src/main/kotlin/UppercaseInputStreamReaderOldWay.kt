class UppercaseInputStreamReaderOldWay(private val innerStream: InputStreamReader<Char?>) : InputStreamReader<Char?> {
    override fun read(): Char? {
        val char = innerStream.read()
        return char?.uppercaseChar()
    }

    override fun skip(amount: Int) = innerStream.skip(amount)

    override fun reset() = innerStream.reset()

    override fun available(): Int = innerStream.available()

    override fun close() = innerStream.close()
}

fun main() {
    val charStreamReader = CharStreamReader("some text")
    val stream = UppercaseInputStreamReaderOldWay(charStreamReader)

    stream.use {
        while (stream.available() != 0) {
            println(stream.read())
        }
    }
}