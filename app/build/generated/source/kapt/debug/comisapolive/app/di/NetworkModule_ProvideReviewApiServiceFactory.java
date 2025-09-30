package comisapolive.app.di;

import comisapolive.app.network.ReviewApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideReviewApiServiceFactory implements Factory<ReviewApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideReviewApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ReviewApiService get() {
    return provideReviewApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideReviewApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideReviewApiServiceFactory(retrofitProvider);
  }

  public static ReviewApiService provideReviewApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideReviewApiService(retrofit));
  }
}
