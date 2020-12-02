package com.example.bagacesatualcance.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bagacesatualcance.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.List.of

class HomeFragment : Fragment() {
//    lateinit var mrecyclerview: RecyclerView
//    lateinit var ref: DatabaseReference
//    lateinit var show_progress: ProgressBar
    private lateinit var adapter:MainAdapter
    private lateinit var homeViewModel: HomeViewModel
    private val mainViewModell by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it

            adapter = MainAdapter(context)
            listNoticia.layoutManager = LinearLayoutManager(context)
            listNoticia.adapter = adapter
            observeData()


        })
        return root
    }
    fun observeData(){
        shimmer_view_container.startShimmer()
        mainViewModell.fechNoticiaData().observe(viewLifecycleOwner, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility=View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}

