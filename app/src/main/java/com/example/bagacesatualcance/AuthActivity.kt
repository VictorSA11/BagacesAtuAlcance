package com.example.bagacesatualcance

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    private val GOOGLE_SING_IN = 100
    private val callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        //splash pantalla de inicio
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //setup o inicio de login
        setup()
        session()
    }
    override fun onStart(){
        super.onStart()
        authLayout.visibility = View.VISIBLE
    }
    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email",null)
        val provider = prefs.getString("provider",null)

        if(email !=null && provider !=null){
            authLayout.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }
    }


   // }

    private fun setup() {
       title = "Autenticación"
//        logOutButton.setOnClickListener {
//            if(emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
//                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),
//                        passwordEditText.text.toString()).addOnCompleteListener {
//                    if (it.isSuccessful){
//                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
//                }else{
//                    showAlert()
//                }
//
//            }
//        }
//    }
//        loginButton.setOnClickListener {
//            if(emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
//                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
//                    passwordEditText.text.toString()).addOnCompleteListener {
//                    if (it.isSuccessful){
//                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
//                    }else{
//                        showAlert()
//                    }
//
//                }
//            }
//        }
            googleButton.setOnClickListener{
                val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
                val googleClient = GoogleSignIn.getClient(this, googleConf)
                googleClient.signOut()
                    startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
            }

            facebookButton.setOnClickListener(){
                LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
                LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                      result?.let {
                          val token = it.accessToken
                          val credential = FacebookAuthProvider.getCredential(token.token)
                          FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                              if (it.isSuccessful){
                                  showHome(it.result?.user?.email ?: "", ProviderType.FACEBOOK)
                              }else{
                                  showAlert()
                              }
                          }
                      }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }
                })
        }
}

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
            builder.setTitle("Error")
            builder.setMessage("Se a producido un error de Autenticación")
            builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
            dialog.show()
    }
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, NavDrawer::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)

        }
        startActivity(homeIntent)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SING_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(account.email ?: "", ProviderType.GOOGLE)
                        }else{
                            showAlert()
                        }
                    }
                }
            } catch (e: ApiException){
                showAlert()
            }
        }

    }
}