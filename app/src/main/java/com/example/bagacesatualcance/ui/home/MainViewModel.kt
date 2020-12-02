package com.example.bagacesatualcance.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
   private val repo = Repo()
    fun fechNoticiaData():LiveData<MutableList<ModelNoticia>>{
        val mutableData = MutableLiveData<MutableList<ModelNoticia>>()
   repo.getNoticiaData().observeForever(){
       mutableData.value = it
   }
        return mutableData
    }
}