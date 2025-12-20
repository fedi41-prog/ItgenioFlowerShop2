package com.fedi4.flowershop2.db

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fedi4.flowershop2.BasketViewModel

class BasketViewModelFactory(var application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
            return BasketViewModel(application) as T;
        }
        return super.create(modelClass)
    }



}


