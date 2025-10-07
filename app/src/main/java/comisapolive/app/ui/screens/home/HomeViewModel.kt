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
        Article(1, "ライブ配信に人が来ない原因と対処方法", "Article1", "https://www.comisapolive.com/column/detail/41/"),
        Article(2, "ビゴライブでの配信中の禁止事項", "Article2", "https://www.comisapolive.com/column/detail/40/"),
        Article(3, "ライブ配信に向いている人の特徴", "Article3", "https://www.comisapolive.com/column/detail/39/"),
        Article(4, "ライブ配信で注意したい3つの騒音", "Article4", "https://www.comisapolive.com/column/detail/38/"),
        Article(5, "TikTokで稼ぐなら", "Article5", "https://www.comisapolive.com/column/detail/37/"),
        Article(6, "ライバーとして稼ぐなら", "Article6", "https://www.comisapolive.com/column/detail/36/"),
        Article(7, "ライブ配信をやめたいと思うのはどんな時？", "Article7", "https://www.comisapolive.com/column/detail/35/"),
        Article(8, "ライブ配信で病む人の特徴", "Article8", "https://www.comisapolive.com/column/detail/34/")
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