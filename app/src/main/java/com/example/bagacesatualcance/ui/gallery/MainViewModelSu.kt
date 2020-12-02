package com.example.bagacesatualcance.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bagacesatualcance.ui.home.ModelNoticia
import com.example.bagacesatualcance.ui.home.Repo

class MainViewModelSu : ViewModel() {
    private val repo = RepoSu()
    fun fechNoticiaData(): LiveData<MutableList<ModelSugerencia>> {
        val mutableData = MutableLiveData<MutableList<ModelSugerencia>>()
        repo.getSugerenciaData().observeForever(){
            mutableData.value = it
        }
        return mutableData
    }
}