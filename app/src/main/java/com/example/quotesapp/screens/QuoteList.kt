package com.example.quotesapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.quotesapp.models.Quote


@Composable
fun QuoteList(list: Array<Quote>, onClick: (quote: Quote) -> Unit) {

    LazyColumn(content = {
        items(list) {
            QuotesListItem(quote = it, onClick)
        }
    })
}