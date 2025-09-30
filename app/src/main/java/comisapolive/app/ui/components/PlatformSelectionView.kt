@file:OptIn(ExperimentalMaterial3Api::class)

package comisapolive.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Platform(
    val name: String,
    val imageName: String,
    val displayName: String,
    val backgroundColor: Color
)

@Composable
fun PlatformSelectionView(
    platforms: List<Platform>,
    onPlatformSelected: (Platform) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        itemsIndexed(platforms) { index, platform ->
            PlatformCard(
                platform = platform,
                onClick = { onPlatformSelected(platform) },
                delay = index * 100
            )
        }
    }
}

@Composable
private fun PlatformCard(
    platform: Platform,
    onClick: () -> Unit,
    delay: Int = 0
) {
    // iOS同等のプレス/ホバーアニメーション
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "platformCardScale"
    )

    val elevation by animateFloatAsState(
        targetValue = if (isPressed) 4f else 8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "platformCardElevation"
    )

    // エントランスアニメーション
    var isVisible by remember { mutableStateOf(false) }
    val animatedScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "entranceScale"
    )

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(delay.toLong())
        isVisible = true
    }

    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .scale(scale * animatedScale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = platform.backgroundColor.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // プラットフォームアイコン
            val imageResId = context.resources.getIdentifier(
                platform.imageName,
                "drawable",
                context.packageName
            )

            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = platform.displayName,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
            } else {
                // フォールバックアイコン
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        text = platform.displayName.take(2).uppercase(),
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // プラットフォーム名
            Text(
                text = platform.displayName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
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