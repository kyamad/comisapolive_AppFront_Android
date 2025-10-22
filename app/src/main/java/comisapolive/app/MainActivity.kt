package comisapolive.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import comisapolive.app.ui.navigation.TabScreen
import comisapolive.app.ui.components.CustomTabBar
import comisapolive.app.ui.screens.*
import comisapolive.app.ui.theme.ComisapoliveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { /* no-op */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        requestPostNotificationPermissionIfNeeded()

        setContent {
            ComisapoliveTheme {
                MainScreen()
            }
        }
    }

    private fun requestPostNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

        val permission = Manifest.permission.POST_NOTIFICATIONS
        val hasPermission = ContextCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            requestNotificationPermission.launch(permission)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(TabScreen.HOME) }

    Scaffold(
        bottomBar = {
            CustomTabBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                TabScreen.HOME -> HomeScreen(
                    onSearchClick = { selectedTab = TabScreen.SEARCH }
                )
                TabScreen.CONTENT -> ContentScreen()
                TabScreen.SEARCH -> SearchScreen()
                TabScreen.MYPAGE -> MyPageScreen()
            }
        }
    }
}
