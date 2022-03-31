package com.lock.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class Notesrvadapter(private val context:Context, private val listner:inoteadapter): RecyclerView.Adapter<Notesrvadapter.noteviewholder>() {

    val allnotes= ArrayList<Note>()
    inner class noteviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textview=itemView.findViewById<TextView>(R.id.text)
        val deletebutton=itemView.findViewById<ImageView>(R.id.deletbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteviewholder {
        val viewHolder=noteviewholder(LayoutInflater.from(context).inflate(R.layout.itemnote,parent,false))
        viewHolder.deletebutton.setOnClickListener{
            listner.onitemclick(allnotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: noteviewholder, position: Int) {
        val currentnote=allnotes[position]
        holder.textview.text=currentnote.text
    }

    override fun getItemCount(): Int {
        return allnotes.size
    }
    fun updateList(newlist:List<Note>){
        allnotes.clear()
        allnotes.addAll(newlist)


        notifyDataSetChanged()
    }
}

interface inoteadapter{
    fun onitemclick(note: Note)
}