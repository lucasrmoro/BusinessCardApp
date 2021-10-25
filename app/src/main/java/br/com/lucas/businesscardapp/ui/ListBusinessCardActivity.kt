package br.com.lucas.businesscardapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.lucas.businesscardapp.databinding.ActivityListBusinessCardBinding

class ListBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityListBusinessCardBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ListBusinessCardViewModel(application) }
    private val adapter by lazy { ListBusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupList()
        insertListeners()

        viewModel.businessCardList.observe(this) {
            adapter.addBusinessCards(it)
        }
    }

    private fun insertListeners() {
        binding.addFab.setOnClickListener {
            AddBusinessCardActivity.launchScreen(this)
        }
    }

    private fun setupList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}