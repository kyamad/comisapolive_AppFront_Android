package comisapolive.app.ui.screens.search;

import android.content.SharedPreferences;
import comisapolive.app.repository.LiverRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<LiverRepository> repositoryProvider;

  private final Provider<SharedPreferences> sharedPreferencesProvider;

  public SearchViewModel_Factory(Provider<LiverRepository> repositoryProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    this.repositoryProvider = repositoryProvider;
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(repositoryProvider.get(), sharedPreferencesProvider.get());
  }

  public static SearchViewModel_Factory create(Provider<LiverRepository> repositoryProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    return new SearchViewModel_Factory(repositoryProvider, sharedPreferencesProvider);
  }

  public static SearchViewModel newInstance(LiverRepository repository,
      SharedPreferences sharedPreferences) {
    return new SearchViewModel(repository, sharedPreferences);
  }
}
