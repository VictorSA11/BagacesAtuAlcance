package com.example.bagacesatualcance.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bagacesatualcance.R
import com.example.bagacesatualcance.ui.home.MainAdapter
import com.example.bagacesatualcance.ui.home.MainViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_home.*

class GalleryFragment : Fragment() {
  private val mainViewModelSu by lazy { ViewModelProviders.of(this).get(MainViewModelSu::class.java) }
  private lateinit var galleryViewModel: GalleryViewModel
  private lateinit var adapter: MainAdapterSu
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    galleryViewModel =
    ViewModelProviders.of(this).get(GalleryViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_gallery, container, false)
    galleryViewModel.text.observe(viewLifecycleOwner, Observer {

      adapter = MainAdapterSu(context)
      listSugerencia.layoutManager = LinearLayoutManager(context)
      listSugerencia.adapter = adapter
      observeData()

    })
    return root
  }


  fun observeData(){
    shimmer_view_containerSu.startShimmer()
    mainViewModelSu.fechNoticiaData().observe(viewLifecycleOwner, Observer {
      shimmer_view_containerSu.stopShimmer()
      shimmer_view_containerSu.visibility=View.GONE
      adapter.setListData(it)
      adapter.notifyDataSetChanged()
    })
  }
}