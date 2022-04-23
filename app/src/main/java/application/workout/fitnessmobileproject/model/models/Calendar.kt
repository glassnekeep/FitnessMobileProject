package application.workout.fitnessmobileproject.model.models
import androidx.room.*
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

@Entity(tableName = "calendar_table", foreignKeys =
    [ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["calendar_user_id"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Program::class, parentColumns = ["program_id"], childColumns = ["calendar_program_id"], onDelete = ForeignKey.CASCADE)]
)
data class DatabaseCalendar(
    @PrimaryKey
    val id: Int,
    val date: String,
    @ColumnInfo(name = "calendar_program_id")
    val programId: Int,
    @ColumnInfo(name = "calendar_user_id")
    val userId: Int
) {
    companion object {
        fun fromDomainObject(calendar: Calendar): DatabaseCalendar =
            DatabaseCalendar(calendar.id, calendar.date, calendar.program.id, calendar.user.id)
    }
}
