package comisapolive.app.ui.screens.home;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<LiverRepository> repositoryProvider;

  public HomeViewModel_Factory(Provider<LiverRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<LiverRepository> repositoryProvider) {
    return new HomeViewModel_Factory(repositoryProvider);
  }

  public static HomeViewModel newInstance(LiverRepository repository) {
    return new HomeViewModel(repository);
  }
}
