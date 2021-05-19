package com.mobile.registrationnavigationapp.Home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobile.registrationnavigationapp.Home.adapter.NoteAdapter
import com.mobile.registrationnavigationapp.R
import com.mobile.registrationnavigationapp.local.DatabaseNotes
import com.mobile.registrationnavigationapp.local.Notes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_form_note.view.*
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.item_note.*
import java.text.SimpleDateFormat
import java.util.*


class NoteFragment : Fragment() {

    private var noteDatabase: DatabaseNotes? = null
    private var dialogView: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        noteDatabase = context?.let { DatabaseNotes.getInstance(it) }

        showNote()

        fab.setOnClickListener{
            showAddDialog()
        }
    }

    private fun showAddDialog(){
        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_form_note, null)
        dialog.setView(view)

        view.btnSave.setOnClickListener {

            if (view.etNote.text.toString().isNotEmpty()){
                addNote(Notes(null, view.etNote.text.toString(), false , getDate()))
            }else{
                view.etNote.error = "Note harus diisi"
            }
        }
        view.btnClose.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()
    }


    private fun showUpdateDialog(item: Notes?){
        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_form_note, null)
        dialog.setView(view)

        view.btnSave.text = "Update"
        view.etNote.setText(item?.note)
        view.btnSave.setOnClickListener {

            if (view.etNote.text.toString().isNotEmpty()){
                updateNote(Notes(item?.id, view.etNote.text.toString(), item?.favorite ?: false, getDate()))
            }else{
                view.etNote.error = "Note harus diisi"
            }
        }

        view.btnClose.setOnClickListener {
            dialogView?.dismiss()
        }

        dialogView = dialog.create()
        dialogView?.show()
    }

    private fun getDate(): String{
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance()
        val formatDate = formatter.format(date)

        return formatDate
    }

    private fun showNote() {
        Observable.fromCallable { noteDatabase?.notesDao()?.getAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       listNote.adapter = NoteAdapter(it,object : NoteAdapter.OnClickListener{
                           override fun update(item: Notes?) {
                               showUpdateDialog(item)
                           }

                           override fun updateFav(item: Notes?) {
                               if (item?.favorite == true){
                                   updateNote(Notes(item.id, item.note, false, item.date))
                               } else {
                                   updateNote(Notes(item?.id, item?.note, true, item?.date))
                               }

                           }

                           override fun delete(item: Notes?) {
                               AlertDialog.Builder(context).apply {
                                   setTitle("Hapus")
                                   setMessage("Yakin hapus note?")
                                   setCancelable(false)

                                   setPositiveButton("Yakin"){dialogInterface, i ->
                                       deleteNote(item)
                                   }
                                   setNegativeButton("Batal"){dialogInterface, i ->
                                       dialogInterface.dismiss()
                                   }
                               }.show()
                           }
                       })
                       },{
                           Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }


    private fun addNote(item: Notes){
        Observable.fromCallable { noteDatabase?.notesDao()?.insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(context, "Note berhasil disimpan", Toast.LENGTH_SHORT).show()
                showNote()
                dialogView?.dismiss()
            },{
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }

    private fun updateNote(item: Notes){
        Observable.fromCallable { noteDatabase?.notesDao()?.update(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(context, "Note berhasil diupdate", Toast.LENGTH_SHORT).show()
                showNote()
                dialogView?.dismiss()
            },{
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }

    private fun deleteNote(item: Notes?){
        Observable.fromCallable { noteDatabase?.notesDao()?.delete(item!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(context, "Note berhasil dihapus", Toast.LENGTH_SHORT).show()
                showNote()
                dialogView?.dismiss()
            },{
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }
}