package comisapolive.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import comisapolive.app.model.Liver
import comisapolive.app.ui.components.LiverDetailsModal
import comisapolive.app.ui.screens.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    var selectedLiver by remember { mutableStateOf<Liver?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable { focusManager.clearFocus() }  // iOS同等のフォーカス制御
    ) {
        // iOS同等の検索バーエリア
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            Text(
                text = "ライバー検索",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            OutlinedTextField(
                value = uiState.query,
                onValueChange = {
                    viewModel.updateQuery(it)
                    viewModel.setTextFieldFocus(true)
                },
                placeholder = {
                    Text(
                        "ライバー名、カテゴリ、性別などで検索",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "検索",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    if (uiState.query.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateQuery("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "クリア")
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (uiState.query.isNotEmpty()) {
                            viewModel.search()
                        }
                        viewModel.setTextFieldFocus(false)
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )
        }

        // iOS同等の検索履歴表示
        if (uiState.isTextFieldFocused && uiState.query.isEmpty() && uiState.searchHistory.isNotEmpty()) {
            SearchHistoryView(
                searchHistory = uiState.searchHistory,
                onHistoryItemClick = { historyItem ->
                    viewModel.selectHistoryItem(historyItem)
                    viewModel.setTextFieldFocus(false)
                    focusManager.clearFocus()
                },
                onClearHistory = { viewModel.clearSearchHistory() },
                onRemoveHistoryItem = { /* 実装予定 */ }
            )
        } else {
            // iOS同等のメインコンテンツ
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    uiState.query.isEmpty() -> {
                        // iOS同等の検索プロンプト
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp),
                                tint = Color.Gray.copy(alpha = 0.5f)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "検索したいキーワードを入力してください",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "ライバー名、カテゴリ、性別などで検索できます",
                                fontSize = 14.sp,
                                color = Color.Gray.copy(alpha = 0.7f)
                            )
                        }
                    }
                    uiState.searchResults.isEmpty() && uiState.hasSearched -> {
                        // iOS同等の検索結果なし
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp),
                                tint = Color.Gray.copy(alpha = 0.5f)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "検索結果が見つかりませんでした",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "\"${uiState.query}\" に一致するライバーはいません",
                                fontSize = 14.sp,
                                color = Color.Gray.copy(alpha = 0.7f)
                            )
                        }
                    }
                    else -> {
                        // iOS同等の検索結果一覧
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            item {
                                Text(
                                    text = "検索結果: ${uiState.searchResults.size}件",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Gray
                                )
                            }

                            items(uiState.searchResults) { liver ->
                                SearchResultCard(
                                    liver = liver,
                                    onClick = { selectedLiver = liver }
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(30.dp))
                            }
                        }
                    }
                }
            }
        }

        // エラーメッセージ表示
        uiState.errorMessage?.let { error ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = error,
                    modifier = Modifier.padding(12.dp),
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }

    }

    // ライバー詳細モーダル（iOS同等）
    selectedLiver?.let { liver ->
        LiverDetailsModal(
            liver = liver,
            onDismiss = { selectedLiver = null }
        )
    }
}

@Composable
private fun SearchHistoryView(
    searchHistory: List<String>,
    onHistoryItemClick: (String) -> Unit,
    onClearHistory: () -> Unit,
    onRemoveHistoryItem: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "検索履歴",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            TextButton(onClick = onClearHistory) {
                Text(
                    text = "クリア",
                    fontSize = 14.sp,
                    color = Color.Blue
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        searchHistory.forEach { historyItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onHistoryItemClick(historyItem) }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = historyItem,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { onRemoveHistoryItem(historyItem) }) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "削除",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Gray
                    )
                }
            }
            Divider(color = Color.Gray.copy(alpha = 0.2f))
        }
    }
}

@Composable
private fun SearchResultCard(
    liver: Liver,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.Top
        ) {
            // プロフィール画像
            AsyncImage(
                model = liver.fullImageURL,
                contentDescription = liver.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_menu_gallery)
            )

            Spacer(modifier = Modifier.width(15.dp))

            // ライバー情報
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = liver.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Text(
                        text = liver.platform,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                Color(red = 95f/255f, green = 211f/255f, blue = 198f/255f),
                                RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = liver.followerDisplayText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }

                // カテゴリ情報
                liver.categories?.let { categories ->
                    if (categories.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = categories.joinToString(" • "),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            maxLines = 2
                        )
                    }
                }

                // 性別情報
                liver.profileInfo?.gender?.let { gender ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "性別: $gender",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}