package comisapolive.app

import android.app.Application
// import com.google.android.gms.ads.MobileAds
import android.util.Log
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
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Subscribed to topic: all")
                } else {
                    Log.w(TAG, "Failed to subscribe to topic: all", task.exception)
                }
            }
    }

    companion object {
        private const val TAG = "ComisapoliveApp"
    }
}
