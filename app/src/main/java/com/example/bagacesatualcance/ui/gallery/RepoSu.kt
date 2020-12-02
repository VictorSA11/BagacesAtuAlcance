package com.example.bagacesatualcance.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class RepoSu {
    fun getSugerenciaData(): LiveData<MutableList<ModelSugerencia>> {
        val mutableData = MutableLiveData<MutableList<ModelSugerencia>>()
        FirebaseFirestore.getInstance().collection("Sugerencia").get().addOnSuccessListener { result->
            val listData = mutableListOf<ModelSugerencia>()
            for (document in result){
                val descripcion = document.getString("descripcion")
                val sugerencia = ModelSugerencia(descripcion!!)
                listData.add(sugerencia)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}