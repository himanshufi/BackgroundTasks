package com.himanshu.backgroundtasks.network

import com.himanshu.backgroundtasks.db.models.CommentModel
import com.himanshu.backgroundtasks.db.models.PhotoModel
import com.himanshu.backgroundtasks.db.models.PostModel
import com.himanshu.backgroundtasks.network.base.ApiInterface
import retrofit2.Response

object NetworkRepository {

    suspend fun getAllPosts(): Response<List<PostModel>> {
        return ApiInterface.getApiService().getAllPosts()
    }

    suspend fun getAllComments(): Response<List<CommentModel>> {
        return ApiInterface.getApiService().getAllComments()
    }

    suspend fun getAllPhotos(): Response<List<PhotoModel>> {
        return ApiInterface.getApiService().getAllPhotos()
    }

}