package comisapolive.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import comisapolive.app.model.Genre
import comisapolive.app.model.Liver
import comisapolive.app.model.Platform
import comisapolive.app.ui.components.CustomTabButton
import comisapolive.app.ui.components.Header
import comisapolive.app.ui.components.LiverCard
import comisapolive.app.ui.components.LiverDetailsModal
import comisapolive.app.ui.components.PlatformSelectionView
import comisapolive.app.ui.screens.content.ContentTab
import comisapolive.app.ui.screens.content.ContentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(
    viewModel: ContentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(uiState.isLoading)
    var selectedLiver by remember { mutableStateOf<Liver?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(onSearchClick = { /* Navigate to search will be handled by parent */ })

        // iOS同等のカスタムタブエリア
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f))
                .padding(top = 25.dp, bottom = 15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // ジャンルから探すタブ（iOS同等のTabButton）
                CustomTabButton(
                    text = "ジャンルから探す",
                    imageName = "tabicon1",
                    backgroundColor = Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f),
                    isSelected = uiState.selectedTab == ContentTab.GENRE,
                    onClick = { viewModel.selectTab(ContentTab.GENRE) },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // 配信アプリから探すタブ（iOS同等のTabButton）
                CustomTabButton(
                    text = "配信アプリから探す",
                    imageName = "tabicon2",
                    backgroundColor = Color(0xFF800080), // purple
                    isSelected = uiState.selectedTab == ContentTab.PLATFORM,
                    onClick = { viewModel.selectTab(ContentTab.PLATFORM) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.refresh() }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp),  // iOS同等のフルスクリーンレイアウト
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {

                // iOS同等のコンテンツエリア
                when (uiState.selectedTab) {
                    ContentTab.GENRE -> {
                        // ジャンルから探す（iOS同等のグリッドレイアウト）
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .padding(20.dp)
                            ) {
                                GenreGrid(
                                    selectedGenre = uiState.selectedGenre,
                                    onGenreSelected = { viewModel.selectGenre(it) }
                                )
                            }
                        }
                    }
                    ContentTab.PLATFORM -> {
                        // 配信アプリから探す（iOS同等のプラットフォーム選択）
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                            ) {
                                PlatformSelectionView(
                                    platforms = uiState.availablePlatforms.map { platformName ->
                                        comisapolive.app.ui.components.Platform(
                                            name = platformName,
                                            imageName = getImageNameForPlatform(platformName),
                                            displayName = platformName,
                                            backgroundColor = Color(0xFF800080)
                                        )
                                    },
                                    onPlatformSelected = { platform -> viewModel.selectPlatform(platform.name) }
                                )
                            }
                        }
                    }
                }

                // Clear selection button
                if (uiState.selectedGenre != null || uiState.selectedPlatform != null) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = when {
                                    uiState.selectedGenre != null -> "${uiState.selectedGenre} (${uiState.filteredLivers.size}件)"
                                    uiState.selectedPlatform != null -> "${uiState.selectedPlatform} (${uiState.filteredLivers.size}件)"
                                    else -> ""
                                },
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            TextButton(onClick = { viewModel.clearSelection() }) {
                                Text("クリア", fontSize = 12.sp)
                            }
                        }
                    }
                }

                // Filtered Results
                if (uiState.filteredLivers.isNotEmpty()) {
                    items(uiState.filteredLivers) { liver ->
                        LiverCard(
                            liver = liver,
                            onClick = { selectedLiver = it },
                            isCircular = false
                        )
                    }
                } else if (uiState.selectedGenre != null || uiState.selectedPlatform != null) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "該当するライバーが見つかりませんでした",
                                fontSize = 16.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                // Loading indicator
                if (uiState.isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
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
            // Show error snackbar
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
}

@Composable
private fun GenreGrid(
    selectedGenre: Genre?,
    onGenreSelected: (Genre) -> Unit
) {
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.height(600.dp) // Fixed height for grid
    ) {
        items(Genre.values()) { genre ->
            GenreItem(
                genre = genre,
                isSelected = selectedGenre == genre,
                onClick = { onGenreSelected(genre) }
            )
        }
    }
}

@Composable
private fun GenreItem(
    genre: Genre,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val iconResId = context.resources.getIdentifier(
                genre.iconResource,
                "drawable",
                context.packageName
            )

            if (iconResId != 0) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = genre.displayName,
                    modifier = Modifier.size(96.dp)
                )
            } else {
                // Fallback icon
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = genre.displayName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Composable
private fun PlatformGrid(
    selectedPlatform: Platform?,
    onPlatformSelected: (Platform) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.height(600.dp) // Fixed height for grid
    ) {
        items(Platform.values()) { platform ->
            PlatformItem(
                platform = platform,
                isSelected = selectedPlatform == platform,
                onClick = { onPlatformSelected(platform) }
            )
        }
    }
}

// プラットフォーム名から適切な画像名を取得する関数
private fun getImageNameForPlatform(platformName: String): String {
    return when (platformName.lowercase()) {
        "youtube" -> "youtube"
        "tiktok" -> "tiktok"
        "pococha" -> "pococha"
        "twitch" -> "twitch"
        "ツイキャス", "twicas" -> "twicas"
        "ニコニコ", "niconico" -> "niconico"
        "mixch" -> "mixch"
        "iriam" -> "iriam"
        "hakuna" -> "hakuna"
        "bigo live", "bigo" -> "bigo"
        "17live" -> "17live"
        else -> "youtube" // デフォルト
    }
}

@Composable
private fun PlatformItem(
    platform: Platform,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val iconResId = context.resources.getIdentifier(
                platform.iconResource,
                "drawable",
                context.packageName
            )

            if (iconResId != 0) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = platform.displayName,
                    modifier = Modifier.size(32.dp)
                )
            } else {
                // Fallback icon
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = platform.displayName,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}