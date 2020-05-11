package com.app.navigationcomponent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToNextScreen()
    }

    private fun navigateToNextScreen(){
        val sharedPrefHelper = SharedPrefHelper(this)
        if (sharedPrefHelper.getUserLoggedInState()){
            goToDetailScreen()
        }else{
            goToMainScreen()
        }
    }

    private fun goToMainScreen(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToDetailScreen(){
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
        finish()
    }


}
