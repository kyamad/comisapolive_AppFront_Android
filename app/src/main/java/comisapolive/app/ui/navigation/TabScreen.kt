package comisapolive.app.ui.navigation

enum class TabScreen(val route: String, val title: String, val iconResource: String) {
    HOME("home", "ホーム", "home"),
    CONTENT("content", "カテゴリ", "menu"),
    SEARCH("search", "探す", "search1"),  // iOS同等の"search 1"
    MYPAGE("mypage", "マイページ", "mypage1")  // iOS同等の"mypage 1"
}