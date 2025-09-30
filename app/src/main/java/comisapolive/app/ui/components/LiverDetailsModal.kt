@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@file:Suppress("DEPRECATION", "EXPERIMENTAL_API_USAGE", "OPT_IN_USAGE")

package comisapolive.app.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import comisapolive.app.model.Liver
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun LiverDetailsModal(
    liver: Liver,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    // iOS同等のアニメーション状態管理
    var isVisible by remember { mutableStateOf(false) }
    var dragOffset by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isVisible) 0.6f else 0f,
        animationSpec = tween(300, easing = EaseOutCubic),
        label = "backgroundAlpha"
    )

    val modalScale by animateFloatAsState(
        targetValue = if (isVisible && !isDragging) 1f else if (isDragging) 0.95f else 0.85f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "modalScale"
    )

    val modalOffset by animateIntOffsetAsState(
        targetValue = if (isVisible) {
            IntOffset(0, dragOffset.roundToInt())
        } else {
            IntOffset(0, with(density) { 1000.dp.roundToPx() })
        },
        animationSpec = if (isDragging) {
            tween(0) // No animation while dragging
        } else {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        },
        label = "modalOffset"
    )

    // モーダル表示時のアニメーション開始
    LaunchedEffect(Unit) {
        isVisible = true
    }

    // 閉じる処理
    val handleDismiss: () -> Unit = {
        isVisible = false
        // アニメーション完了後にonDismissを呼ぶ
    }

    // アニメーション完了後の処理
    LaunchedEffect(isVisible) {
        if (!isVisible) {
            delay(300) // アニメーション時間と同期
            onDismiss()
        }
    }

    Dialog(
        onDismissRequest = handleDismiss
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // iOS同等の背景ブラー効果
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                        handleDismiss()
                    }
            ) {
                drawRect(
                    color = Color.Black.copy(alpha = backgroundAlpha),
                    size = size
                )
            }

            // メインモーダルコンテンツ
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.92f)
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .scale(modalScale)
                    .offset { modalOffset }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                isDragging = true
                            },
                            onDragEnd = {
                                isDragging = false
                                if (dragOffset > 150) {
                                    handleDismiss()
                                } else {
                                    dragOffset = 0f
                                }
                            }
                        ) { _, dragAmount ->
                            val newOffset = dragOffset + dragAmount.y
                            dragOffset = if (newOffset > 0) newOffset else 0f
                        }
                    },
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
            ) {
                Column {
                    // iOS同等のハンドルバー
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(4.dp)
                                .background(
                                    color = Color.Gray.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )
                    }

                    // ヘッダーエリア（クローズボタン付き）
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        IconButton(
                            onClick = handleDismiss,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "閉じる",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // メインコンテンツエリア
                    EnhancedModalContent(
                        liver = liver,
                        isVisible = isVisible
                    )
                }
            }
        }
    }
}

