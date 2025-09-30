package comisapolive.app.repository

import android.content.SharedPreferences
import comisapolive.app.model.Review
import comisapolive.app.model.ReviewStats
import comisapolive.app.model.ReviewSubmissionRequest
import comisapolive.app.network.ReviewApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(
    private val reviewApiService: ReviewApiService,
    private val sharedPreferences: SharedPreferences
) {

    // iOS同等の口コミ取得
    fun getReviews(liverId: String): Flow<Result<List<Review>>> = flow {
        try {
            val response = reviewApiService.getReviews(liverId)
            if (response.isSuccessful) {
                val reviewResponse = response.body()
                if (reviewResponse?.success == true) {
                    emit(Result.success(reviewResponse.reviews))
                } else {
                    emit(Result.success(emptyList()))
                }
            } else {
                emit(Result.failure(Exception("HTTP Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    // iOS同等の口コミ統計取得
    fun getReviewStats(liverId: String): Flow<Result<ReviewStats?>> = flow {
        try {
            val response = reviewApiService.getReviewStats(liverId)
            if (response.isSuccessful) {
                val stats = response.body()
                if (stats?.success == true) {
                    emit(Result.success(stats))
                } else {
                    emit(Result.success(null))
                }
            } else {
                emit(Result.success(null))
            }
        } catch (e: Exception) {
            emit(Result.success(null))
        }
    }

    // iOS同等の口コミ投稿
    fun submitReview(liverId: String, rating: Int, comment: String): Flow<Result<Boolean>> = flow {
        try {
            val request = ReviewSubmissionRequest(liverId, rating, comment)
            val response = reviewApiService.submitReview(request)

            if (response.isSuccessful) {
                val submissionResponse = response.body()
                if (submissionResponse?.success == true) {
                    // iOS同等の投稿済み記録
                    markLiverAsReviewed(liverId)
                    emit(Result.success(true))
                } else {
                    val errorMessage = when (response.code()) {
                        429 -> {
                            val remainingSeconds = submissionResponse?.remainingSeconds ?: 0
                            val minutes = remainingSeconds / 60
                            val seconds = remainingSeconds % 60
                            "投稿制限中です。あと${minutes}分${seconds}秒お待ちください。"
                        }
                        else -> submissionResponse?.error ?: "投稿に失敗しました"
                    }
                    emit(Result.failure(Exception(errorMessage)))
                }
            } else {
                emit(Result.failure(Exception("HTTP Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(Exception("ネットワークエラーが発生しました")))
        }
    }

    // iOS UserDefaultsManager同等機能
    fun hasReviewedLiver(liverId: String): Boolean {
        val reviewedLivers = getReviewedLivers()
        return reviewedLivers.contains(liverId)
    }

    private fun markLiverAsReviewed(liverId: String) {
        val reviewedLivers = getReviewedLivers().toMutableSet()
        reviewedLivers.add(liverId)
        sharedPreferences.edit()
            .putStringSet(REVIEWED_LIVERS_KEY, reviewedLivers)
            .apply()
    }

    private fun getReviewedLivers(): Set<String> {
        return sharedPreferences.getStringSet(REVIEWED_LIVERS_KEY, emptySet()) ?: emptySet()
    }

    companion object {
        private const val REVIEWED_LIVERS_KEY = "reviewed_livers"
    }
}