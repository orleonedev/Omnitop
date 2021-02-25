package com.annoyingturtle.omnitop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_home.*
import kotlinx.android.synthetic.main.activity_home.addBtn1
import kotlinx.android.synthetic.main.activity_home.cardBtn1
import kotlinx.android.synthetic.main.activity_home.diceBtn1
import kotlinx.android.synthetic.main.activity_home.gridBtn1
import kotlinx.android.synthetic.main.activity_home.noteBtn1
import fabPackage.AbsFab

class DndCampagnaHome : AppCompatActivity() {

    lateinit var mCampagnaViewModel: CampagnaViewModel

    var idCampagna: Int = -1

    lateinit var mytablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_home)

        var extras = intent.extras
        idCampagna = extras!!.getInt("idItem")

        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        mCampagnaViewModel.getCampagnaFromID(idCampagna)
        showBasicCampagnaData()


        /**Action Bar */
        setSupportActionBar(myToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        /** Navigare fra le schede */
        val navController = findNavController(R.id.campagnafragmenthost)
        mytablayout = tabNav

        mytablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text.toString() == "SCHEDE")
                    navController.navigate(R.id.dnDCampagnaSchedeFragment)
                else if (tab?.text.toString() == "NOTE")
                    navController.navigate(R.id.dndCamapagnaNoteFragment)
                else
                    navController.navigate(R.id.dndCampagnaCompendiumFragment)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })


        /********** FAB ***********/
        val fab = AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager)
        fab.startListener(this)
    }

    private fun showBasicCampagnaData() {
       mCampagnaViewModel.getSingleLiveData().observe(this, Observer {
            supportActionBar?.title= it.titoloCampagna

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_risorse_campagna, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nuovaSchedaOpt -> Toast.makeText(this, "nuovascheda", Toast.LENGTH_SHORT).show()

            R.id.nuovaNotaOpt -> Toast.makeText(this, "nuovanota", Toast.LENGTH_SHORT).show()

            R.id.nuovoElementoLibOpt -> Toast.makeText(this, "nuovoele", Toast.LENGTH_SHORT).show()

            R.id.infoCampagna -> Toast.makeText(this, "info", Toast.LENGTH_SHORT).show()
        }

            return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.campagnafragmenthost)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}