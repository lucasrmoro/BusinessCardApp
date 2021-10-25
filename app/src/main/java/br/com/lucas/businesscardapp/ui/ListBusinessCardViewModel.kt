package br.com.lucas.businesscardapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.lucas.businesscardapp.data.BusinessCard
import br.com.lucas.businesscardapp.data.DataBaseConnect
import kotlinx.coroutines.launch

class ListBusinessCardViewModel(private val context: Application) : AndroidViewModel(context) {

    private val _businessCardsList = MutableLiveData<List<BusinessCard>>()
    val businessCardList: LiveData<List<BusinessCard>>
        get() = _businessCardsList

    fun refresh() {
        viewModelScope.launch {
            _businessCardsList.postValue(
                DataBaseConnect.getBusinessCardDao(context).getAll()
            )
        }
    }
}