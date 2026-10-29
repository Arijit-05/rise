package com.arijit.rise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arijit.rise.fragments.LoginFragment
import com.arijit.rise.fragments.RegisterFragment

class AuthenticationActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_AUTH_DESTINATION = "extra_auth_destination"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        if (savedInstanceState == null) {
            val destination = intent.getStringExtra(EXTRA_AUTH_DESTINATION) ?: "LOGIN"
            if (destination == "REGISTER")
                showFragment(RegisterFragment(), addToBackStack = false)
            else
                showFragment(LoginFragment(), addToBackStack = false)
        }
    }

    fun showFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()


    }
}