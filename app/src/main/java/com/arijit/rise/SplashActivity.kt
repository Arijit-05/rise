package com.arijit.rise

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loading: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loading = findViewById(R.id.loading)

        lifecycleScope.launch {
            loading.progress = 30
            for (i in 1..100) {
                delay(5)
                loading.setProgress(i, true)
            }
            delay(50)
            launchActivity(WelcomeActivity())
        }




//        launchActivity(MainActivity())

//        auth = Firebase.auth
//        val currentUser = auth.currentUser
//
//        Handler().postDelayed({
//            if (currentUser != null) launchActivity(MainActivity())
//            else launchActivity(WelcomeActivity())
//            finish()
//        }, 1000)

    }

    fun launchActivity(activity: AppCompatActivity) {
        startActivity(Intent(this@SplashActivity, activity::class.java))
    }
}