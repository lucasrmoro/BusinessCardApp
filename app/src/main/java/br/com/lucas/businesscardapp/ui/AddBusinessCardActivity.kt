package br.com.lucas.businesscardapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.lucas.businesscardapp.R
import br.com.lucas.businesscardapp.core.extensions.checkFieldIsEmpty
import br.com.lucas.businesscardapp.core.extensions.toast
import br.com.lucas.businesscardapp.data.BusinessCard
import br.com.lucas.businesscardapp.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private val viewModel by lazy { AddBusinessCardViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.btnSave.setOnClickListener {
            var businessCard = BusinessCard(
                name = checkFieldIsEmpty(binding.tilName.editText?.text!!, getString(R.string.label_undefined_name)),
                phone = checkFieldIsEmpty(binding.tilPhone.editText?.text!!, getString(R.string.label_undefined_phone)),
                email = checkFieldIsEmpty(binding.tilEmail.editText?.text!!, getString(R.string.label_undefined_email)),
                company = checkFieldIsEmpty(binding.tilCompany.editText?.text!!, getString(R.string.label_undefined_company)),
                customBackground = checkFieldIsEmpty(binding.tilColor.editText?.text!!,
                    resources.getString(0+R.color.gray)),
            )
            try {
                viewModel.insert(this, businessCard)
                finish()
                toast(R.string.label_successfully_saved)
            } catch (e: Exception) {
                Log.e("Error", "$e")
                toast(getString(R.string.label_something_went_wrong))
                finish()
            }
        }
    }

    companion object {
        fun launchScreen(context: Context) {
            val intent = Intent(context, AddBusinessCardActivity::class.java)
            context.startActivity(intent)
        }
    }
}