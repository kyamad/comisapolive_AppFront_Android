package comisapolive.app.repository;

import android.content.SharedPreferences;
import comisapolive.app.network.ReviewApiService;
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
public final class ReviewRepository_Factory implements Factory<ReviewRepository> {
  private final Provider<ReviewApiService> reviewApiServiceProvider;

  private final Provider<SharedPreferences> sharedPreferencesProvider;

  public ReviewRepository_Factory(Provider<ReviewApiService> reviewApiServiceProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    this.reviewApiServiceProvider = reviewApiServiceProvider;
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public ReviewRepository get() {
    return newInstance(reviewApiServiceProvider.get(), sharedPreferencesProvider.get());
  }

  public static ReviewRepository_Factory create(Provider<ReviewApiService> reviewApiServiceProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    return new ReviewRepository_Factory(reviewApiServiceProvider, sharedPreferencesProvider);
  }

  public static ReviewRepository newInstance(ReviewApiService reviewApiService,
      SharedPreferences sharedPreferences) {
    return new ReviewRepository(reviewApiService, sharedPreferences);
  }
}
