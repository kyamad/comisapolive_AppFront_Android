package comisapolive.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import comisapolive.app.model.Liver
import kotlin.math.roundToInt

@Composable
fun LiverCard(
    liver: Liver,
    onClick: (Liver) -> Unit,
    modifier: Modifier = Modifier,
    isCircular: Boolean = false,
    showRating: Boolean = true,
    isPromoted: Boolean = false
) {
    if (isCircular) {
        EnhancedCircularLiverCard(
            liver = liver,
            onClick = onClick,
            modifier = modifier,
            showRating = showRating
        )
    } else {
        EnhancedRectangularLiverCard(
            liver = liver,
            onClick = onClick,
            modifier = modifier,
            showRating = showRating,
            isPromoted = isPromoted
        )
    }
}

@Composable
private fun EnhancedCircularLiverCard(
    liver: Liver,
    onClick: (Liver) -> Unit,
    modifier: Modifier = Modifier,
    showRating: Boolean = true
) {
    // iOS同等のプレス/ホバーアニメーション
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardScale"
    )

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(85.dp)
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick(liver)
            }
            .padding(8.dp)
    ) {
        Box {
            // iOS同等の影とボーダー効果
            AsyncImage(
                model = liver.fullImageURL,
                contentDescription = liver.name,
                modifier = Modifier
                    .size(70.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.25f)
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_menu_gallery)
            )

            // オンライン状態インジケーター（iOS同等） - 将来的に実装予定
            // liver.details?.isOnline?.let { isOnline ->
            //     if (isOnline) {
            //         Box(
            //             modifier = Modifier
            //                 .size(16.dp)
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
            //         )
            //     }
            // }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 名前表示（iOS同等のスタイル）
        Text(
            text = liver.name,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            lineHeight = 13.sp
        )

        // プラットフォーム表示
        Text(
            text = liver.platform,
            fontSize = 9.sp,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )

        // レーティング表示（iOS同等） - 将来的に実装予定
        // if (showRating) {
        //     liver.details?.averageRating?.let { rating ->
        //         if (rating > 0) {
        //             Spacer(modifier = Modifier.height(4.dp))
        //             Row(
        //                 verticalAlignment = Alignment.CenterVertically,
        //                 horizontalArrangement = Arrangement.Center
        //             ) {
        //                 Icon(
        //                     imageVector = Icons.Filled.Star,
        //                     contentDescription = "Rating",
        //                     tint = Color(0xFFFFD700),
        //                     modifier = Modifier.size(10.dp)
        //                 )
        //                 Spacer(modifier = Modifier.width(2.dp))
        //                 Text(
        //                     text = String.format("%.1f", rating),
        //                     fontSize = 8.sp,
        //                     color = Color.Gray,
        //                     fontWeight = FontWeight.Medium
        //                 )
        //             }
        //         }
        //     }
        // }
    }

    // プレス状態の管理
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is androidx.compose.foundation.interaction.PressInteraction.Press -> {
                    isPressed = true
                }
                is androidx.compose.foundation.interaction.PressInteraction.Release -> {
                    isPressed = false
                }
                is androidx.compose.foundation.interaction.PressInteraction.Cancel -> {
                    isPressed = false
                }
            }
        }
    }
}

