package comisapolive.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import comisapolive.app.model.Article
import comisapolive.app.model.Liver
import comisapolive.app.repository.LiverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val newLivers: List<Liver> = emptyList(),
    val collaborationLivers: List<Liver> = emptyList(),
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LiverRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val articles = listOf(
        Article(1, "ライブ配信で収益を上げる方法", "Article1", "https://example.com/article1"),
        Article(2, "配信機材の選び方ガイド", "Article2", "https://example.com/article2"),
        Article(3, "視聴者を増やすコツ", "Article3", "https://example.com/article3"),
        Article(4, "コラボ配信の始め方", "Article4", "https://example.com/article4"),
        Article(5, "配信スケジュールの組み方", "Article5", "https://example.com/article5"),
        Article(6, "SNS連携で集客アップ", "Article6", "https://example.com/article6"),
        Article(7, "配信トラブルの対処法", "Article7", "https://example.com/article7"),
        Article(8, "ファンとのコミュニケーション術", "Article8", "https://example.com/article8")
    )

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                android.util.Log.d("HomeViewModel", "ホーム画面データ取得開始")
                // iOS同等の並列データ取得
                val newLiversFlow = repository.getNewLivers()
                val collaborationLiversFlow = repository.getCollaborationLivers()

                // 両方のデータを並列で取得
                combine(newLiversFlow, collaborationLiversFlow) { newLiversResult, collaborationLiversResult ->
                    android.util.Log.d("HomeViewModel", "データ取得結果: newSuccess=${newLiversResult.isSuccess}, colaboSuccess=${collaborationLiversResult.isSuccess}")

                    val newLivers = newLiversResult.getOrNull() ?: emptyList()
                    val collaborationLivers = collaborationLiversResult.getOrNull() ?: emptyList()

                    android.util.Log.d("HomeViewModel", "取得データ件数: new=${newLivers.size}, colabo=${collaborationLivers.size}")

                    val errorMessage = when {
                        newLiversResult.isFailure && collaborationLiversResult.isFailure -> {
                            val newError = newLiversResult.exceptionOrNull()?.message ?: "不明なエラー"
                            val colaboError = collaborationLiversResult.exceptionOrNull()?.message ?: "不明なエラー"
                            android.util.Log.e("HomeViewModel", "全データ取得失敗: new=$newError, colabo=$colaboError")
                            "データの取得に失敗しました"
                        }
                        newLiversResult.isFailure -> {
                            val error = newLiversResult.exceptionOrNull()?.message ?: "不明なエラー"
                            android.util.Log.e("HomeViewModel", "新着ライバー取得失敗: $error")
                            "新着ライバーの取得に失敗しました"
                        }
                        collaborationLiversResult.isFailure -> {
                            val error = collaborationLiversResult.exceptionOrNull()?.message ?: "不明なエラー"
                            android.util.Log.e("HomeViewModel", "コラボライバー取得失敗: $error")
                            "コラボ可能ライバーの取得に失敗しました"
                        }
                        else -> null
                    }

                    HomeUiState(
                        newLivers = newLivers,
                        collaborationLivers = collaborationLivers,
                        articles = articles,
                        isLoading = false,
                        errorMessage = errorMessage
                    )
                }.catch { e ->
                    android.util.Log.e("HomeViewModel", "Flow例外", e)
                    emit(HomeUiState(
                        articles = articles,
                        isLoading = false,
                        errorMessage = "データの読み込みに失敗しました: ${e.message}"
                    ))
                }.collect { newState ->
                    android.util.Log.d("HomeViewModel", "UI状態更新: loading=${newState.isLoading}, error=${newState.errorMessage}")
                    _uiState.value = newState
                }

            } catch (e: Exception) {
                android.util.Log.e("HomeViewModel", "ViewModelScope例外", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "データの読み込みに失敗しました: ${e.message}"
                )
            }
        }
    }

    fun refresh() {
        loadData()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}