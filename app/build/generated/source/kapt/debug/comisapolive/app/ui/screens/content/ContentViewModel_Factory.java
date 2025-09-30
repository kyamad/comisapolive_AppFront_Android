package comisapolive.app.ui.screens.content;

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
public final class ContentViewModel_Factory implements Factory<ContentViewModel> {
  private final Provider<LiverRepository> repositoryProvider;

  public ContentViewModel_Factory(Provider<LiverRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ContentViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ContentViewModel_Factory create(Provider<LiverRepository> repositoryProvider) {
    return new ContentViewModel_Factory(repositoryProvider);
  }

  public static ContentViewModel newInstance(LiverRepository repository) {
    return new ContentViewModel(repository);
  }
}
