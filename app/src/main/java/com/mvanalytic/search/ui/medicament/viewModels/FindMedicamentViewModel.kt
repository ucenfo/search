package com.mvanalytic.search.ui.medicament.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvanalytic.search.data.datasources.LocalMedsDataSource
import com.mvanalytic.search.data.repositories.MedsRepositoryImpl
import com.mvanalytic.search.domian.models.MedModel
import com.mvanalytic.search.domian.repositories.MedsRepository
import com.mvanalytic.search.domian.usecases.GetMedUseCase

class FindMedicamentViewModel(
    private val getMedUseCase: GetMedUseCase
): ViewModel() {


    private val _medListLiveData = MutableLiveData<List<MedModel>>()

    val medListLiveData: LiveData<List<MedModel>>
        get() = _medListLiveData

    fun onViewReady(){
        val list = getMedUseCase.execute()
        _medListLiveData.value = list
        println("lista: $list")
    }
}

