package comisapolive.app.ui.screens.search;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u0011J\u000e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0018J\u0010\u0010\u001c\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u0018J\u000e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u001f\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u0018J\u000e\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006&"}, d2 = {"Lcomisapolive/app/ui/screens/search/SearchViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcomisapolive/app/repository/LiverRepository;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Lcomisapolive/app/repository/LiverRepository;Landroid/content/SharedPreferences;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcomisapolive/app/ui/screens/search/SearchUiState;", "gson", "Lcom/google/gson/Gson;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "clearResults", "clearSearchHistory", "loadAvailablePlatforms", "loadSearchHistory", "saveSearchToHistory", "query", "", "search", "selectCategory", "category", "selectGender", "gender", "selectHistoryItem", "selectPlatform", "platform", "setTextFieldFocus", "focused", "", "updateQuery", "Companion", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SearchViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final comisapolive.app.repository.LiverRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<comisapolive.app.ui.screens.search.SearchUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<comisapolive.app.ui.screens.search.SearchUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SEARCH_HISTORY_KEY = "search_history";
    @org.jetbrains.annotations.NotNull()
    public static final comisapolive.app.ui.screens.search.SearchViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public SearchViewModel(@org.jetbrains.annotations.NotNull()
    comisapolive.app.repository.LiverRepository repository, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<comisapolive.app.ui.screens.search.SearchUiState> getUiState() {
        return null;
    }
    
    public final void updateQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void search() {
    }
    
    public final void selectCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category) {
    }
    
    public final void selectGender(@org.jetbrains.annotations.Nullable()
    java.lang.String gender) {
    }
    
    public final void selectPlatform(@org.jetbrains.annotations.Nullable()
    java.lang.String platform) {
    }
    
    public final void setTextFieldFocus(boolean focused) {
    }
    
    public final void selectHistoryItem(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void clearSearchHistory() {
    }
    
    public final void clearError() {
    }
    
    public final void clearResults() {
    }
    
    private final void loadSearchHistory() {
    }
    
    private final void saveSearchToHistory(java.lang.String query) {
    }
    
    private final void loadAvailablePlatforms() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcomisapolive/app/ui/screens/search/SearchViewModel$Companion;", "", "()V", "SEARCH_HISTORY_KEY", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}