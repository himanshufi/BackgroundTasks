@file:Suppress("KDocUnresolvedReference")

package com.himanshu.backgroundtasks

class Overview {

    /**
     *
     * List of branches:
     * 1. master : You are here!!
     * 2. br_with_wk
     *
     */


    /**
     *
     * [Documentation]
     *
     * 1. br_with_wk :
     *
     * In this branch, we are executing a background task using `WorkManager` and `AlarmManager`.
     * The Work is being executed using `CoroutineWorker`, once all the constraints are satisfied.
     * Once the work is done, we will show a notification which displays the status of the operation
     * which is executed in the `Worker`.
     *
     * Also, we are observing a table in the DB. If there is any change occurs in the data of that
     * table then the changes will be reflected on the UI.
     *
     */
}