package ihor.kalaur.topmovieapp.util

class DataParser {
    fun parseYearFromResponse(str: String): String = str.substring(0,4)
}