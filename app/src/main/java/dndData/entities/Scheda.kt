package dndData.entities

import android.graphics.Bitmap
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import dndData.TipoScheda
import dndData.utilData.*


@Entity(tableName = "Scheda_table",
foreignKeys = arrayOf(
    ForeignKey(
        onDelete = CASCADE ,
        entity = Campagna::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("campagna_id")
        )
    ),
    indices = arrayOf(Index(name = "campagna_id_indexSchede", value = ["campagna_id"]))
)
data class Scheda(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "campagna_id") val Campagnaid: Int,
        var nomePG: String,
        var tipoScheda: TipoScheda,

        @Embedded
        var statistiche: Statistiche?,

        @Embedded
        var incantatore: Incantatore?,

        @Embedded
        var dettagli: Dettagli?,

        @Embedded
        var moneteTotali: Money?,

        var imgSchedaBitmap: Bitmap?

)


