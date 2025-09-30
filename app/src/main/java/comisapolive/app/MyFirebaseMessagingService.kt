package comisapolive.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")

            // Handle data payload
            handleDataPayload(remoteMessage.data)
        }

        // Check if message contains a notification payload
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.title, it.body, remoteMessage.data)
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // Send token to server if needed
        sendRegistrationToServer(token)
    }

    private fun handleDataPayload(data: Map<String, String>) {
        // Handle different types of data payloads
        val type = data["type"]
        val liverId = data["liver_id"]
        val url = data["url"]

        when (type) {
            "new_liver" -> {
                // Handle new liver notification
                sendNotification(
                    title = "新着ライバー",
                    body = data["message"] ?: "新しいライバーが追加されました",
                    data = data
                )
            }
            "collaboration" -> {
                // Handle collaboration notification
                sendNotification(
                    title = "コラボ募集",
                    body = data["message"] ?: "新しいコラボ募集があります",
                    data = data
                )
            }
            "general" -> {
                // Handle general notification
                sendNotification(
                    title = data["title"] ?: "コミサポライブ",
                    body = data["message"] ?: "",
                    data = data
                )
            }
        }
    }

    private fun sendNotification(title: String?, body: String?, data: Map<String, String>) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            // Add data to intent for deep linking
            data["liver_id"]?.let { putExtra("liver_id", it) }
            data["url"]?.let { putExtra("url", it) }
            data["type"]?.let { putExtra("notification_type", it) }
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "default_notification_channel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title ?: "コミサポライブ")
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "コミサポライブ通知",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "コミサポライブからの通知"
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun sendRegistrationToServer(token: String) {
        Log.d(TAG, "Sending token to server: $token")
        // TODO: Implement server communication to store the token
        // This would typically involve making an API call to your backend
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}