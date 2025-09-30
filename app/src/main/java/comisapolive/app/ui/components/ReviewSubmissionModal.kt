package comisapolive.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ReviewSubmissionModal(
    liverId: String,
    onDismiss: () -> Unit,
    onSubmit: (rating: Int, comment: String) -> Unit
) {
    var rating by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }
    val isValid = rating > 0 && comment.isNotBlank() && comment.length <= 1000

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // タイトル
                Text(
                    text = "口コミを投稿",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // 星評価セクション（iOS同等）
                Column {
                    Text(
                        text = "評価",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(5) { index ->
                            Icon(
                                imageVector = if (index < rating) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "${index + 1}つ星",
                                tint = if (index < rating) Color(0xFFFFB400) else Color.Gray.copy(alpha = 0.3f),
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { rating = index + 1 }
                            )
                        }
                    }

                    if (rating > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${rating}つ星",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                // コメント入力セクション（iOS同等の1000文字制限）
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "コメント",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Text(
                            text = "${comment.length}/1000",
                            fontSize = 12.sp,
                            color = if (comment.length > 1000) Color.Red else Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = comment,
                        onValueChange = { newValue ->
                            if (newValue.length <= 1000) {
                                comment = newValue
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        placeholder = {
                            Text(
                                text = "このライバーについての感想をお聞かせください...",
                                color = Color.Gray.copy(alpha = 0.7f),
                                fontSize = 14.sp
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // ボタンエリア（iOS同等）
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // キャンセルボタン
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Gray
                        ),
                    ) {
                        Text(
                            text = "キャンセル",
                            fontSize = 16.sp
                        )
                    }

                    // 投稿ボタン
                    Button(
                        onClick = {
                            if (isValid) {
                                onSubmit(rating, comment.trim())
                            }
                        },
                        modifier = Modifier.weight(1f),
                        enabled = isValid,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5F8BDD),
                            disabledContainerColor = Color.Gray.copy(alpha = 0.3f)
                        )
                    ) {
                        Text(
                            text = "投稿",
                            color = if (isValid) Color.White else Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}