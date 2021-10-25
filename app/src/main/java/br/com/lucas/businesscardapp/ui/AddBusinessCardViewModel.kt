package br.com.lucas.businesscardapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.businesscardapp.data.BusinessCard
import br.com.lucas.businesscardapp.data.DataBaseConnect
import kotlinx.coroutines.launch

class AddBusinessCardViewModel : ViewModel() {

    fun insert(context: Context, businessCard: BusinessCard) {
        viewModelScope.launch {
            DataBaseConnect.getBusinessCardDao(context).insert(businessCard)
        }
    }
}