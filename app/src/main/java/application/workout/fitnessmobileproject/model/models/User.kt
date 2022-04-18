package application.workout.fitnessmobileproject.model.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    val email: String,
    val password: String,
    val sex: String,
    val growth: Int,
    val weight: Int
)
