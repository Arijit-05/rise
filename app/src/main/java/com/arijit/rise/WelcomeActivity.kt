package com.arijit.rise

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    private lateinit var getStartedButton: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getStartedButton = findViewById(R.id.get_started_button)
        getStartedButton.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        }

    }

    private fun navigateToAuth(destination: String) {
        val intent = Intent(
            this@WelcomeActivity,
            AuthenticationActivity::class.java
        )
            .apply {
            putExtra(AuthenticationActivity.EXTRA_AUTH_DESTINATION, destination)
        }
        startActivity(intent)
    }

}