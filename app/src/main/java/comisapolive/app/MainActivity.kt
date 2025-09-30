package comisapolive.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import comisapolive.app.ui.navigation.TabScreen
import comisapolive.app.ui.components.CustomTabBar
import comisapolive.app.ui.screens.*
import comisapolive.app.ui.theme.ComisapoliveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComisapoliveTheme {
                MainScreen()
            }
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