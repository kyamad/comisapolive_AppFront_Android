@file:OptIn(ExperimentalMaterial3Api::class)

package comisapolive.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun CustomTabButton(
    text: String,
    imageName: String,
    backgroundColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // iOS同等のプレス/選択アニメーション
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = when {
            isPressed -> 0.95f
            isSelected -> 1.05f
            else -> 1f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "tabScale"
    )

    val elevation by animateFloatAsState(
        targetValue = if (isSelected) 8f else 4f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "tabElevation"
    )

    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    val iconResId = context.resources.getIdentifier(
        imageName,
        "drawable",
        context.packageName
    )

    // iOS同等のTabButtonスタイル
    Card(
        modifier = modifier
            .height(80.dp)
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .zIndex(if (isSelected) 1f else 0f),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.White else backgroundColor.copy(alpha = 0.8f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // アイコン表示
            if (iconResId != 0) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = text,
                    modifier = Modifier.size(24.dp),
                    colorFilter = if (isSelected) {
                        androidx.compose.ui.graphics.ColorFilter.tint(backgroundColor)
                    } else {
                        androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                    }
                )
            } else {
                // フォールバックアイコン
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = if (isSelected) backgroundColor else Color.White,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // テキスト表示
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) backgroundColor else Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
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