package comisapolive.app.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006\u00f8\u0001\u0000J\u001b\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006\u00f8\u0001\u0000J\u001b\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006\u00f8\u0001\u0000JK\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u00062\u0006\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e\u00f8\u0001\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcomisapolive/app/repository/LiverRepository;", "", "apiService", "Lcomisapolive/app/network/ApiService;", "(Lcomisapolive/app/network/ApiService;)V", "getCollaborationLivers", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Result;", "", "Lcomisapolive/app/model/Liver;", "getLivers", "getNewLivers", "searchLivers", "query", "", "selectedCategories", "selectedGender", "selectedPlatform", "app_release"})
public final class LiverRepository {
    @org.jetbrains.annotations.NotNull()
    private final comisapolive.app.network.ApiService apiService = null;
    
    @javax.inject.Inject()
    public LiverRepository(@org.jetbrains.annotations.NotNull()
    comisapolive.app.network.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.util.List<comisapolive.app.model.Liver>>> getLivers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.util.List<comisapolive.app.model.Liver>>> getNewLivers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.util.List<comisapolive.app.model.Liver>>> getCollaborationLivers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.util.List<comisapolive.app.model.Liver>>> searchLivers(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> selectedCategories, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedGender, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedPlatform) {
        return null;
    }
}