package ihor.kalaur.topmovieapp.util

import javax.inject.Inject

class DataParser @Inject constructor(){
    fun parseYearFromResponse(str: String): String = str.substring(0,4)
}