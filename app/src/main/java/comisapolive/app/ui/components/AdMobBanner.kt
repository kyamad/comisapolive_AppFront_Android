package comisapolive.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
// import androidx.compose.ui.viewinterop.AndroidView
// import com.google.android.gms.ads.AdRequest
// import com.google.android.gms.ads.AdSize
// import com.google.android.gms.ads.AdView

@Composable
fun AdMobBanner(
    modifier: Modifier = Modifier,
    adUnitId: String = "ca-app-pub-5103020251808633/9942411882"
) {
    // TODO: 広告表示は一時停止中 - 復活時は下記のコメントアウトを解除
    /*
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.MEDIUM_RECTANGLE)
                    this.adUnitId = adUnitId
                    loadAd(AdRequest.Builder().build())
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
    */
}