package com.mvanalytic.search.ui.main.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mvanalytic.search.R
import com.mvanalytic.search.ui.main.viewmodels.MainViewModel
import com.mvanalytic.search.ui.medicament.views.FindMedicamentFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToFragment(FindMedicamentFragment(), true)
        }
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observe()
    }

    /**
     * Función que establece una observación del evento de navegación del ViewModel y realiza las
     * acciones específicas dependiendo del tipo de evento recibido. Esto permite la navegación a
     * diferentes fragmentos de la aplicación de la pantalla según el evento de navegación emitido.
     */
    private fun observe() {
        // Agregar navegación específica fuera del navbar
        mainViewModel.navigationEvent.observe(this) { event ->
            when (event) {

                else -> {}
            }
        }
    }

    /**
     * Función privada que delega al Main Activity el cambio del fragmento que se muestra en su
     * contenedor principal.
     */
    private fun navigateToFragment(fragment: Fragment, isInitialFragment: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (!isInitialFragment) {
            transaction.addToBackStack(fragment.javaClass.name)
        }
        transaction
            .commit()
    }
}