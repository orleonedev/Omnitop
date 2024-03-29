package dndData.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.room.TypeConverter
import dndData.LvlCompetenza
import dndData.RuoloGiocatore
import dndData.TipoOggetto
import dndData.TipoScheda
import java.io.ByteArrayOutputStream
import java.io.File

class CustomConverters {

    @TypeConverter
    fun toRuoloGiocatore(value: String) = enumValueOf<RuoloGiocatore>(value)

    @TypeConverter
    fun fromRuoloGiocatore(value: RuoloGiocatore) = value.name

    @TypeConverter
    fun toTipoScheda(value: String) = enumValueOf<TipoScheda>(value)

    @TypeConverter
    fun fromTipoScheda(value: TipoScheda) = value.name

    @TypeConverter
    fun toLvlCompetenza(value: String) = enumValueOf<LvlCompetenza>(value)

    @TypeConverter
    fun fromLvlCompetenza(value: LvlCompetenza) = value.name

    @TypeConverter
    fun toTipoOggetto(value: String) = enumValueOf<TipoOggetto>(value)

    @TypeConverter
    fun fromTipoOggetto(value: TipoOggetto) = value.name

    @TypeConverter
    fun fromByteArrayToBitmap(byteArray: ByteArray?): Bitmap? {
        if (byteArray != null) {
            return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
        }
        return null
    }

    @TypeConverter
    fun fromBitmapToByteArray(bitmap: Bitmap?): ByteArray?{
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG,20, outputStream)
        return outputStream.toByteArray()
    }

}