package comisapolive.app.network

import comisapolive.app.model.LiverResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/livers")
    suspend fun getLivers(): Response<LiverResponse>
}