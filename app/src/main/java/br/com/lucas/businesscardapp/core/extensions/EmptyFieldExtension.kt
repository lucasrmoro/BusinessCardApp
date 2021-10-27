package br.com.lucas.businesscardapp.core.extensions

import android.text.Editable

fun checkFieldIsEmpty(field: Editable, newText: String): String{
    return if (field.isBlank()){
            newText
    } else {
       field.toString()
    }
}
