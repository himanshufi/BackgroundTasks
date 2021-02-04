package com.himanshu.backgroundtasks.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentModel(
    val postId:Int,
    @PrimaryKey val id:Int,
    val email:String,
    val name:String,
    val body:String
)