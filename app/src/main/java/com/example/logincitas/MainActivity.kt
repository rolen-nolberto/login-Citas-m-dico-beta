package com.example.logincitas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btningresar: Button = findViewById(R.id.btnIngresar)
        val txtemail: TextView = findViewById(R.id.edtEmail)
        val txtpass: TextView = findViewById(R.id.edtPassword)
        val btnCrearCuentaNueva : TextView = findViewById(R.id.btnCrearCuenta)
        firebaseAuth= Firebase.auth
        btningresar.setOnClickListener()
        {
            signIn(txtemail.text.toString(),txtpass.text.toString())
        }
        btnCrearCuentaNueva.setOnClickListener()
        {
            val i = Intent (this,CrearCuentaActivity3::class.java)
            startActivity(i)

        }
    }

    private fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext,"Atenticacion exitosa",Toast.LENGTH_SHORT).show()
                    //
                    val i = Intent( this, MainActivity2::class.java)
                    startActivity(i)

                }
                else
                {
                    Toast.makeText(baseContext, "Error de Email y/o contraseña", Toast.LENGTH_SHORT).show()
                }
            }
    }
}