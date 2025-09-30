package comisapolive.app.network;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\f\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcomisapolive/app/network/ReviewApiService;", "", "getReviewStats", "Lretrofit2/Response;", "Lcomisapolive/app/model/ReviewStats;", "liverId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReviews", "Lcomisapolive/app/model/ReviewResponse;", "submitReview", "Lcomisapolive/app/model/ReviewSubmissionResponse;", "request", "Lcomisapolive/app/model/ReviewSubmissionRequest;", "(Lcomisapolive/app/model/ReviewSubmissionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface ReviewApiService {
    
    @retrofit2.http.GET(value = "api/reviews/{liverId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReviews(@retrofit2.http.Path(value = "liverId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String liverId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<comisapolive.app.model.ReviewResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/reviews/stats/{liverId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReviewStats(@retrofit2.http.Path(value = "liverId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String liverId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<comisapolive.app.model.ReviewStats>> $completion);
    
    @retrofit2.http.POST(value = "api/reviews")
    @retrofit2.http.Headers(value = {"Content-Type: application/json"})
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitReview(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    comisapolive.app.model.ReviewSubmissionRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<comisapolive.app.model.ReviewSubmissionResponse>> $completion);
}