package fabPackage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.fragment.noteActivity.NoteAdapter
import com.annoyingturtle.omnitop.fragment.noteActivity.NuovaNota
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dndData.viewModel.NotesViewModel
import kotlinx.android.synthetic.main.note_fab_layout.*
import kotlinx.android.synthetic.main.note_fab_layout.view.*


class NoteFabFragment() : BottomSheetDialogFragment() {

    var preferite = true
    private lateinit var mNotaViewModel : NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.note_fab_layout, container, false)

        val adapter = NoteAdapter()
        val recyclerView = view.favNoteRecyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNotaViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        mNotaViewModel.readFavoriteData.observe(viewLifecycleOwner, Observer { nota -> adapter.setDataNota(nota)})

        return view

    }

    override fun onStart() {
        super.onStart()

        nuovaNotaBtn.setOnClickListener(){

            startActivity(Intent(context, NuovaNota()::class.java))

        }

        allNotesBtn.setOnClickListener(){

            if(preferite){
                allNotesBtn.text = getString(R.string.favorite)
                textView8.text = getString(R.string.allNote)
                preferite = false

                val adapter = NoteAdapter()
                val recyclerView = view?.favNoteRecyclerView

                recyclerView?.adapter = adapter
                recyclerView?.layoutManager = LinearLayoutManager(requireContext())

                mNotaViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
                mNotaViewModel.readAllData.observe(viewLifecycleOwner, Observer { nota -> adapter.setDataNota(nota)})
            }
            else
            {
                preferite = true
                allNotesBtn.text = getText(R.string.allNote)
                textView8.text = getString(R.string.favorite)

                val adapter = NoteAdapter()
                val recyclerView = view?.favNoteRecyclerView

                recyclerView?.adapter = adapter
                recyclerView?.layoutManager = LinearLayoutManager(requireContext())

                mNotaViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
                mNotaViewModel.readFavoriteData.observe(viewLifecycleOwner, Observer { nota -> adapter.setDataNota(nota)})

            }

        }

    }


}