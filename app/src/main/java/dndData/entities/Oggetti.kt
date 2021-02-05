package dndData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import dndData.TipoOggetto

@Entity(tableName = "oggetti_table",foreignKeys = arrayOf(
        ForeignKey(
                entity = Scheda::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("scheda_id")
                )
        )
)
data class Oggetti(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var scheda_id: Int,
        var nomeOggetto: String,
        var descrizioneOggetto: String?,
        var tipoOggetto: TipoOggetto

)