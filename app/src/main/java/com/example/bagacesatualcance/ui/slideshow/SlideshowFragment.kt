package com.example.bagacesatualcance.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bagacesatualcance.R
import io.github.ponnamkarthik.richlinkpreview.ViewListener
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

  private lateinit var slideshowViewModel: SlideshowViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?

  ): View? {

    slideshowViewModel =
    ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
    val textView: TextView = root.findViewById(R.id.text_slideshow)
    slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it


        richLinkViewTel.setLink("https://www.bagaces.go.cr", object : ViewListener {

            override fun onSuccess(status: Boolean) {

            }

            override fun onError(e: Exception) {

            }
        })

        richLinkViewTe.setLink("https://www.facebook.com/MunicipalidaddeBagaces", object : ViewListener {

            override fun onSuccess(status: Boolean) {

            }

            override fun onError(e: Exception) {

            }
        })
        richLinkViewT.setLink("https://www.youtube.com/channel/UCUnWNK_57Suf0g1z733cuag", object : ViewListener {

            override fun onSuccess(status: Boolean) {

            }

            override fun onError(e: Exception) {

            }
        })
        richLinkViewT1.setLink("http://mercadovirtual.bagaces.go.cr/index.php/es/", object : ViewListener {

            override fun onSuccess(status: Boolean) {

            }

            override fun onError(e: Exception) {

            }
        })
        richLinkViewT12.setLink("https://www.bagaces.go.cr/tramites-municipales", object : ViewListener {

            override fun onSuccess(status: Boolean) {

            }

            override fun onError(e: Exception) {

            }
        })
    })
    return root
  }

}