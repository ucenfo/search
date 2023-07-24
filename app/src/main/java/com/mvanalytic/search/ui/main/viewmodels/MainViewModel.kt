package com.mvanalytic.search.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    /**
     * Instancia privada de la clase MutableLiveData que recibe como parámetro un objeto de tipo
     * NavigationScreen. Esta clase permite almacenar y emitir datos observables.
     * Es una variable que permite actualizaciones únicamente dentro de la clase
     */
    private val _navigationEvent = MutableLiveData<NavigationScreen>()

    /**
     * Variable pública de LiveData, que es de solo lectura que expone datos observables
     * Esta propiedad permite a los observadores suscribirse para observar cambio en los datos
     * almacenados dentro de ella. Los observadores se deben suscribir
     * @return LiveData<TagModel> LiveData que contiene la etiqueta agregada.
     */
    val navigationEvent: LiveData<NavigationScreen>
        get() = _navigationEvent

    /**
     * Función que permite almacenar el objeto naviationScreen en _navigationEvent
     */
    fun navigateTo(navigationScreen: NavigationScreen) {
        _navigationEvent.value = navigationScreen
    }
}

sealed class NavigationScreen {
    object AddPersonalInformation: NavigationScreen()
}