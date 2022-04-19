package application.workout.fitnessmobileproject.model.models
import androidx.room.*
import kotlinx.serialization.Serializable

@Entity(tableName = "settings_table", foreignKeys =
    [ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["child_user_id"], onDelete = ForeignKey.CASCADE)])
@Serializable
data class Settings(
    @PrimaryKey
    //@ColumnInfo(name = "settings_user_id")
    val id: Int,
    @Embedded(prefix = "child_")
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
