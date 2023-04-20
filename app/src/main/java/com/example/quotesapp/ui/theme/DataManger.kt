package com.example.quotesapp.ui.theme

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotesapp.Pages
import com.example.quotesapp.models.Quote
import com.google.gson.Gson

object DataManger {

    var data = emptyArray<Quote>()
    var isDataLoaded = mutableStateOf(false)

    var currentQuote: Quote? = null

    var currentPage = mutableStateOf(Pages.LISTING)

    fun loadAssetsFromFile(context: Context) {
        val inputStream = context.assets.open("quotes.json")
        //val size: Int = inputStream.available()
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        //val gson = Gson()
        data = Gson().fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true

    }

    fun switchPages(quote: Quote?) {
        if (currentPage.value == Pages.LISTING) {
            currentQuote = quote
            currentPage.value = Pages.DETAIL
        } else {
            currentPage.value = Pages.LISTING
        }
    }

}