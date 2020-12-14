package com.example.authauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var inputEmail : EditText
    private lateinit var inputPassword : EditText

    private lateinit var submitButton : Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEmail = findViewById(R.id.registerEmailAddress)
        inputPassword = findViewById(R.id.registerPassword)
        submitButton = findViewById(R.id.submitButton)

        mAuth = FirebaseAuth.getInstance()

        submitButton.setOnClickListener{
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Empty!",Toast.LENGTH_SHORT).show()
            }else{
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Success!",Toast.LENGTH_SHORT).show()
                        inputEmail.setText("")
                        inputPassword.setText("")
                        inputEmail.hint="E-mail"
                        inputPassword.hint="Password"

                    }else{
                        Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }
}