# BackgroundTasks

This project contains different use cases of `Background Tasks` with the implementation. 

You will find different implementation on different branches.

### Branch Details:

1. `br_with_wk` : In this, we have implemented the execution of a background task using `WorkManager`
 and `AlarmManager`. The `Coroutine Worker` of `WorkManager` is used to execute the background task,
 once all the constraints are satisfied.
<br><br>
We are also showing a <b>notification</b> with a button to <b>re-execute</b> the task using a 
`AlarmManager`, once the `work` is executed.
<br><br>
Also, we are observing the table's data to reflect the changes on UI in case of any change.
<br><br>
<b>Following are the tasks which are executed in the `WorkManager`: </b>
 * Network call: To fetch the data.
 * Database operation: To save the data into the db.

### Note: The operations will execute even if the app is closed.
