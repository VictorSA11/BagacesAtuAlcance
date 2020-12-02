package com.example.bagacesatualcance.ui.home.Crear

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.bagacesatualcance.R
import com.google.android.gms.location.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_noticias.*
import java.util.*


var PERMISSION_ID = 1010
private const val RECUEST_CODE = 49
class NoticiasActivity : AppCompatActivity(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
 private val db = FirebaseFirestore.getInstance()
    var dia =0
    var mes =0
    var year =0
    var hour =0
    var minuto=0

    var saveDia =0
    var saveMes =0
    var saveYear =0
    var saveHour =0
    var saveMinuto =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)
        title = "Crea tu  nueva Noticia"
        //onclick de la ubicacion
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        btnLocation.setOnClickListener(){
           getLastLocation()
        }
//guarda noticia
        btnNuevaNoticia.setOnClickListener{
        db.collection("Noticia").document(txtfecha.text.toString()).set(hashMapOf("ubicacion" to txtubicacion.text.toString(),
        "foto" to imageView2.toString(),"fecha" to txtfecha.text.toString(),
        "descripcion" to txtdescripcion.text.toString()))
            Toast.makeText(this,"Noticia publicada con éxito",Toast.LENGTH_SHORT).show()

            finish()
        }

//onclick de la camara
        btnCamara.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(this.packageManager) != null){
                startActivityForResult(takePictureIntent, RECUEST_CODE)
            }else{
                Toast.makeText(this,"Imposible abrir camara", Toast.LENGTH_SHORT).show()
            }

        }
        pickDate()
    }
    // metodos de la ubicacion
    private fun CheckPermission():Boolean{
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        return false
    }
    private fun RequestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISSION_ID
        )
    }
    private fun isLocationEnabled():Boolean{

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
    private fun getLastLocation(){

        if (CheckPermission()){
            if (isLocationEnabled()){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task ->
                     var location = task.result
                     if (location == null){
                        getNewLocation()
                     }else{

                         txtubicacion.text = "Ubicación exacta de los hechos:\n Longitud: "+ location.longitude + "\n Latitud: " + location.latitude
                     }
                 }
            }else{
                Toast.makeText(this,"Necesita activar Ubicación",Toast.LENGTH_SHORT).show()
            }

        }else{
            RequestPermission()
        }
    }
    private fun getNewLocation(){
    var locationRequest = LocationRequest()
     locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
     locationRequest.interval = 0
     locationRequest.fastestInterval = 0
     locationRequest.numUpdates = 1
     fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
     if (ActivityCompat.checkSelfPermission(
             this,
             android.Manifest.permission.ACCESS_FINE_LOCATION
         ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
             this,
             android.Manifest.permission.ACCESS_COARSE_LOCATION
         ) != PackageManager.PERMISSION_GRANTED
     ) {
         // TODO: Consider calling
         //    ActivityCompat#requestPermissions
         // here to request the missing permissions, and then overriding
         //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
         //                                          int[] grantResults)
         // to handle the case where the user grants the permission. See the documentation
         // for ActivityCompat#requestPermissions for more details.
         return
     }
     fusedLocationProviderClient!!.requestLocationUpdates(locationRequest,locationCallback,Looper.myLooper())
 }
    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation = p0.lastLocation


            txtubicacion.text = "Ubicación exacta de los hechos:\n Longitud: "+ lastLocation.longitude + "\n Latitud: " + lastLocation.latitude
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode== PERMISSION_ID){
            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.d("Debug:","Tienes Permiso")
            }
        }
    }

// EL RESULTADO DE LA IMAGEN
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenImage = data?.extras?.get("data") as Bitmap
            imageView2.setImageBitmap(takenImage)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

// metodos para calendario
    private fun getDateTimeCalendar(){
val cal = Calendar.getInstance()
    dia = cal.get(Calendar.DAY_OF_MONTH)
    mes = cal.get(Calendar.MONTH)
    year = cal.get(Calendar.YEAR)
    hour = cal.get(Calendar.HOUR)
    minuto= cal.get(Calendar.MINUTE)

    }

    private fun pickDate(){
        btnCalendar.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,mes,dia).show()
        }
    }
    override fun onDateSet(p0: DatePicker?, year : Int, month: Int, dayOFMoOnth: Int) {
        saveDia = dayOFMoOnth
        saveMes = month
        saveYear = year
        getDateTimeCalendar()
        TimePickerDialog(this,this,hour,minuto, true).show()
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        saveHour = hourOfDay
        saveMinuto = minute

        txtfecha.text =" Fecha: $saveDia-$saveMes-$saveYear Hora $saveHour:$saveMinuto min"
    }


}