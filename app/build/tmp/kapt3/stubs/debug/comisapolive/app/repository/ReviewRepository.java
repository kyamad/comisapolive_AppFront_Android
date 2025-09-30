package comisapolive.app.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\f\u00f8\u0001\u0000J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0002J#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\t0\b2\u0006\u0010\u000b\u001a\u00020\f\u00f8\u0001\u0000J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fH\u0002J-\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\f\u00f8\u0001\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcomisapolive/app/repository/ReviewRepository;", "", "reviewApiService", "Lcomisapolive/app/network/ReviewApiService;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Lcomisapolive/app/network/ReviewApiService;Landroid/content/SharedPreferences;)V", "getReviewStats", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Result;", "Lcomisapolive/app/model/ReviewStats;", "liverId", "", "getReviewedLivers", "", "getReviews", "", "Lcomisapolive/app/model/Review;", "hasReviewedLiver", "", "markLiverAsReviewed", "", "submitReview", "rating", "", "comment", "Companion", "app_debug"})
public final class ReviewRepository {
    @org.jetbrains.annotations.NotNull()
    private final comisapolive.app.network.ReviewApiService reviewApiService = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String REVIEWED_LIVERS_KEY = "reviewed_livers";
    @org.jetbrains.annotations.NotNull()
    public static final comisapolive.app.repository.ReviewRepository.Companion Companion = null;
    
    @javax.inject.Inject()
    public ReviewRepository(@org.jetbrains.annotations.NotNull()
    comisapolive.app.network.ReviewApiService reviewApiService, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.util.List<comisapolive.app.model.Review>>> getReviews(@org.jetbrains.annotations.NotNull()
    java.lang.String liverId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<comisapolive.app.model.ReviewStats>> getReviewStats(@org.jetbrains.annotations.NotNull()
    java.lang.String liverId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.lang.Boolean>> submitReview(@org.jetbrains.annotations.NotNull()
    java.lang.String liverId, int rating, @org.jetbrains.annotations.NotNull()
    java.lang.String comment) {
        return null;
    }
    
    public final boolean hasReviewedLiver(@org.jetbrains.annotations.NotNull()
    java.lang.String liverId) {
        return false;
    }
    
    private final void markLiverAsReviewed(java.lang.String liverId) {
    }
    
    private final java.util.Set<java.lang.String> getReviewedLivers() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcomisapolive/app/repository/ReviewRepository$Companion;", "", "()V", "REVIEWED_LIVERS_KEY", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}