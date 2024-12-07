class UppercaseInputStream(private val innerStream: InputStream<Char?>) : InputStream<Char?> by innerStream {
    override fun read(): Char? {
        val char = innerStream.read()
        return char?.uppercaseChar()
    }
}

fun main() {
    val charStreamReader = CharStreamReader("some text")
    val stream = UppercaseInputStream(charStreamReader)

    while (stream.available() != 0) {
        println(stream.read())
    }
}