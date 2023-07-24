package com.mvanalytic.search.ui.medicament.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvanalytic.search.domian.usecases.GetMedUseCase
import com.mvanalytic.search.ui.medicament.viewModels.FindMedicamentViewModel

class FindMedicamentViewModelFactory(
    private val getMedUseCase: GetMedUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FindMedicamentViewModel(
            getMedUseCase = getMedUseCase
        ) as T
    }
}