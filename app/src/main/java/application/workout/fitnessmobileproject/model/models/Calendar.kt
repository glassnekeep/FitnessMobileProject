package application.workout.fitnessmobileproject.model.models
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
