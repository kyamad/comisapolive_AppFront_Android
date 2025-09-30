package comisapolive.app.model

import com.google.gson.annotations.SerializedName

// MARK: - Data Models
data class LiverResponse(
    val timestamp: String?,  // ISO日付文字列に変更
    val total: Int?,
    val data: List<Liver>?
)

data class Liver(
    val id: String,
    @SerializedName("originalId") val originalId: String,
    val name: String,
    val platform: String,
    val followers: Int,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("actualImageUrl") val actualImageUrl: String?,
    @SerializedName("detailUrl") val detailUrl: String?,
    @SerializedName("pageNumber") val pageNumber: Int?,
    @SerializedName("updatedAt") val updatedAt: Long?,
    val details: LiverDetailsData?
) {

    private val baseURL = "https://liver-scraper-main.pwaserve8.workers.dev"

    val fullImageURL: String
        get() {
            // iOS同等の優先順位: profileImages.url → imageUrl → actualImageUrl → originalIdベース
            details?.profileImages?.firstOrNull()?.url?.let { profileUrl ->
                if (profileUrl.isNotEmpty()) {
                    return profileUrl // 既に完全なURL
                }
            }

            if (imageUrl.isNotEmpty()) {
                return baseURL + imageUrl
            }

            actualImageUrl?.let { actualUrl ->
                if (actualUrl.isNotEmpty()) {
                    return baseURL + actualUrl
                }
            }

            return "$baseURL/api/images/$originalId.jpg"
        }

    val followerDisplayText: String
        get() = when {
            followers >= 10000 -> "${followers / 1000}K人"
            followers >= 1000 -> String.format("%.1fK人", followers / 1000.0)
            else -> "${followers}人"
        }

    val mainComment: String
        get() = details?.comments?.firstOrNull() ?: "よろしくお願いします！"

    val channelUrl: String
        get() {
            // 優先順位：streamingUrls → detailUrl → デフォルト
            details?.streamingUrls?.firstOrNull()?.url?.let { streamingUrl ->
                if (streamingUrl.isNotEmpty()) {
                    return streamingUrl
                }
            }

            detailUrl?.let { url ->
                if (url.isNotEmpty()) {
                    return url
                }
            }

            return "https://www.youtube.com/channel/UCvycHCl3r3v_MYYPI_brTag"
        }

    // 下位互換性のための計算プロパティ
    val collaborationStatus: String? get() = details?.collaborationStatus
    val collaborationComment: String? get() = details?.collaborationComment
    val collaboOK: String? get() = details?.collaborationComment
    val detailName: String? get() = details?.detailName
    val detailFollowers: String? get() = details?.detailFollowers
    val categories: List<String>? get() = details?.categories?.distinct() // 重複除去
    val profileInfo: ProfileInfo? get() = details?.profileInfo
    val profileImages: List<ProfileImage>? get() = details?.profileImages
    val eventInfo: List<String>? get() = details?.eventInfo
    val comments: List<String>? get() = details?.comments
    val schedules: List<Schedule>? get() = details?.schedules
}

// 新しいAPI構造に対応した詳細情報
data class LiverDetailsData(
    val categories: List<String>?,
    @SerializedName("detailName") val detailName: String?,
    @SerializedName("detailFollowers") val detailFollowers: String?,
    @SerializedName("profileImages") val profileImages: List<ProfileImage>?,
    @SerializedName("collaborationStatus") val collaborationStatus: String?,
    @SerializedName("collaborationComment") val collaborationComment: String?,
    @SerializedName("profileInfo") val profileInfo: ProfileInfo?,
    @SerializedName("rawProfileTexts") val rawProfileTexts: List<String>?,
    @SerializedName("eventInfo") val eventInfo: List<String>?,
    val comments: List<String>?,
    val schedules: List<Schedule>?,
    @SerializedName("streamingUrls") val streamingUrls: List<StreamingUrl>?,
    @SerializedName("genderFound") val genderFound: GenderInfo?
)

data class ProfileImage(
    val url: String?,
    @SerializedName("originalUrl") val originalUrl: String?
)

data class ProfileInfo(
    val gender: String?,
    @SerializedName("streamingHistory") val streamingHistory: String?,
    val birthday: String?,
    val age: Int?,
    val height: Int?
)

data class Schedule(
    val name: String,
    val url: String?,
    val followers: String?
)

data class StreamingUrl(
    val url: String?,
    val type: String?,
    val source: String?
)

data class GenderInfo(
    val gender: String?,
    val confidence: Double?
)

data class MediaLink(
    val url: String?,
    val text: String?
)

data class Article(
    val id: Int,
    val title: String,
    val imageResource: String,
    val url: String
)

enum class Genre(val displayName: String, val iconResource: String) {
    GAME("ゲーム", "game"),
    SONG("歌・音楽", "ongaku"),
    TALK("雑談", "zatudan"),
    ART("絵・アート", "art"),
    COOKING("料理", "cooking"),
    COSPLAY("コスプレ", "cosplay"),
    FPS("FPS", "fps"),
    PERSONAL("個人勢", "kojin"),
    MODEL("モデル", "model"),
    COMEDY("お笑い", "owarai"),
    RADIO("ラジオ", "radio"),
    CONSULTATION("相談", "soudan"),
    STREAMER("ストリーマー", "streamer"),
    TRAINING("配信練習", "training"),
    FORTUNE("占い", "uranai"),
    UTA("歌", "uta"),
    VLIVER("Vライバー", "vliver"),
    VSTREAMER("Vストリーマー", "vstreamer")
}

enum class Platform(val displayName: String, val iconResource: String) {
    YOUTUBE("YouTube", "youtube"),
    TIKTOK("TikTok", "tiktok"),
    POCOCHA("Pococha", "pococha"),
    TWITCH("Twitch", "twitch"),
    TWICAS("ツイキャス", "twicas"),
    NICONICO("ニコニコ", "niconico"),
    MIXCH("MixCh", "mixch"),
    IRIAM("IRIAM", "iriam"),
    HAKUNA("HAKUNA", "hakuna"),
    BIGO("BIGO LIVE", "bigo"),
    SEVENTEEN_LIVE("17LIVE", "17live")
}