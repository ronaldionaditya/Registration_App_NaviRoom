package com.mobile.registrationnavigationapp.Home.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.registrationnavigationapp.R
import com.mobile.registrationnavigationapp.local.Notes
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(
    private val data: List<Notes>?,
    private val itemClick : OnClickListener
    )
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class ViewHolder(val view: View,
        val itemClick: OnClickListener)
        : RecyclerView.ViewHolder(view) {
        fun bind(item: Notes?){
            view.tvDate.text = item?.date
            view.tvNote.text = item?.note

            view.btnUpdate.setOnClickListener {
                itemClick.update(item)
            }
            view.btnDelete.setOnClickListener {
                itemClick.delete(item)
            }
        }
    }

    interface OnClickListener{
        fun update (item: Notes?)
        fun delete (item: Notes?)
    }

}