package application.workout.fitnessmobileproject.model.models
import android.os.Parcelable
import androidx.room.*
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "program_table")
data class Program(
    @PrimaryKey
    @ColumnInfo(name = "program_id")
    val id: Int,
    val name: String,
    val interval: Int,
    //@Relation(associateBy = Junction(ProgramToExerciseRef::class), parentColumn = "program_id", entityColumn = "exerciseId")
    val exercise: List<Exercise>,
    //@Relation(associateBy = Junction(ProgramToUserRef::class), parentColumn = "program_id", entityColumn = "userId")
    val users: List<User>,
    val image: String,
    val numberOfExercises: Int
    //TODO подумать о том чтобы тут было максимальное число очков за данную программу
)
/*@Entity(primaryKeys = ["program_id", "exerciseId"])
data class ProgramToExerciseRef(
    @ColumnInfo(name = "program_id")
    val programId: Int,
    val exerciseId: Int
)

@Entity(primaryKeys = ["program_id", "userId"])
data class ProgramToUserRef(
    @ColumnInfo(name = "program_id")
    val programId: Int,
    val userId: Int
)*/