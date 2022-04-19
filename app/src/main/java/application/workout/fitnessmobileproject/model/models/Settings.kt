package application.workout.fitnessmobileproject.model.models
import androidx.room.*
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Settings(
    @PrimaryKey
    @ColumnInfo(name = "settings_user_id")
    val id: Int,
    @Embedded
    val user: User,
    val restTime: Int,
    val countDownTime: Int
)

data class UserAndSettings(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "settings_user_id"
    )
    val settings: Settings
)
