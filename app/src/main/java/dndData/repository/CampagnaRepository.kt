package dndData.repository

import androidx.lifecycle.LiveData
import androidx.room.Update
import dndData.dao.CampagnaDAO
import dndData.entities.*
import dndData.relationData.SchedeCampagna

class CampagnaRepository(private val CampagnaDAO: CampagnaDAO) {

    val readAllData: LiveData<List<Campagna>> = CampagnaDAO.readAllData()
    val readDMData: LiveData<List<Campagna>> = CampagnaDAO.readDMData()
    val readPGData: LiveData<List<Campagna>> = CampagnaDAO.readPGData()

    suspend fun addCampagna(Campagna: Campagna){
        CampagnaDAO.addCampagna(Campagna)
    }

    suspend fun updateCampagna(Campagna: Campagna){
        CampagnaDAO.updateCampagna(Campagna)
    }

    suspend fun deleteCampagna(Campagna: Campagna){
        CampagnaDAO.deleteCampagna(Campagna)
    }

    suspend fun getCampagnaFromID(id: Int): Campagna {
        return CampagnaDAO.getCampagnaFromID(id)
    }

    suspend fun readAllSchedeFromCampagnaID(id: Int): List<Scheda>{
        return CampagnaDAO.readAllSchedeFromCampagnaID(id)
    }

    suspend fun readAllNotesFromCampagnaID(id: Int): List<Notes>{
        return CampagnaDAO.readAllNotesFromCampagnaID(id)
    }

}