@Composable
private fun EnhancedRectangularLiverCard(
    liver: Liver,
    onClick: (Liver) -> Unit,
    modifier: Modifier = Modifier,
    showRating: Boolean = true,
    isPromoted: Boolean = false
) {
    // iOS同等のプレス/ホバーアニメーション
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardScale"
    )

    val elevation by animateFloatAsState(
        targetValue = if (isPressed) 2f else if (isPromoted) 8f else 6f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardElevation"
    )

    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick(liver) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPromoted) {
                Color(0xFFFFF8E1) // iOS同等のプロモーション背景色
            } else {
                Color.White
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.dp)
    ) {
        Column {
            // プロモーションバッジ（iOS同等）
            if (isPromoted) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFFD700),
                                    Color(0xFFFFA000)
                                )
                            )
                        )
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "✨ おすすめ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    // iOS同等の影とボーダー効果
                    AsyncImage(
                        model = liver.fullImageURL,
                        contentDescription = liver.name,
                        modifier = Modifier
                            .size(70.dp)
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(12.dp),
                                spotColor = Color.Black.copy(alpha = 0.2f)
                            )
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                        error = painterResource(id = android.R.drawable.ic_menu_gallery)
                    )

                    // オンライン状態インジケーター - 将来的に実装予定
                    // liver.details?.isOnline?.let { isOnline ->
                    //     if (isOnline) {
                    //         Box(
                    //             modifier = Modifier
                    //                 .size(18.dp)
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
                    //                 fontSize = 8.sp,
                    //                 modifier = Modifier.align(Alignment.Center)
                    //             )
                    //         }
                    //     }
                    // }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // 名前とプラットフォーム（iOS同等のスタイル）
                    Text(
                        text = liver.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = liver.platform,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // プラットフォームアイコン（iOS同等）
                        val context = LocalContext.current
                        val platformIconId = context.resources.getIdentifier(
                            liver.platform.lowercase(),
                            "drawable",
                            context.packageName
                        )

                        if (platformIconId != 0) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = platformIconId),
                                contentDescription = liver.platform,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    // フォロワー数表示（iOS同等の書式）
                    if (liver.followers > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = formatFollowerCount(liver.followers),
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // レーティング表示（iOS同等） - 将来的に実装予定
                    // if (showRating) {
                    //     liver.details?.averageRating?.let { rating ->
                    //         if (rating > 0) {
                    //             Spacer(modifier = Modifier.height(6.dp))
                    //             Row(
                    //                 verticalAlignment = Alignment.CenterVertically
                    //             ) {
                    //                 repeat(5) { index ->
                    //                     Icon(
                    //                         imageVector = Icons.Filled.Star,
                    //                         contentDescription = null,
                    //                         tint = if (index < rating.roundToInt()) {
                    //                             Color(0xFFFFD700)
                    //                         } else {
                    //                             Color(0xFFE0E0E0)
                    //                         },
                    //                         modifier = Modifier.size(14.dp)
                    //                     )
                    //                 }
                    //                 Spacer(modifier = Modifier.width(6.dp))
                    //                 Text(
                    //                     text = String.format("%.1f", rating),
                    //                     fontSize = 12.sp,
                    //                     color = Color.Gray,
                    //                     fontWeight = FontWeight.Medium
                    //                 )
                    //             }
                    //         }
                    //     }
                    // }

                    // コラボ可能表示（iOS同等のスタイル）
                    liver.details?.collaborationStatus?.let { status ->
                        if (status.contains("OK", ignoreCase = true) ||
                            status.contains("可能", ignoreCase = true)) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFF4CAF50),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "コラボ可能",
                                        fontSize = 11.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // 右側のアクションエリア（iOS同等）
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    // お気に入りやその他のアクションボタン（将来的に追加可能）
                }
            }
        }
    }

    // プレス状態の管理
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is androidx.compose.foundation.interaction.PressInteraction.Press -> {
                    isPressed = true
                }
                is androidx.compose.foundation.interaction.PressInteraction.Release -> {
                    isPressed = false
                }
                is androidx.compose.foundation.interaction.PressInteraction.Cancel -> {
                    isPressed = false
                }
            }
        }
    }
}

// iOS同等のフォロワー数フォーマット関数
private fun formatFollowerCount(followers: Int): String {
    return when {
        followers >= 1000000 -> String.format("%.1fM フォロワー", followers / 1000000.0)
        followers >= 1000 -> String.format("%.1fK フォロワー", followers / 1000.0)
        else -> "$followers フォロワー"
    }
}