package com.instaclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Logs current user out if the application is closed
        ParseUser.logOut()

        // check if there is a user logged in
        // If there is, take them to MainActivity
        if(ParseUser.getCurrentUser()!=null){
            goToMainActivity()
        }

        // On click listener for sign in button
        findViewById<Button>(R.id.login_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username, password)
        }

        // OnClick listener for sign up button
        findViewById<Button>(R.id.signup_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username, password)
        }
    }
    // Method to sign up user
    private fun signUpUser(username: String, password: String){
        // Create the ParseUser
        // Create the ParseUser
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User has successfully created a new account
                // TODO: Navigate the user to the MainActivity
                // TODO: Show a toast to indicate user successfully signed up for an account
                Toast.makeText(this, "Signed Up Successful!", Toast.LENGTH_SHORT).show()
                goToMainActivity()
            } else {
                // TODO: Show a toast to tell user sign up was not successful
                e.printStackTrace()

            }
        }
    }

    // Method to loginUser
    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity(){
        // How does intent helps the user return to the main activity?
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish() // closes out login activity

    }

    companion object{
        const val TAG = "LoginActivity"
    }
}