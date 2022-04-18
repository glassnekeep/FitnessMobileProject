package application.workout.fitnessmobileproject.model.models
import androidx.room.*
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Program(
    @PrimaryKey
    @ColumnInfo(name = "program_id")
    val id: Int,
    val name: String,
    val interval: Int,
    @Ignore
    val exercise: List<Exercise>,
    @Ignore
    val users: List<User>,
    val image: String
    //TODO подумать о том чтобы тут было максимальное число очков за данную программу
)

@Entity(primaryKeys = ["program_id", "user_id"])
data class ProgramWithExercises(
    val programId: String,
    val userId: String
)
