package com.himanshu.backgroundtasks.db.dao

import androidx.room.*
import com.himanshu.backgroundtasks.db.models.CommentModel

@Dao
interface CommentDao {

    /**
     *
     * To insert a single [CommentModel] item into the db.
     *
     * @Insert(onConflict = OnConflictStrategy.REPLACE)
     * This above line will replace the existing item with the new item in the db,
     * if a new item needs to inserted with the same [PrimaryKey].
     *
     * suspend fun insertItem(item: CommentModel)
     *
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(item: List<CommentModel>)

    /**
     *
     * To fetch all [CommentModel] from the db.
     *
     * @Query("SELECT * FROM PhotoModel")
     * suspend fun getAllItems(): List<CommentModel>
     *
     * You can also fetch the items as LiveData.
     * fun getAllItems(): LiveData<List<CommentModel>>
     *
     */

    @Query("SELECT * FROM CommentModel")
    suspend fun getItemsCount(): List<CommentModel>


    /**
     *
     * To delete a [CommentModel] from the db.
     *
     * @Delete
     * suspend fun deleteItem(item: CommentModel)
     *
     */

}