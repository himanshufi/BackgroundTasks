@file:Suppress("KDocUnresolvedReference")

package com.himanshu.backgroundtasks.db

import android.content.Context
import androidx.room.Room
import com.himanshu.backgroundtasks.db.dao.CommentDao
import com.himanshu.backgroundtasks.db.dao.PhotoDao
import com.himanshu.backgroundtasks.db.dao.PostDao

class DbRepository (private val database: MyLocalDb) {

    companion object {
        private var instance: DbRepository? = null

        /**
         * This will help us in creating a [Singleton] instance of [DbRepository].
         */

        fun getInstance(context: Context): DbRepository {
            return instance ?: synchronized(this) {
                instance ?: DbRepository(initializeDb(context)).also { instance = it }
            }
        }

        private fun initializeDb(context: Context): MyLocalDb {
            return Room.databaseBuilder(
                context,
                MyLocalDb::class.java, "myLocalDb" // This will be name of our db in the storage.
            ).build()
        }
    }

    /**
     *
     * We will access [Dao] from here in all the project.
     *
     */

    fun postDao():PostDao = database.getPostDao()

    fun photoDao():PhotoDao = database.getPhotoDao()

    fun commentDao():CommentDao = database.getCommentDao()

}