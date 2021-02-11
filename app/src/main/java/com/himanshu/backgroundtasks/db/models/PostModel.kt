package com.himanshu.backgroundtasks.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostModel(
        val userId:Long,
        @PrimaryKey val id:Long,
        val title:String,
        val body:String
)