package comisapolive.app.ui.screens.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import comisapolive.app.model.Genre
import comisapolive.app.model.Liver
import comisapolive.app.model.Platform
import comisapolive.app.repository.LiverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ContentUiState(
    val selectedTab: ContentTab = ContentTab.GENRE,
    val allLivers: List<Liver> = emptyList(),
    val filteredLivers: List<Liver> = emptyList(),
    val selectedGenre: Genre? = null,
    val selectedPlatform: String? = null,  // iOS同等のString型
    val availablePlatforms: List<String> = emptyList(),  // iOS同等の動的プラットフォーム
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

enum class ContentTab(val title: String) {
    GENRE("ジャンルから探す"),
    PLATFORM("配信アプリから探す")
}

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val repository: LiverRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContentUiState())
    val uiState: StateFlow<ContentUiState> = _uiState.asStateFlow()

    init {
        loadLivers()
    }

    fun selectTab(tab: ContentTab) {
        _uiState.value = _uiState.value.copy(
            selectedTab = tab,
            selectedGenre = null,
            selectedPlatform = null,
            filteredLivers = emptyList()
        )
    }

    fun selectGenre(genre: Genre) {
        _uiState.value = _uiState.value.copy(selectedGenre = genre)
        filterByGenre(genre)
    }

    fun selectPlatform(platform: String?) {
        _uiState.value = _uiState.value.copy(selectedPlatform = platform)
        if (platform != null) {
            filterByPlatform(platform)
        } else {
            _uiState.value = _uiState.value.copy(filteredLivers = emptyList())
        }
    }

    fun clearSelection() {
        _uiState.value = _uiState.value.copy(
            selectedGenre = null,
            selectedPlatform = null,
            filteredLivers = emptyList()
        )
    }

    private fun loadLivers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                repository.getLivers()
                    .catch { e ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = "ライバー情報の取得に失敗しました: ${e.message}"
                        )
                    }
                    .collect { result ->
                        result.fold(
                            onSuccess = { livers ->
                                _uiState.value = _uiState.value.copy(
                                    allLivers = livers,
                                    availablePlatforms = generateAvailablePlatforms(livers),
                                    isLoading = false
                                )
                            },
                            onFailure = { e ->
                                _uiState.value = _uiState.value.copy(
                                    isLoading = false,
                                    errorMessage = "ライバー情報の取得に失敗しました: ${e.message}"
                                )
                            }
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "ライバー情報の取得に失敗しました: ${e.message}"
                )
            }
        }
    }

    private fun filterByGenre(genre: Genre) {
        android.util.Log.d("ContentViewModel", "Filtering by genre: ${genre.displayName}")
        android.util.Log.d("ContentViewModel", "Total livers: ${_uiState.value.allLivers.size}")

        // まずすべてのライバーのカテゴリを出力
        _uiState.value.allLivers.forEach { liver ->
            android.util.Log.d("ContentViewModel", "Liver: ${liver.name}, Categories: ${liver.details?.categories}")
        }

        val filteredLivers = _uiState.value.allLivers.filter { liver ->
            // カテゴリがnullまたは空の場合も考慮
            val categories = liver.details?.categories ?: liver.categories

            val hasMatchingCategory = categories?.any { category ->
                android.util.Log.d("ContentViewModel", "Checking category: '$category' for genre: '${genre.displayName}'")
                val matches = category.contains(genre.displayName, ignoreCase = true) ||
                        // Additional matching for common variations
                        when (genre) {
                            Genre.GAME -> category.contains("ゲーム", ignoreCase = true) ||
                                    category.contains("game", ignoreCase = true)
                            Genre.SONG -> category.contains("歌", ignoreCase = true) ||
                                    category.contains("音楽", ignoreCase = true) ||
                                    category.contains("song", ignoreCase = true)
                            Genre.TALK -> category.contains("雑談", ignoreCase = true) ||
                                    category.contains("talk", ignoreCase = true)
                            Genre.ART -> category.contains("絵", ignoreCase = true) ||
                                    category.contains("アート", ignoreCase = true) ||
                                    category.contains("art", ignoreCase = true)
                            Genre.COOKING -> category.contains("料理", ignoreCase = true) ||
                                    category.contains("cooking", ignoreCase = true)
                            Genre.COSPLAY -> category.contains("コスプレ", ignoreCase = true) ||
                                    category.contains("cosplay", ignoreCase = true)
                            else -> false
                        }
                android.util.Log.d("ContentViewModel", "Category '$category' matches: $matches")
                matches
            } ?: false

            if (hasMatchingCategory) {
                android.util.Log.d("ContentViewModel", "Liver ${liver.name} matches genre ${genre.displayName}")
            }
            hasMatchingCategory
        }

        android.util.Log.d("ContentViewModel", "Filtered livers count: ${filteredLivers.size}")
        android.util.Log.d("ContentViewModel", "Filtered livers: ${filteredLivers.map { it.name }}")
        _uiState.value = _uiState.value.copy(filteredLivers = filteredLivers)
    }

    // iOS同等の動的プラットフォーム生成（schedules.nameから収集、「レベル」を含むものは除外）
    private fun generateAvailablePlatforms(livers: List<Liver>): List<String> {
        return try {
            val allPlatforms = livers.mapNotNull { liver ->
                try {
                    liver.details?.schedules?.mapNotNull { schedule ->
                        schedule.name?.takeIf { it.isNotBlank() }
                    }
                } catch (e: Exception) {
                    android.util.Log.e("ContentViewModel", "Error processing liver schedules: ${e.message}")
                    null
                }
            }.flatten()

            // 「レベル」を含むプラットフォーム名を除外
            val filteredPlatforms = allPlatforms.filter { platform ->
                !platform.contains("レベル", ignoreCase = true)
            }

            val uniquePlatforms = filteredPlatforms.toSet().toList()

            // iOS同等の優先順序: YouTube, TikTok, Twitch を最初に表示
            val priorityPlatforms = listOf("YouTube", "TikTok", "Twitch")
            val sortedPlatforms = mutableListOf<String>()

            // 優先プラットフォームを最初に追加
            priorityPlatforms.forEach { priority ->
                if (uniquePlatforms.contains(priority)) {
                    sortedPlatforms.add(priority)
                }
            }

            // 残りのプラットフォームをアルファベット順で追加
            val remainingPlatforms = uniquePlatforms.filter { !priorityPlatforms.contains(it) }.sorted()
            sortedPlatforms.addAll(remainingPlatforms)

            android.util.Log.d("ContentViewModel", "Generated platforms: $sortedPlatforms")
            sortedPlatforms

        } catch (e: Exception) {
            android.util.Log.e("ContentViewModel", "Error generating platforms: ${e.message}")
            // フォールバック用の固定プラットフォームリスト
            listOf("YouTube", "TikTok", "Twitch", "Pococha", "IRIAM")
        }
    }

    // iOS同等のプラットフォームフィルタリング（schedules.name との一致でフィルタリング）
    private fun filterByPlatform(platform: String) {
        return try {
            android.util.Log.d("ContentViewModel", "Filtering by platform: $platform")

            val filteredLivers = _uiState.value.allLivers.filter { liver ->
                try {
                    liver.details?.schedules?.any { schedule ->
                        schedule.name?.equals(platform, ignoreCase = true) == true
                    } == true
                } catch (e: Exception) {
                    android.util.Log.e("ContentViewModel", "Error filtering liver by platform: ${e.message}")
                    false
                }
            }

            android.util.Log.d("ContentViewModel", "Platform filtered livers count: ${filteredLivers.size}")
            _uiState.value = _uiState.value.copy(filteredLivers = filteredLivers)
        } catch (e: Exception) {
            android.util.Log.e("ContentViewModel", "Error in filterByPlatform: ${e.message}")
            _uiState.value = _uiState.value.copy(filteredLivers = emptyList())
        }
    }

    fun refresh() {
        loadLivers()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}