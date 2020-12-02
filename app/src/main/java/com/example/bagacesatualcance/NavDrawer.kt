package com.example.bagacesatualcance
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bagacesatualcance.ui.gallery.SugerenciasActivity
import com.example.bagacesatualcance.ui.home.Crear.NoticiasActivity
import com.example.bagacesatualcance.ui.mapas.MapaActivity
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main.*

class NavDrawer : AppCompatActivity() {
   var isOpen = false

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)




         val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
            val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
            val fabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
            val fabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)
            fab.setOnClickListener{
                if (isOpen){
                    fab1.startAnimation(fabClose)
                    fab2.startAnimation(fabClose)
                    fab3.startAnimation(fabClose)
                    fab.startAnimation(fabRClockwise)
                    isOpen = false
                }
                else{
                    fab1.startAnimation(fabOpen)
                    fab2.startAnimation(fabOpen)
                    fab3.startAnimation(fabOpen)
                    fab.startAnimation(fabRAntiClockwise)

                    fab2.isClickable
                    fab2.isClickable
                    fab3.isClickable

                    isOpen = true
                }
                fab2.setOnClickListener {
                    val intent = Intent(this, NoticiasActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Agregar Noticia", Toast.LENGTH_LONG).show()
                }
                fab1.setOnClickListener{
                    val intent = Intent(this, SugerenciasActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Agregar Sugerencia", Toast.LENGTH_LONG).show()
                }
                fab3.setOnClickListener{
                    val intent = Intent(this, MapaActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Ver Rutas", Toast.LENGTH_LONG).show()
                }
            }

        }




        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.homeActivity
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.nav_drawer, menu)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    }