@Composable
private fun EnhancedModalContent(
    liver: Liver,
    isVisible: Boolean
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // ヘッダー - 段階的アニメーション表示
        itemsIndexed(listOf("header")) { index, _ ->
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { it / 2 },
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = index * 100,
                        easing = EaseOutCubic
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = index * 100
                    )
                ) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = tween(
                        durationMillis = 400,
                        delayMillis = index * 100
                    )
                ),
                exit = slideOutVertically() + fadeOut() + scaleOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // iOS同等のヒーロー画像エリア
                    Box {
                        // 背景グラデーション
                        Box(
                            modifier = Modifier
                                .size(160.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f).copy(alpha = 0.2f),
                                            Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f).copy(alpha = 0.05f)
                                        )
                                    ),
                                    shape = CircleShape
                                )
                        )

                        AsyncImage(
                            model = liver.fullImageURL,
                            contentDescription = liver.name,
                            modifier = Modifier
                                .size(140.dp)
                                .align(Alignment.Center)
                                .shadow(
                                    elevation = 12.dp,
                                    shape = RoundedCornerShape(20.dp),
                                    spotColor = Color.Black.copy(alpha = 0.25f)
                                )
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                            error = painterResource(id = android.R.drawable.ic_menu_gallery)
                        )

                        // オンライン状態インジケーター - 将来的に実装予定
                        // liver.details?.isOnline?.let { isOnline ->
                        //     if (isOnline) {
                        //         Box(
                        //             modifier = Modifier
                        //                 .size(24.dp)
                        //                 .background(
                        //                     brush = Brush.radialGradient(
                        //                         colors = listOf(
                        //                             Color(0xFF4CAF50),
                        //                             Color(0xFF388E3C)
                        //                         )
                        //                     ),
                        //                     shape = CircleShape
                        //                 )
                        //                 .align(Alignment.TopEnd)
                        //                 .zIndex(1f)
                        //         ) {
                        //             Text(
                        //                 text = "●",
                        //                 color = Color.White,
                        //                 fontSize = 12.sp,
                        //                 modifier = Modifier.align(Alignment.Center)
                        //             )
                        //         }
                        //     }
                        // }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // ライバー名
                    Text(
                        text = liver.name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // プラットフォームと詳細情報
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = liver.platform,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )

                        if (liver.followers > 0) {
                            Text(
                                text = " • ",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = formatFollowerCount(liver.followers),
                                fontSize = 16.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    // レーティング表示 - 将来的に実装予定
                    // liver.details?.averageRating?.let { rating ->
                    //     if (rating > 0) {
                    //         Spacer(modifier = Modifier.height(12.dp))
                    //         Row(
                    //             verticalAlignment = Alignment.CenterVertically,
                    //             horizontalArrangement = Arrangement.Center
                    //         ) {
                    //             repeat(5) { index ->
                    //                 Icon(
                    //                     imageVector = Icons.Filled.Star,
                    //                     contentDescription = null,
                    //                     tint = if (index < rating.roundToInt()) {
                    //                         Color(0xFFFFD700)
                    //                     } else {
                    //                         Color(0xFFE0E0E0)
                    //                     },
                    //                     modifier = Modifier.size(20.dp)
                    //                 )
                    //             }
                    //             Spacer(modifier = Modifier.width(8.dp))
                    //             Text(
                    //                 text = String.format("%.1f", rating),
                    //                 fontSize = 18.sp,
                    //                 color = Color.Gray,
                    //                 fontWeight = FontWeight.Bold
                    //             )
                    //         }
                    //     }
                    // }
                }
            }
        }



        // コラボレーションステータス
        liver.details?.collaborationStatus?.let { status ->
            itemsIndexed(listOf("collaboration")) { index, _ ->
                val isCollabOK = status.contains("OK", ignoreCase = true) ||
                                status.contains("可能", ignoreCase = true)

                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it / 3 },
                        animationSpec = tween(
                            durationMillis = 500,
                            delayMillis = (index + 3) * 150,
                            easing = EaseOutCubic
                        )
                    ) + fadeIn(
                        animationSpec = tween(
                            durationMillis = 500,
                            delayMillis = (index + 3) * 150
                        )
                    ),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCollabOK) {
                                Color(0xFF4CAF50).copy(alpha = 0.15f)
                            } else {
                                Color.Gray.copy(alpha = 0.1f)
                            }
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = if (isCollabOK) Color(0xFF4CAF50) else Color.Gray,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (isCollabOK) "✓" else "×",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(
                                    text = "コラボレーション",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = status,
                                    fontSize = 14.sp,
                                    color = if (isCollabOK) Color(0xFF4CAF50) else Color.Gray,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }

                // Schedules
                liver.details?.schedules?.let { schedules ->
                    if (schedules.isNotEmpty()) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "配信プラットフォーム",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    schedules.forEach { schedule ->
                                        Text(
                                            text = schedule.name + if (schedule.followers != null) " (${schedule.followers})" else "",
                                            fontSize = 14.sp,
                                            color = Color.Black,
                                            modifier = Modifier.padding(vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Streaming URLs
                liver.details?.streamingUrls?.let { streamingUrls ->
                    if (streamingUrls.isNotEmpty()) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "チャンネルリンク",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    streamingUrls.forEach { streamingUrl ->
                                        Text(
                                            text = streamingUrl.type ?: "配信URL",
                                            fontSize = 14.sp,
                                            color = Color.Blue,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp)
                                                .clickable {
                                                    streamingUrl.url?.let { url ->
                                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                                        context.startActivity(intent)
                                                    }
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Comments
                liver.details?.comments?.let { comments ->
                    if (comments.isNotEmpty()) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "コメント",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    comments.forEach { comment ->
                                        Text(
                                            text = comment,
                                            fontSize = 14.sp,
                                            color = Color.Black,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

        // 口コミセクション
        itemsIndexed(listOf("reviews")) { index, _ ->
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { it / 3 },
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = (index + 6) * 150,
                        easing = EaseOutCubic
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = (index + 6) * 150
                    )
                ),
                exit = slideOutVertically() + fadeOut()
            ) {
                EnhancedInfoCard(
                    title = "口コミ",
                    icon = "💬"
                ) {
                    ReviewsView(liverId = liver.id)
                }
            }
        }

        // 余白
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun EnhancedInfoCard(
    title: String,
    icon: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = icon,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            content()
        }
    }
}

@Composable
private fun EnhancedInfoRow(
    label: String,
    value: String,
    icon: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 12.dp)
        )
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Composable
private fun EnhancedCategoryChip(
    category: String,
    delay: Int
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(delay.toLong())
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(
            initialScale = 0.8f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ) + fadeIn(),
        exit = scaleOut() + fadeOut()
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f).copy(alpha = 0.2f)
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = category,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(red = 96f/255f, green = 212f/255f, blue = 200f/255f),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}

// フォロワー数フォーマット関数
private fun formatFollowerCount(followers: Int): String {
    return when {
        followers >= 1000000 -> String.format("%.1fM フォロワー", followers / 1000000.0)
        followers >= 1000 -> String.format("%.1fK フォロワー", followers / 1000.0)
        else -> "$followers フォロワー"
    }
}