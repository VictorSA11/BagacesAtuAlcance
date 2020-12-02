package com.example.bagacesatualcance.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bagacesatualcance.R
import com.example.bagacesatualcance.ui.home.MainAdapter
import com.example.bagacesatualcance.ui.home.ModelNoticia
import kotlinx.android.synthetic.main.listar_noticias.view.*
import kotlinx.android.synthetic.main.listar_sugerencia.view.*

class MainAdapterSu (private val context: Context?): RecyclerView.Adapter<MainAdapterSu.MainViewHolder>() {
    private var dataList = mutableListOf<ModelSugerencia>()
    fun setListData(data:MutableList<ModelSugerencia>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listar_sugerencia,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val sugerencia = dataList[position]
        holder.bindView(sugerencia)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }
    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(sugerencia: ModelSugerencia){

            itemView.mostrar_queja.text = sugerencia.descripcion

        }
    }
}