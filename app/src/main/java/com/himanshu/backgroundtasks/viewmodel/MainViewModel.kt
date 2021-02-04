package com.himanshu.backgroundtasks.viewmodel

import androidx.lifecycle.ViewModel
import com.himanshu.backgroundtasks.App

class MainViewModel: ViewModel() {

    fun observePostsInDb() = App.db.postDao().getPostCount()

}