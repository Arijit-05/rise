package com.arijit.rise.fragments

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arijit.rise.R

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val glow: View = view.findViewById(R.id.glow)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            glow.setRenderEffect(
                RenderEffect.createBlurEffect(45f, 45f, Shader.TileMode.CLAMP)
            )
        }

        return view
    }

}