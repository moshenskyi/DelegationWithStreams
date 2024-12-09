class UppercaseInputStreamReader(private val innerStream: InputStreamReader<Char?>) : InputStreamReader<Char?> by innerStream {
    override fun read(): Char? {
        val char = innerStream.read()
        return char?.uppercaseChar()
    }
}

fun main() {
    val charStreamReader = CharStreamReader("some text")
    val stream = UppercaseInputStreamReader(charStreamReader)

    stream.use {
        while (stream.available() != 0) {
            println(stream.read())
        }
    }
}