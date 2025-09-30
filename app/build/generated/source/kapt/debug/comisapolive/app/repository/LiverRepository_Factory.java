package comisapolive.app.repository;

import comisapolive.app.network.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class LiverRepository_Factory implements Factory<LiverRepository> {
  private final Provider<ApiService> apiServiceProvider;

  public LiverRepository_Factory(Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public LiverRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static LiverRepository_Factory create(Provider<ApiService> apiServiceProvider) {
    return new LiverRepository_Factory(apiServiceProvider);
  }

  public static LiverRepository newInstance(ApiService apiService) {
    return new LiverRepository(apiService);
  }
}
