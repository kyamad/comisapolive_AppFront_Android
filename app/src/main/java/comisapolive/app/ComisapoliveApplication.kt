package comisapolive.app

import android.app.Application
// import com.google.android.gms.ads.MobileAds
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComisapoliveApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Mobile Ads SDK - 一時停止中
        // MobileAds.initialize(this) {}

        // Subscribe to Firebase topic for push notifications
        FirebaseMessaging.getInstance().subscribeToTopic("all")
    }
}