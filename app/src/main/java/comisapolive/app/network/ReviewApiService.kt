package comisapolive.app.network

import comisapolive.app.model.ReviewResponse
import comisapolive.app.model.ReviewStats
import comisapolive.app.model.ReviewSubmissionRequest
import comisapolive.app.model.ReviewSubmissionResponse
import retrofit2.Response
import retrofit2.http.*

interface ReviewApiService {

    @GET("api/reviews/{liverId}")
    suspend fun getReviews(@Path("liverId") liverId: String): Response<ReviewResponse>

    @GET("api/reviews/stats/{liverId}")
    suspend fun getReviewStats(@Path("liverId") liverId: String): Response<ReviewStats>

    @POST("api/reviews")
    @Headers("Content-Type: application/json")
    suspend fun submitReview(@Body request: ReviewSubmissionRequest): Response<ReviewSubmissionResponse>
}