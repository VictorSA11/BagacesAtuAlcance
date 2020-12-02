package com.example.bagacesatualcance.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bagacesatualcance.R
import kotlinx.android.synthetic.main.listar_noticias.view.*

class MainAdapter(private val context: Context?): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

 private var dataList = mutableListOf<ModelNoticia>()
    fun setListData(data:MutableList<ModelNoticia>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listar_noticias,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       val noticia = dataList[position]
        holder.bindView(noticia)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }
    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
     fun bindView(noticia : ModelNoticia){
         if (context != null) {
             Glide.with(context).load(noticia.foto).into(itemView.mostrar_imagen)
         }
         itemView.mostrar_fecha.text = noticia.fecha
         itemView.mostrar_ubicacion.text = noticia.ubicacion
         itemView.mostrar_descripcion.text = noticia.descripcion

     }
    }

}