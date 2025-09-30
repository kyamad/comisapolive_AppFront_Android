package comisapolive.app.ui.screens.review;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u001e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcomisapolive/app/ui/screens/review/ReviewViewModel;", "Landroidx/lifecycle/ViewModel;", "reviewRepository", "Lcomisapolive/app/repository/ReviewRepository;", "(Lcomisapolive/app/repository/ReviewRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcomisapolive/app/ui/screens/review/ReviewUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearMessages", "", "loadReviews", "liverId", "", "submitReview", "rating", "", "comment", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReviewViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final comisapolive.app.repository.ReviewRepository reviewRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<comisapolive.app.ui.screens.review.ReviewUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<comisapolive.app.ui.screens.review.ReviewUiState> uiState = null;
    
    @javax.inject.Inject()
    public ReviewViewModel(@org.jetbrains.annotations.NotNull()
    comisapolive.app.repository.ReviewRepository reviewRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<comisapolive.app.ui.screens.review.ReviewUiState> getUiState() {
        return null;
    }
    
    public final void loadReviews(@org.jetbrains.annotations.NotNull()
    java.lang.String liverId) {
    }
    
    public final void submitReview(@org.jetbrains.annotations.NotNull()
    java.lang.String liverId, int rating, @org.jetbrains.annotations.NotNull()
    java.lang.String comment) {
    }
    
    public final void clearMessages() {
    }
}