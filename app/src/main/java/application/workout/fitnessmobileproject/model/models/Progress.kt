package application.workout.fitnessmobileproject.model.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class Progress(
    val id: Int,
    val program: Program,
    val user: User,
    val currentExercise: Int
)

@Entity(tableName = "progress_name", foreignKeys =
    [ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["progress_user_id"], onDelete = CASCADE),
    ForeignKey(entity = Program::class, parentColumns = ["program_id"], childColumns = ["progress_program_id"], onDelete = CASCADE)]
)
data class ProgressTable(
    val id: Int,
    @ColumnInfo(name = "progress_program_id")
    val programId: Int,
    @ColumnInfo(name = "progress_user_id")
    //@Relation(entity = User::class, entityColumn = "")
    val userId: Int,
    @ColumnInfo(name = "progress_current_exercise")
    val currentExercise: Int
)


