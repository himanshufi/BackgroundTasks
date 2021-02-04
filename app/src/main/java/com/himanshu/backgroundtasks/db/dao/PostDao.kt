package com.himanshu.backgroundtasks.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.himanshu.backgroundtasks.db.models.PostModel

@Dao
interface PostDao {

    /**
     *
     * To insert a single [PostModel] item into the db.
     *
     * @Insert(onConflict = OnConflictStrategy.REPLACE)
     * This above line will replace the existing item with the new item in the db,
     * if a new item needs to inserted with the same [PrimaryKey].
     *
     * suspend fun insertItem(item: PostModel)
     *
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(item: List<PostModel>): List<Long>

    /**
     *
     * To fetch all [PostModel] from the db.
     *
     * @Query("SELECT * FROM PostModel")
     * suspend fun getAllItems(): List<PostModel>
     *
     * Note: The function which returns [LiveData] cannot
     * be suspended using [suspend] keyword.
     */

    @Query("SELECT * FROM PostModel")
    fun getPostCount(): LiveData<List<PostModel>>


    /**
     *
     * To delete a [PostModel] from the db.
     *
     * @Delete
     * suspend fun deleteItem(item: PostModel)
     *
     */

    /**
     *
     * It will [Delete] all the data in the [PostModel] table.
     *
     */

    @Query("DELETE FROM PostModel")
    suspend fun deleteAll()

}