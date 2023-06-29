package com.rathalove.kunapheapadmin.Util.Components.Card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rathalove.kunapheapadmin.Activity.HomeActivity
import com.rathalove.kunapheapadmin.MainActivity
import com.rathalove.kunapheapadmin.R
import com.rathalove.kunapheapadmin.RoomData.UserLogIn.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private val userDatabase: UserDatabase by lazy { UserDatabase.getDatabase(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            GlobalScope.launch (Dispatchers.IO){
                var user = userDatabase.userDao().getAllUser()
                launch(Dispatchers.Main){
                    if(user.isEmpty()){
                        var intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        var username = user[0].username
                        var password = user[0].password
                        if (username.equals("admin1") && password.equals("admin1")){
                            var intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else if (username.equals("admin2") && password.equals("admin2")){
                            var intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }, 3000)
    }
}