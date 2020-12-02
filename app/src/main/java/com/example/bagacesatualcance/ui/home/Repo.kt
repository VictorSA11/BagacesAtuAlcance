package com.example.bagacesatualcance.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class Repo {
  fun getNoticiaData():LiveData<MutableList<ModelNoticia>>{
      val mutableData = MutableLiveData<MutableList<ModelNoticia>>()
      FirebaseFirestore.getInstance().collection("Noticia").get().addOnSuccessListener { result->
          val listData = mutableListOf<ModelNoticia>()
          for (document in result){
              val foto = document.getString("foto")
              val fecha = document.getString("fecha")
              val ubicacion = document.getString("ubicacion")
              val descripcion = document.getString("descripcion")
              val noticia = ModelNoticia(foto!!,fecha!!,ubicacion!!,descripcion!!)
              listData.add(noticia)
          }
          mutableData.value = listData
      }
      return mutableData
  }
}