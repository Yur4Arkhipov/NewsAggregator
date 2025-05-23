package com.example.newsaggregator.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.ui.news.NewsListScreen
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.theguardian.com")
    .addConverterFactory(
        XML.asConverterFactory(
            "application/xml; charset=UTF8".toMediaType()
        )
    ).build()

private val guardian = retrofit.create(RssFeed::class.java)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAggregatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsListScreen(
                        modifier = Modifier.padding(innerPadding),
                        guardian
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    text: String,
    modifier: Modifier = Modifier,
    feed: RssFeed,
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                Log.d("happy", "done")
                scope.launch {
                    val r = feed.getRss()
                    r.channel.items.forEach {
                        Log.d("title", it.title)
                        Log.d("link", it.link)
                        Log.d("guid", it.guid)
                        Log.d("dcDate", it.dcDate)
                        Log.d("pubDate", it.pubDate)
                    }
                }
            },
        ) {
            Text(
                text = text,
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAggregatorTheme {
        Greeting(
            text = "Press me!",
            feed = guardian
        )
    }
}