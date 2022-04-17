package application.workout.fitnessmobileproject.model.dao

import androidx.room.Dao
import androidx.room.Query
import application.workout.fitnessmobileproject.model.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserWithId(id: Int) : User


}