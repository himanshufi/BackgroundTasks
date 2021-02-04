package com.himanshu.backgroundtasks.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostModel(
        val userId:Int,
        @PrimaryKey val id:Int,
        val title:String,
        val body:String
)