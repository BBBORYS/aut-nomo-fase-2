package com.example.zapatos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zapatos.R
import com.example.zapatos.Shoe

class ShoeAdapter(private val context: Context, private val shoes: List<Shoe>) :
    RecyclerView.Adapter<ShoeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shoe, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = shoes[position]
        holder.nameTextView.text = shoe.name
        holder.descriptionTextView.text = shoe.description
        holder.priceTextView.text = "$${shoe.price}"
    }

    override fun getItemCount(): Int {
        return shoes.size
    }
}
