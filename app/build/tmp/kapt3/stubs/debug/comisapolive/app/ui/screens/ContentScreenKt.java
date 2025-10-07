package comisapolive.app.ui.screens;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0012\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0003\u001a&\u0010\u0007\u001a\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a&\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0003\u001a&\u0010\u0012\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a&\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0003\u001a&\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0003\u001a\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0002\u00a8\u0006\u001f"}, d2 = {"ContentScreen", "", "viewModel", "Lcomisapolive/app/ui/screens/content/ContentViewModel;", "EmptyResultMessage", "message", "", "GenreGrid", "selectedGenre", "Lcomisapolive/app/model/Genre;", "onGenreSelected", "Lkotlin/Function1;", "GenreItem", "genre", "isSelected", "", "onClick", "Lkotlin/Function0;", "PlatformGrid", "selectedPlatform", "Lcomisapolive/app/model/Platform;", "onPlatformSelected", "PlatformItem", "platform", "SelectionHeader", "title", "count", "", "onClear", "getImageNameForPlatform", "platformName", "app_debug"})
public final class ContentScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ContentScreen(@org.jetbrains.annotations.NotNull()
    comisapolive.app.ui.screens.content.ContentViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SelectionHeader(java.lang.String title, int count, kotlin.jvm.functions.Function0<kotlin.Unit> onClear) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyResultMessage(java.lang.String message) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GenreGrid(comisapolive.app.model.Genre selectedGenre, kotlin.jvm.functions.Function1<? super comisapolive.app.model.Genre, kotlin.Unit> onGenreSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GenreItem(comisapolive.app.model.Genre genre, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PlatformGrid(comisapolive.app.model.Platform selectedPlatform, kotlin.jvm.functions.Function1<? super comisapolive.app.model.Platform, kotlin.Unit> onPlatformSelected) {
    }
    
    private static final java.lang.String getImageNameForPlatform(java.lang.String platformName) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PlatformItem(comisapolive.app.model.Platform platform, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}