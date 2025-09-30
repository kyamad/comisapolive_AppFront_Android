package comisapolive.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import comisapolive.app.model.Liver

@Composable
fun ColaboLiverCarousel(
    livers: List<Liver>,
    onLiverTap: (Liver) -> Unit,
    modifier: Modifier = Modifier
) {
    // NewライバーCarouselと同様のシンプルな横スクロール型
    LazyRow(
        modifier = modifier.padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        itemsIndexed(livers) { _, liver ->
            SimpleColaboCard(
                liver = liver,
                onClick = { onLiverTap(liver) }
            )
        }
    }
}

@Composable
private fun SimpleColaboCard(
    liver: Liver,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "colaboCardScale"
    )

    val interactionSource = remember { MutableInteractionSource() }

    // 要求された仕様に基づくカードデザイン
    Column(
        modifier = Modifier
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(8.dp)
    ) {
        // プロフィール画像 - 四角形、左上と右下のみ角丸
        AsyncImage(
            model = liver.fullImageURL,
            contentDescription = liver.name,
            modifier = Modifier
                .size(238.dp)  // 140dp * 1.7 = 238dp
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 12.dp
                    )
                ),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_menu_gallery)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 名前欄 - 白背景、黒ボーダー、シャドウ
        Box(
            modifier = Modifier
                .background(
                    Color.White,
                    RoundedCornerShape(4.dp)
                )
                .border(
                    1.dp,
                    Color.Black,
                    RoundedCornerShape(4.dp)
                )
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0xFFCBEEEA).copy(alpha = 0.5f),
                    ambientColor = Color(0xFFCBEEEA).copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = liver.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // プラットフォーム・フォロワー情報 - 黒背景、白文字
        Box(
            modifier = Modifier
                .width(238.dp)
                .background(
                    Color.Black,
                    RoundedCornerShape(4.dp)
                )
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = liver.platform,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${liver.followerDisplayText}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 配信カテゴリ - #cbeeea背景、左上四角・他角丸、全カテゴリ表示
        liver.categories?.let { categories ->
            if (categories.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .width(238.dp)
                        .background(
                            Color(0xFFCBEEEA),
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 8.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 8.dp
                            )
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = categories.joinToString(", "),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 14.sp
                    )
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

