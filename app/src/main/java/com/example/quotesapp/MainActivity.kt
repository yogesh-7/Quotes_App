package com.example.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quotesapp.screens.QuoteListScreen
import com.example.quotesapp.screens.QuotesDetail
import com.example.quotesapp.ui.theme.DataManger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            DataManger.loadAssetsFromFile(applicationContext)
        }
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    if (DataManger.isDataLoaded.value) {
        if (DataManger.currentPage.value == Pages.LISTING) {
            QuoteListScreen(data = DataManger.data) {
                DataManger.switchPages(it)
            }
        } else {
            DataManger.currentQuote?.let { QuotesDetail(quote = it) }
        }

    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)

        ) {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.h6
            )
        }
    }
}

enum class Pages {
    LISTING,
    DETAIL
}
