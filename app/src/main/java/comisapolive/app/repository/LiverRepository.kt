package comisapolive.app.repository

import comisapolive.app.model.Liver
import comisapolive.app.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class LiverRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getLivers(): Flow<Result<List<Liver>>> = flow {
        try {
            android.util.Log.d("LiverRepository", "API呼び出し開始: getLivers()")
            val response = apiService.getLivers()
            android.util.Log.d("LiverRepository", "APIレスポンス受信: code=${response.code()}, isSuccessful=${response.isSuccessful}")

            if (response.isSuccessful) {
                val liverResponse = response.body()
                android.util.Log.d("LiverRepository", "レスポンスボディ取得: total=${liverResponse?.total}, dataSize=${liverResponse?.data?.size}")

                val livers = liverResponse?.data ?: emptyList()
                // iOS同等の重複除去ロジック: originalIdベース
                val uniqueLivers = livers.distinctBy { it.originalId }
                android.util.Log.d("LiverRepository", "重複除去後: uniqueSize=${uniqueLivers.size}")

                emit(Result.success(uniqueLivers))
            } else {
                val errorMsg = "API Error: ${response.code()}"
                android.util.Log.e("LiverRepository", errorMsg)
                emit(Result.failure(Exception(errorMsg)))
            }
        } catch (e: Exception) {
            android.util.Log.e("LiverRepository", "API呼び出し例外", e)
            emit(Result.failure(e))
        }
    }

    fun getNewLivers(): Flow<Result<List<Liver>>> = flow {
        try {
            android.util.Log.d("LiverRepository", "API呼び出し開始: getNewLivers()")
            val response = apiService.getLivers()
            android.util.Log.d("LiverRepository", "getNewLivers APIレスポンス: code=${response.code()}")

            if (response.isSuccessful) {
                val liverResponse = response.body()
                val livers = liverResponse?.data ?: emptyList()
                val uniqueLivers = livers.distinctBy { it.originalId }

                // iOS同等のnewLiversロジック: originalIdの数値でソートして上位5件
                val newLivers = uniqueLivers
                    .sortedBy { liver ->
                        liver.originalId.filter { it.isDigit() }.toIntOrNull() ?: Int.MAX_VALUE
                    }
                    .take(5)

                android.util.Log.d("LiverRepository", "新着ライバー取得完了: ${newLivers.size}件")
                emit(Result.success(newLivers))
            } else {
                val errorMsg = "API Error: ${response.code()}"
                android.util.Log.e("LiverRepository", "getNewLivers $errorMsg")
                emit(Result.failure(Exception(errorMsg)))
            }
        } catch (e: Exception) {
            android.util.Log.e("LiverRepository", "getNewLivers例外", e)
            emit(Result.failure(e))
        }
    }

    fun getCollaborationLivers(): Flow<Result<List<Liver>>> = flow {
        try {
            android.util.Log.d("LiverRepository", "API呼び出し開始: getCollaborationLivers()")
            val response = apiService.getLivers()
            android.util.Log.d("LiverRepository", "getCollaborationLivers APIレスポンス: code=${response.code()}")

            if (response.isSuccessful) {
                val liverResponse = response.body()
                val livers = liverResponse?.data ?: emptyList()
                val uniqueLivers = livers.distinctBy { it.originalId }

                // iOS同等のcolaboLiversロジック: collaborationStatus="OK"でフィルタ + ランダム化
                val collaborationLivers = uniqueLivers.filter { liver ->
                    liver.details?.collaborationStatus?.equals("OK", ignoreCase = true) == true
                }.shuffled(Random.Default)

                android.util.Log.d("LiverRepository", "コラボ可能ライバー取得完了: ${collaborationLivers.size}件")
                emit(Result.success(collaborationLivers))
            } else {
                val errorMsg = "API Error: ${response.code()}"
                android.util.Log.e("LiverRepository", "getCollaborationLivers $errorMsg")
                emit(Result.failure(Exception(errorMsg)))
            }
        } catch (e: Exception) {
            android.util.Log.e("LiverRepository", "getCollaborationLivers例外", e)
            emit(Result.failure(e))
        }
    }

    fun searchLivers(
        query: String,
        selectedCategories: List<String> = emptyList(),
        selectedGender: String? = null,
        selectedPlatform: String? = null
    ): Flow<Result<List<Liver>>> = flow {
        try {
            val response = apiService.getLivers()
            if (response.isSuccessful) {
                val liverResponse = response.body()
                val livers = liverResponse?.data ?: emptyList()
                val uniqueLivers = livers.distinctBy { it.originalId }

                val filteredLivers = uniqueLivers.filter { liver ->
                    // iOS同等の高度な検索ロジック
                    val matchesQuery = if (query.isBlank()) true else {
                        liver.name.contains(query, ignoreCase = true) ||
                                liver.details?.categories?.any { it.contains(query, ignoreCase = true) } == true ||
                                liver.details?.profileInfo?.gender?.contains(query, ignoreCase = true) == true ||
                                liver.details?.comments?.any { it.contains(query, ignoreCase = true) } == true ||
                                liver.details?.schedules?.any { schedule ->
                                    schedule.name.contains(query, ignoreCase = true)
                                } == true ||
                                liver.platform.contains(query, ignoreCase = true)
                    }

                    val matchesCategories = if (selectedCategories.isEmpty()) true else {
                        liver.details?.categories?.any { category ->
                            selectedCategories.any { selected ->
                                category.contains(selected, ignoreCase = true)
                            }
                        } == true
                    }

                    val matchesGender = if (selectedGender.isNullOrBlank()) true else {
                        liver.details?.profileInfo?.gender?.contains(selectedGender, ignoreCase = true) == true
                    }

                    val matchesPlatform = if (selectedPlatform.isNullOrBlank()) true else {
                        liver.platform.contains(selectedPlatform, ignoreCase = true) ||
                                liver.details?.schedules?.any { schedule ->
                                    schedule.name.contains(selectedPlatform, ignoreCase = true)
                                } == true
                    }

                    matchesQuery && matchesCategories && matchesGender && matchesPlatform
                }

                emit(Result.success(filteredLivers))
            } else {
                emit(Result.failure(Exception("API Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}