package com.rathalove.kunapheapadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rathalove.kunapheapadmin.Activity.HomeActivity
import com.rathalove.kunapheapadmin.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private var PASSWORD_PARTTERN: Pattern = Pattern.compile(
        "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //  "(?=.*[a-z])" +         //at least 1 lower case letter
                //  "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //  "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this

        binding.btnLogIn.setOnClickListener{
            validatePass()
            validateUsername()
            if (validatePass().equals(false) || validateUsername().equals(false)){
                Toast.makeText(this,"Long In fail", Toast.LENGTH_SHORT).show()
            }
            else{
                var username = binding.usernameEdt.text.toString()
                var password = binding.passwordEdt.text.toString()
                var intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)

            }
        }











    }




    private fun validateUsername(): Boolean {
        var username = binding.usernameEdt.text.toString()

        if (username.length < 4) {
            binding.usernameInp.error = "Invalid Username"
            return false
        } else if (username.isEmpty()) {
            binding.usernameInp.error = "Field can not empty"
            return false
        } else {
            binding.usernameInp.error = null
            binding.usernameInp.isErrorEnabled = false
            binding.usernameInp.isHelperTextEnabled = false
            return true
        }
    }
    private fun validatePass(): Boolean {
        val passTxt = binding.passwordEdt.text.toString()
        if (passTxt.isEmpty()) {
            binding.passwordInp.error = "Field can not be empty"
            return false
        } else if (!PASSWORD_PARTTERN.matcher(passTxt).matches()) {
            binding.passwordInp.error = "Invalid Password"
            return false
        } else {
            binding.passwordInp.error = null
            binding.passwordInp.isErrorEnabled = false
            binding.passwordInp.isHelperTextEnabled = false
            return true
        }
    }
}