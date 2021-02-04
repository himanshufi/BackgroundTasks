@file:Suppress("KDocUnresolvedReference")
package com.himanshu.backgroundtasks.network.base

import com.himanshu.backgroundtasks.db.models.CommentModel
import com.himanshu.backgroundtasks.db.models.PhotoModel
import com.himanshu.backgroundtasks.db.models.PostModel
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    /**
     * [SmallData] You will get around 100 rows of data with this API.
     */

    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostModel>>

    /**
     * [MediumData] You will get around 500 rows of data with this API.
     */

    @GET("comments")
    suspend fun getAllComments(): Response<List<CommentModel>>

    /**
     * [LargeData] You will get around 5000 rows of data with this API.
     */

    @GET("posts")
    suspend fun getAllPhotos(): Response<List<PhotoModel>>


}