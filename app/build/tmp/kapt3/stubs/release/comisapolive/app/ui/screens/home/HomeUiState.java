package comisapolive.app.ui.screens.home;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003JO\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u000bH\u00d6\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e\u00a8\u0006\u001f"}, d2 = {"Lcomisapolive/app/ui/screens/home/HomeUiState;", "", "newLivers", "", "Lcomisapolive/app/model/Liver;", "collaborationLivers", "articles", "Lcomisapolive/app/model/Article;", "isLoading", "", "errorMessage", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/lang/String;)V", "getArticles", "()Ljava/util/List;", "getCollaborationLivers", "getErrorMessage", "()Ljava/lang/String;", "()Z", "getNewLivers", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_release"})
public final class HomeUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<comisapolive.app.model.Liver> newLivers = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<comisapolive.app.model.Liver> collaborationLivers = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<comisapolive.app.model.Article> articles = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String errorMessage = null;
    
    public HomeUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<comisapolive.app.model.Liver> newLivers, @org.jetbrains.annotations.NotNull()
    java.util.List<comisapolive.app.model.Liver> collaborationLivers, @org.jetbrains.annotations.NotNull()
    java.util.List<comisapolive.app.model.Article> articles, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<comisapolive.app.model.Liver> getNewLivers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<comisapolive.app.model.Liver> getCollaborationLivers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<comisapolive.app.model.Article> getArticles() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public HomeUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<comisapolive.app.model.Liver> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<comisapolive.app.model.Liver> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<comisapolive.app.model.Article> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final comisapolive.app.ui.screens.home.HomeUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<comisapolive.app.model.Liver> newLivers, @org.jetbrains.annotations.NotNull()
    java.util.List<comisapolive.app.model.Liver> collaborationLivers, @org.jetbrains.annotations.NotNull()
    java.util.List<comisapolive.app.model.Article> articles, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}