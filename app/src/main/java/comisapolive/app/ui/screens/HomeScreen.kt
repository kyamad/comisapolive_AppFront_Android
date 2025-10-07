package comisapolive.app.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import comisapolive.app.model.Liver
import comisapolive.app.ui.components.*
import comisapolive.app.ui.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(uiState.isLoading)

    var selectedLiver by remember { mutableStateOf<Liver?>(null) }
    var selectedArticleUrl by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(onSearchClick = onSearchClick)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.refresh() }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp),  // iOS同等のフルスクリーンレイアウト
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                // iOS同等の広告エリア
                item {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(Color.Black)
                                .offset(y = (-2).dp)
                        )

                        AdMobBanner()

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(Color.Black)
                                .offset(y = 2.dp)
                        )
                    }
                }

                // New Livers Section（iOS同等のスタイル）
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f))
                            .padding(bottom = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp)
                                .padding(top = 40.dp, bottom = 15.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.Start
                            ) {
                                // iOS同等のNewLiverテキストスタイル
                                Text(
                                    text = "Newライバー",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        // iOS同等のNewLiverCarousel
                        if (uiState.isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.White)
                            }
                        } else if (uiState.newLivers.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "データを取得できませんでした",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        text = "ライバー数: ${uiState.newLivers.size}",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        } else {
                            NewLiverCarousel(
                                livers = uiState.newLivers,
                                onLiverTap = { liver ->
                                    selectedLiver = liver
                                }
                            )
                        }
                    }
                }

                // iOS同等の記事セクション
                item {
                    NewArticles(
                        articles = uiState.articles,
                        onArticleClick = { article ->
                            selectedArticleUrl = article.url
                        }
                    )
                }

                // 再度広告エリア（iOS同等）
                item {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(Color.Black)
                                .offset(y = (-2).dp)
                        )

                        AdMobBanner()

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(Color.Black)
                                .offset(y = 2.dp)
                        )
                    }
                }

                // iOS同等のコラボOKライバーセクション
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f).copy(alpha = 0.1f))
                            .padding(bottom = 100.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp)
                                .padding(top = 25.dp, bottom = 15.dp)
                        ) {
                            Text(
                                text = "コラボ配信OKライバー",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        // iOS同等のColaboLiverCarousel
                        if (uiState.isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        } else if (uiState.collaborationLivers.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "データを取得できませんでした",
                                        color = Color.Black,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        text = "ライバー数: ${uiState.collaborationLivers.size}",
                                        color = Color.Gray,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        } else {
                            ColaboLiverCarousel(
                                livers = uiState.collaborationLivers,
                                onLiverTap = { liver ->
                                    selectedLiver = liver
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                            )
                        }

                        // iOS同等の最終境界線
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(Color.Black)
                                .offset(y = 80.dp)
                        )
                    }
                }

                // Bottom spacing
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

    // Error handling
    uiState.errorMessage?.let { error ->
        LaunchedEffect(error) {
            // Show error snackbar or dialog
            viewModel.clearError()
        }
    }

    // Liver details modal
    selectedLiver?.let { liver ->
        LiverDetailsModal(
            liver = liver,
            onDismiss = { selectedLiver = null }
        )
    }

    selectedArticleUrl?.let { url ->
        ArticleWebViewDialog(
            url = url,
            onDismiss = { selectedArticleUrl = null }
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun ArticleWebViewDialog(
    url: String,
    onDismiss: () -> Unit
) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 6.dp
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "記事を表示",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    TextButton(onClick = onDismiss) {
                        Text("閉じる")
                    }
                }

                Divider(color = Color.Gray.copy(alpha = 0.2f))

                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    request: android.webkit.WebResourceRequest?
                                ): Boolean = false
                            }
                            loadUrl(url)
                        }
                    },
                    update = { webView ->
                        if (webView.url != url) {
                            webView.loadUrl(url)
                        }
                    }
                )
            }
        }
    }
}
