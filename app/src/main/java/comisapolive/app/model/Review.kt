package comisapolive.app.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

// MARK: - Review Data Models

data class ReviewResponse(
    val success: Boolean,
    @SerializedName("liver_id") val liverId: String,
    val reviews: List<Review>,
    val total: Int
)

data class Review(
    val id: Int,
    val rating: Int,
    val comment: String,
    @SerializedName("created_at") val createdAt: Long
) {
    // 日付表示用の計算プロパティ（iOS同等）
    val formattedDate: String
        get() {
            val date = Date(createdAt)
            val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
            return formatter.format(date)
        }
}

data class ReviewStats(
    val success: Boolean,
    @SerializedName("liver_id") val liverId: String,
    @SerializedName("average_rating") val averageRating: Double,
    @SerializedName("review_count") val reviewCount: Int
)

data class ReviewSubmissionRequest(
    @SerializedName("liver_id") val liverId: String,
    val rating: Int,
    val comment: String
)

data class ReviewSubmissionResponse(
    val success: Boolean,
    @SerializedName("review_id") val reviewId: Int?,
    val message: String?,
    val error: String?,
    val remainingSeconds: Int?
)