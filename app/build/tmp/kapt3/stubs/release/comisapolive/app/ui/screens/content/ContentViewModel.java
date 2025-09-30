package comisapolive.app.ui.screens.content;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0016H\u0002J\b\u0010\u0019\u001a\u00020\rH\u0002J\u0006\u0010\u001a\u001a\u00020\rJ\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u001c\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u000e\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006 "}, d2 = {"Lcomisapolive/app/ui/screens/content/ContentViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcomisapolive/app/repository/LiverRepository;", "(Lcomisapolive/app/repository/LiverRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcomisapolive/app/ui/screens/content/ContentUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "clearSelection", "filterByGenre", "genre", "Lcomisapolive/app/model/Genre;", "filterByPlatform", "platform", "", "generateAvailablePlatforms", "", "livers", "Lcomisapolive/app/model/Liver;", "loadLivers", "refresh", "selectGenre", "selectPlatform", "selectTab", "tab", "Lcomisapolive/app/ui/screens/content/ContentTab;", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ContentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final comisapolive.app.repository.LiverRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<comisapolive.app.ui.screens.content.ContentUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<comisapolive.app.ui.screens.content.ContentUiState> uiState = null;
    
    @javax.inject.Inject()
    public ContentViewModel(@org.jetbrains.annotations.NotNull()
    comisapolive.app.repository.LiverRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<comisapolive.app.ui.screens.content.ContentUiState> getUiState() {
        return null;
    }
    
    public final void selectTab(@org.jetbrains.annotations.NotNull()
    comisapolive.app.ui.screens.content.ContentTab tab) {
    }
    
    public final void selectGenre(@org.jetbrains.annotations.NotNull()
    comisapolive.app.model.Genre genre) {
    }
    
    public final void selectPlatform(@org.jetbrains.annotations.Nullable()
    java.lang.String platform) {
    }
    
    public final void clearSelection() {
    }
    
    private final void loadLivers() {
    }
    
    private final void filterByGenre(comisapolive.app.model.Genre genre) {
    }
    
    private final java.util.List<java.lang.String> generateAvailablePlatforms(java.util.List<comisapolive.app.model.Liver> livers) {
        return null;
    }
    
    private final void filterByPlatform(java.lang.String platform) {
    }
    
    public final void refresh() {
    }
    
    public final void clearError() {
    }
}