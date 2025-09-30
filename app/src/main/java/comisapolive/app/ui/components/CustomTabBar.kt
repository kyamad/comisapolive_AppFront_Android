package comisapolive.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import comisapolive.app.ui.navigation.TabScreen

@Composable
fun CustomTabBar(
    selectedTab: TabScreen,
    onTabSelected: (TabScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    // iOS同等のシンプルなTabBar + システムバー対応
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .shadow(
                elevation = 8.dp,
                spotColor = Color.Black.copy(alpha = 0.1f),
                ambientColor = Color.Black.copy(alpha = 0.05f)
            )
    ) {
        // 上部の境界線（iOS同等）
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)  // iOS同等の標準的なTabBarの高さ
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TabScreen.values().forEach { tab ->
                    TabItem(
                        tab = tab,
                        isSelected = selectedTab == tab,
                        onClick = { onTabSelected(tab) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // システムバー用のパディング
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TabItem(
    tab: TabScreen,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val iconResId = context.resources.getIdentifier(
        tab.iconResource,
        "drawable",
        context.packageName
    )

    // iOS同等のタブアイテム
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .clickable { onClick() }
            .padding(horizontal = 4.dp)
    ) {
        if (iconResId != 0) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = tab.title,
                modifier = Modifier
                    .size(24.dp)
                    .offset(y = 6.dp),  // iOS同等のアイコン位置調整
                colorFilter = if (isSelected) {
                    androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF007AFF))  // iOS accent color
                } else {
                    androidx.compose.ui.graphics.ColorFilter.tint(Color.Gray.copy(alpha = 0.6f))
                }
            )
        } else {
            // Fallback icon if resource not found
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .offset(y = 6.dp)
                    .background(
                        color = if (isSelected) Color(0xFF007AFF) else Color.Gray.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = tab.title,
            fontSize = 12.sp,  // iOS同等のフォントサイズ
            fontWeight = FontWeight.Medium,  // iOS同等のフォントウェイト
            color = if (isSelected) Color(0xFF007AFF) else Color.Gray.copy(alpha = 0.6f),
            modifier = Modifier.offset(y = 10.dp)  // iOS同等のテキスト位置調整
        )
    }
}