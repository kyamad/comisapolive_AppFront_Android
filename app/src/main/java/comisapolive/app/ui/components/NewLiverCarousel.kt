@file:OptIn(ExperimentalFoundationApi::class)

package comisapolive.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import comisapolive.app.model.Liver
import kotlin.math.abs

@Composable
fun NewLiverCarousel(
    livers: List<Liver>,
    onLiverTap: (Liver) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val density = LocalDensity.current
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    // iOS同等の3Dカルーセル効果とスナップ動作
    LazyRow(
        state = listState,
        flingBehavior = snapBehavior,
        modifier = modifier.height(260.dp),
        horizontalArrangement = Arrangement.spacedBy((-20).dp), // iOS同等のオーバーラップ
        contentPadding = PaddingValues(horizontal = 60.dp)
    ) {
        itemsIndexed(livers) { index, liver ->
            // iOS同等の視差効果とカード変形計算
            val itemInfo = listState.layoutInfo.visibleItemsInfo
                .firstOrNull { it.index == index }

            val viewportCenter = listState.layoutInfo.viewportStartOffset +
                listState.layoutInfo.viewportSize.width / 2

            val itemCenter = itemInfo?.let {
                it.offset + it.size / 2
            } ?: 0

            val distanceFromCenter = abs(itemCenter - viewportCenter).toFloat()
            val maxDistance = with(density) { 200.dp.toPx() }
            val normalizedDistance = (distanceFromCenter / maxDistance).coerceIn(0f, 1f)

            Enhanced3DLiverCard(
                liver = liver,
                onClick = { onLiverTap(liver) },
                distanceFromCenter = normalizedDistance,
                isCenter = normalizedDistance < 0.3f
            )
        }
    }
}

@Composable
private fun Enhanced3DLiverCard(
    liver: Liver,
    onClick: () -> Unit,
    distanceFromCenter: Float,
    isCenter: Boolean
) {
    val density = LocalDensity.current
    // iOS同等の3D変形とアニメーション
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = when {
            isPressed -> 0.9f
            isCenter -> 1f
            else -> 0.7f + (1f - distanceFromCenter) * 0.3f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardScale"
    )

    val rotationY by animateFloatAsState(
        targetValue = if (isCenter) 0f else (distanceFromCenter - 0.5f) * 45f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardRotationY"
    )

    val elevation by animateFloatAsState(
        targetValue = if (isCenter) 12f else 4f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardElevation"
    )

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(200.dp)
            .scale(scale)
            .graphicsLayer(
                rotationY = rotationY,
                cameraDistance = 8 * density.density
            )
            .zIndex(if (isCenter) 1f else 0f)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(16.dp)
    ) {
        // シンプルな円形画像
        Box(
            modifier = Modifier
                .size(120.dp)
                .shadow(
                    elevation = elevation.dp,
                    shape = CircleShape,
                    spotColor = Color.Black.copy(alpha = 0.3f)
                )
                .background(Color.White, shape = CircleShape)
                .padding(8.dp)
        ) {
            AsyncImage(
                model = liver.fullImageURL,
                contentDescription = liver.name,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)  // 完全な正方形を保証
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_menu_gallery)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ライバー名（iOS同等のスタイル）
        Text(
            text = liver.name,
            fontSize = if (isCenter) 18.sp else 16.sp,
            fontWeight = if (isCenter) FontWeight.Bold else FontWeight.SemiBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

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


// フォロワー数フォーマット関数
private fun formatFollowerCount(followers: Int): String {
    return when {
        followers >= 1000000 -> String.format("%.1fM フォロワー", followers / 1000000.0)
        followers >= 1000 -> String.format("%.1fK フォロワー", followers / 1000.0)
        else -> "$followers フォロワー"
    }
}