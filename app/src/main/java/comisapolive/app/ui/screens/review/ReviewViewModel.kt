package comisapolive.app.ui.screens.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import comisapolive.app.model.Review
import comisapolive.app.model.ReviewStats
import comisapolive.app.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReviewUiState(
    val reviews: List<Review> = emptyList(),
    val reviewStats: ReviewStats? = null,
    val isLoading: Boolean = false,
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val hasReviewed: Boolean = false
)

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState: StateFlow<ReviewUiState> = _uiState.asStateFlow()

    // iOS同等の口コミ取得機能
    fun loadReviews(liverId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                // 並列でレビューと統計を取得（iOS同等）
                val reviewsFlow = reviewRepository.getReviews(liverId)
                val statsFlow = reviewRepository.getReviewStats(liverId)

                combine(reviewsFlow, statsFlow) { reviewsResult, statsResult ->
                    val reviews = reviewsResult.getOrNull() ?: emptyList()
                    val stats = statsResult.getOrNull()

                    val errorMessage = when {
                        reviewsResult.isFailure -> "口コミの取得に失敗しました"
                        else -> null
                    }

                    ReviewUiState(
                        reviews = reviews,
                        reviewStats = stats,
                        isLoading = false,
                        errorMessage = errorMessage,
                        hasReviewed = reviewRepository.hasReviewedLiver(liverId)
                    )
                }.catch { e ->
                    emit(ReviewUiState(
                        isLoading = false,
                        errorMessage = "データの読み込みに失敗しました: ${e.message}",
                        hasReviewed = reviewRepository.hasReviewedLiver(liverId)
                    ))
                }.collect { newState ->
                    _uiState.value = newState
                }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "データの読み込みに失敗しました: ${e.message}"
                )
            }
        }
    }

    // iOS同等の口コミ投稿機能
    fun submitReview(liverId: String, rating: Int, comment: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isSubmitting = true,
                errorMessage = null,
                successMessage = null
            )

            reviewRepository.submitReview(liverId, rating, comment)
                .collect { result ->
                    result.fold(
                        onSuccess = {
                            _uiState.value = _uiState.value.copy(
                                isSubmitting = false,
                                successMessage = "口コミを投稿しました",
                                hasReviewed = true
                            )
                            // 投稿成功後にデータを再取得
                            loadReviews(liverId)
                        },
                        onFailure = { e ->
                            _uiState.value = _uiState.value.copy(
                                isSubmitting = false,
                                errorMessage = e.message
                            )
                        }
                    )
                }
        }
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            successMessage = null
        )
    }
}