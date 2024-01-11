package com.org.proyektingkat2.siagabanjir

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.org.proyektingkat2.siagabanjir.databinding.LoginActivityBinding



class Login: AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}