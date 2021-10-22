package br.com.lucas.businesscardapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.businesscardapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.addFab.setOnClickListener {
            AddBusinessCardActivity.launchScreen(this)
        }
    }
}