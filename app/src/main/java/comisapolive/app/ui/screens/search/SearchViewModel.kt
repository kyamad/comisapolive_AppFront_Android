package comisapolive.app.ui.screens.search

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import comisapolive.app.model.Genre
import comisapolive.app.model.Liver
import comisapolive.app.repository.LiverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val searchResults: List<Liver> = emptyList(),
    val searchHistory: List<String> = emptyList(),
    val selectedCategories: List<String> = emptyList(),
    val selectedGender: String? = null,
    val selectedPlatform: String? = null,
    val availablePlatforms: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val hasSearched: Boolean = false,
    val isTextFieldFocused: Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: LiverRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val gson = Gson()

    init {
        loadSearchHistory()
        loadAvailablePlatforms()
    }

    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun search() {
        val currentState = _uiState.value
        if (currentState.query.isBlank() &&
            currentState.selectedCategories.isEmpty() &&
            currentState.selectedGender.isNullOrBlank() &&
            currentState.selectedPlatform.isNullOrBlank()) {
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                repository.searchLivers(
                    query = currentState.query,
                    selectedCategories = currentState.selectedCategories,
                    selectedGender = currentState.selectedGender,
                    selectedPlatform = currentState.selectedPlatform
                ).collect { result ->
                    result.fold(
                        onSuccess = { livers ->
                            _uiState.value = _uiState.value.copy(
                                searchResults = livers,
                                isLoading = false,
                                hasSearched = true
                            )

                            // iOS同等の検索履歴保存ロジック
                            if (currentState.query.isNotBlank()) {
                                saveSearchToHistory(currentState.query)
                            }
                        },
                        onFailure = { e ->
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                errorMessage = "検索に失敗しました: ${e.message}"
                            )
                        }
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "検索に失敗しました: ${e.message}"
                )
            }
        }
    }

    fun selectCategory(category: String) {
        val currentCategories = _uiState.value.selectedCategories.toMutableList()
        if (currentCategories.contains(category)) {
            currentCategories.remove(category)
        } else {
            currentCategories.add(category)
        }
        _uiState.value = _uiState.value.copy(selectedCategories = currentCategories)
    }

    fun selectGender(gender: String?) {
        _uiState.value = _uiState.value.copy(selectedGender = gender)
    }

    fun selectPlatform(platform: String?) {
        _uiState.value = _uiState.value.copy(selectedPlatform = platform)
    }

    fun setTextFieldFocus(focused: Boolean) {
        _uiState.value = _uiState.value.copy(isTextFieldFocused = focused)
    }

    fun selectHistoryItem(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
        search()
    }

    fun clearSearchHistory() {
        sharedPreferences.edit().remove(SEARCH_HISTORY_KEY).apply()
        _uiState.value = _uiState.value.copy(searchHistory = emptyList())
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun clearResults() {
        _uiState.value = _uiState.value.copy(
            searchResults = emptyList(),
            hasSearched = false,
            query = "",
            selectedCategories = emptyList(),
            selectedGender = null,
            selectedPlatform = null
        )
    }

    private fun loadSearchHistory() {
        val historyJson = sharedPreferences.getString(SEARCH_HISTORY_KEY, null)
        if (historyJson != null) {
            try {
                val type = object : TypeToken<List<String>>() {}.type
                val history = gson.fromJson<List<String>>(historyJson, type)
                _uiState.value = _uiState.value.copy(searchHistory = history)
            } catch (e: Exception) {
                // If parsing fails, start with empty history
                _uiState.value = _uiState.value.copy(searchHistory = emptyList())
            }
        }
    }

    private fun saveSearchToHistory(query: String) {
        val currentHistory = _uiState.value.searchHistory.toMutableList()

        // Remove if already exists to avoid duplicates
        currentHistory.remove(query)

        // Add to beginning
        currentHistory.add(0, query)

        // Keep only last 10 items
        if (currentHistory.size > 10) {
            currentHistory.removeAt(currentHistory.size - 1)
        }

        // Save to SharedPreferences
        val historyJson = gson.toJson(currentHistory)
        sharedPreferences.edit().putString(SEARCH_HISTORY_KEY, historyJson).apply()

        _uiState.value = _uiState.value.copy(searchHistory = currentHistory)
    }

    // iOS同等のプラットフォーム動的取得
    private fun loadAvailablePlatforms() {
        viewModelScope.launch {
            try {
                repository.getLivers().collect { result ->
                    result.fold(
                        onSuccess = { livers ->
                            val platforms = mutableSetOf<String>()
                            livers.forEach { liver ->
                                platforms.add(liver.platform)
                                // schedulesからも動的にプラットフォームを取得（iOS同等）
                                liver.details?.schedules?.forEach { schedule ->
                                    platforms.add(schedule.name)
                                }
                            }
                            _uiState.value = _uiState.value.copy(
                                availablePlatforms = platforms.sorted()
                            )
                        },
                        onFailure = {
                            // エラー時はデフォルトプラットフォームを使用
                            _uiState.value = _uiState.value.copy(
                                availablePlatforms = listOf("YouTube", "TikTok", "Pococha", "Twitch", "ツイキャス")
                            )
                        }
                    )
                }
            } catch (e: Exception) {
                // エラー時はデフォルトプラットフォームを使用
                _uiState.value = _uiState.value.copy(
                    availablePlatforms = listOf("YouTube", "TikTok", "Pococha", "Twitch", "ツイキャス")
                )
            }
        }
    }

    companion object {
        private const val SEARCH_HISTORY_KEY = "search_history"
    }
}