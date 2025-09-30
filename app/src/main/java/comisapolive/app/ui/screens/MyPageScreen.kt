package comisapolive.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import comisapolive.app.ui.components.Header

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(onSearchClick = { /* Navigate to search will be handled by parent */ })

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // App Info Section
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "コミサポライブ",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Version 2.0.0",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "ライバーとファンをつなぐ\nプラットフォーム",
                            fontSize = 16.sp,
                            color = Color.Black,
                            lineHeight = 24.sp
                        )
                    }
                }
            }

            // Menu Items
            item {
                MenuSection(
                    title = "サービス情報",
                    items = listOf(
                        MenuItem("公式サイト", "https://www.comisapolive.com"),
                        MenuItem("利用規約", "https://www.comisapolive.com/terms"),
                        MenuItem("プライバシーポリシー", "https://www.comisapolive.com/privacy"),
                        MenuItem("お問い合わせ", "https://www.comisapolive.com/contact")
                    ),
                    onItemClick = { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
            }

            item {
                MenuSection(
                    title = "機能",
                    items = listOf(
                        MenuItem("ライバー検索", null),
                        MenuItem("カテゴリ別検索", null),
                        MenuItem("プラットフォーム別検索", null),
                        MenuItem("コラボ募集", null)
                    ),
                    onItemClick = { /* Navigation will be handled by parent */ }
                )
            }

            item {
                MenuSection(
                    title = "その他",
                    items = listOf(
                        MenuItem("アプリについて", null),
                        MenuItem("レビューを書く", "market://details?id=jp.comisapolive.app"),
                        MenuItem("バグ報告", "https://www.comisapolive.com/bug-report")
                    ),
                    onItemClick = { url ->
                        url?.let {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                            context.startActivity(intent)
                        }
                    }
                )
            }

            // Copyright
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "© 2024 コミサポライブ\nAll rights reserved.",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        lineHeight = 18.sp
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

@Composable
private fun MenuSection(
    title: String,
    items: List<MenuItem>,
    onItemClick: (String?) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            items.forEachIndexed { index, item ->
                MenuItemRow(
                    item = item,
                    onClick = { onItemClick(item.url) },
                    showDivider = index < items.size - 1
                )
            }
        }
    }
}

@Composable
private fun MenuItemRow(
    item: MenuItem,
    onClick: () -> Unit,
    showDivider: Boolean
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                color = Color.Black
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "移動",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }

        if (showDivider) {
            Divider(
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.2f)
            )
        }
    }
}

private data class MenuItem(
    val title: String,
    val url: String?
)