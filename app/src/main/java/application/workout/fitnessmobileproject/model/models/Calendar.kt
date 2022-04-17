package application.workout.fitnessmobileproject.model.models
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import application.workout.fitnessmobileproject.model.dao.UserDao
import kotlinx.serialization.Serializable

@Serializable
data class Calendar(
    val id: Int,
    //TODO Разобраться вот тут с типом данных для даты
    val date: String,
    //val exercise: Exercise,
    val program: Program,
    val user: User
)

@Entity(tableName = "calendar_table")
data class DatabaseCalendar(
    @PrimaryKey
    val id: Int,
    val date: String,
    @ColumnInfo(name = "program_id")
    val programId: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int
) {
    companion object {
        fun fromDomainObject(calendar: Calendar): DatabaseCalendar =
            DatabaseCalendar(calendar.id, calendar.date, calendar.program.id, calendar.user.id)
    }
}
