package application.workout.fitnessmobileproject.model.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey
    @ColumnInfo(name = "exercise_id")
    val id: Int,
    val name: String,
    val time: Int,
    @ColumnInfo(name = "number_of_approaches")
    val numberOfApproaches: Int,
    val periods: Int,
    //TODO Подумать над этим полем, возможно стоит убрать, так это вес того или иного упражнения для отсчета прогресса
    val weight: Int,
    val image: String
)
