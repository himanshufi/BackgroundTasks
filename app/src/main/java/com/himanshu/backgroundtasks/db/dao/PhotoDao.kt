package com.himanshu.backgroundtasks.db.dao

import androidx.room.*
import com.himanshu.backgroundtasks.db.models.PhotoModel

@Dao
interface PhotoDao {

    /**
     *
     * To insert a single [PhotoModel] item into the db.
     *
     * @Insert(onConflict = OnConflictStrategy.REPLACE)
     * This above line will replace the existing item with the new item in the db,
     * if a new item needs to inserted with the same [PrimaryKey].
     *
     * suspend fun insertItem(item: PhotoModel)
     *
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(item: List<PhotoModel>)

    /**
     *
     * To fetch all [PhotoModel] from the db.
     *
     * @Query("SELECT * FROM PhotoModel")
     * suspend fun getAllItems(): List<PhotoModel>
     *
     * You can also fetch the items as LiveData.
     * fun getAllItems(): LiveData<List<PhotoModel>>
     *
     */

    @Query("SELECT * FROM PhotoModel")
    suspend fun getItemsCount(): List<PhotoModel>


    /**
     *
     * To delete a [PhotoModel] from the db.
     *
     * @Delete
     * suspend fun deleteItem(item: PhotoModel)
     *
     */

}