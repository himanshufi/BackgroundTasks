@file:Suppress("KDocUnresolvedReference")
package com.himanshu.backgroundtasks.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.himanshu.backgroundtasks.db.dao.CommentDao
import com.himanshu.backgroundtasks.db.dao.PhotoDao
import com.himanshu.backgroundtasks.db.dao.PostDao
import com.himanshu.backgroundtasks.db.models.CommentModel
import com.himanshu.backgroundtasks.db.models.PhotoModel
import com.himanshu.backgroundtasks.db.models.PostModel

/**
 * In the entities, you will have to add all the class which you have annotated as [Entity],
 * like we have done for [PostModel].
 */

@Database(entities = [
    PostModel::class,
    CommentModel::class,
    PhotoModel::class], version = 1)
abstract class MyLocalDb: RoomDatabase() {

    /**
     * Write all the [Dao] as abstract fun here.
     */

    abstract fun getPostDao(): PostDao
    abstract fun getPhotoDao(): PhotoDao
    abstract fun getCommentDao(): CommentDao

}