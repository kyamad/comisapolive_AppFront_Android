package comisapolive.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App title/logo
            Text(
                text = "コミサポライブ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Search button
            val searchIconId = context.resources.getIdentifier(
                "search",
                "drawable",
                context.packageName
            )

            if (searchIconId != 0) {
                Image(
                    painter = painterResource(id = searchIconId),
                    contentDescription = "検索",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onSearchClick() }
                )
            } else {
                // Fallback search button
                Button(
                    onClick = onSearchClick,
                    modifier = Modifier.size(40.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("🔍", fontSize = 16.sp)
                }
            }
        }

        // Divider
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.3f)
        )
    }
}