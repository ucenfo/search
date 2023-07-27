package com.mvanalytic.search.ui.medicament.views

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvanalytic.search.R
import com.mvanalytic.search.data.datasources.LocalMedsDataSource
import com.mvanalytic.search.data.repositories.MedsRepositoryImpl
import com.mvanalytic.search.domian.models.MedModel
import com.mvanalytic.search.domian.usecases.GetMedUseCase
import com.mvanalytic.search.ui.main.viewmodels.MainViewModel
import com.mvanalytic.search.ui.medicament.adapters.FindMedicamentListAdapter
import com.mvanalytic.search.ui.medicament.viewModels.FindMedicamentViewModel
import com.mvanalytic.search.ui.medicament.viewModels.factories.FindMedicamentViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale


class FindMedicamentFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var camara: ImageView
    private lateinit var medsList: RecyclerView
    private lateinit var pbLarge: ProgressBar
    private lateinit var noMedGroup: Group
    private lateinit var progressBarGroup: Group
    private lateinit var lenguage: String
    private val initialList = mutableListOf<MedModel>()
    private val leakedData = mutableListOf<MedModel>()

    private lateinit var mainViewModel: MainViewModel

    private lateinit var findMedicamentViewModel: FindMedicamentViewModel

    private val localMedDataSource by lazy { LocalMedsDataSource }
    private val medsRepositoryImpl by lazy { MedsRepositoryImpl(localMedDataSource) }
    private val getMedUseCase by lazy { GetMedUseCase(medsRepositoryImpl) }
    private val findMedicamentViewModelFactory by lazy {
        FindMedicamentViewModelFactory(
            getMedUseCase
        )
    }


    private val findMedicamentListAdapter by lazy {
        FindMedicamentListAdapter(
            displayDetailOfMedication = {item -> displayDatailMedicament(item)}
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_find_medicament, container, false)
        initViews(view)
        findMedicamentViewModel = ViewModelProvider(this, findMedicamentViewModelFactory)[FindMedicamentViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        observe()
        queryText()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findMedicamentViewModel.onViewReady()
    }

    private fun initViews(view: View){
        with(view){
            medsList = findViewById(R.id.medicaments_list)
            medsList.adapter = findMedicamentListAdapter
            medsList.layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            searchView = findViewById(R.id.search_view)
            noMedGroup = findViewById(R.id.no_medicaments_group)
            noMedGroup.visibility = View.GONE
            progressBarGroup = findViewById(R.id.progress_bar_group)
            progressBarGroup.visibility = View.VISIBLE
            camara = findViewById(R.id.camara)
            camara.setOnClickListener { searchUsingCamara() }
            pbLarge = findViewById(R.id.pbLarge)
            initProgressBar()
        }
        lenguage = Locale.getDefault().language

    }

    private fun initProgressBar(){
        pbLarge.indeterminateDrawable.setColorFilter(ContextCompat.getColor(requireContext(),
        R.color.blue), PorterDuff.Mode.SRC_IN)

    }


    private fun observe(){
        findMedicamentViewModel.medListLiveData.observe(viewLifecycleOwner) {
            list ->
            initialList.clear()
            setProgressBar(initialList)
            initialList.addAll(list)
            // muestra
            showProgressBarWithDelay(initialList)
            // continua
//            findMedicamentListAdapter.setData(initialList)
//            setProgressBar(initialList)



        }
    }

    private fun showProgressBarWithDelay(list: List<MedModel>) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                delay(3000)
                findMedicamentListAdapter.setData(initialList)
                setProgressBar(initialList)
            }
        }
    }

    private fun setSearchMessage(list: List<MedModel>) {
        if (list.isEmpty()){
            noMedGroup.visibility = View.VISIBLE
            medsList.visibility = View.GONE
        } else {
            noMedGroup.visibility = View.GONE
            medsList.visibility = View.VISIBLE
        }
    }

    private fun setProgressBar(list: List<MedModel>) {
        if (list.isEmpty()){
            progressBarGroup.visibility = View.VISIBLE
            medsList.visibility = View.GONE
        } else {
            progressBarGroup.visibility = View.GONE
            medsList.visibility = View.VISIBLE
        }
    }

    private fun queryText(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchMed(query.toString(), initialList)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchMed(newText.toString(), initialList)
                }
                return false
            }
        })
    }

    private fun searchMed(text: String, list: List<MedModel>) {

        list?.let { list ->
            val marchingItems = if (lenguage == "en") {
                list.filter { medModel ->
                    medModel.nameEng.contains(text, ignoreCase = true) ||
                            medModel.brand.contains(text, ignoreCase = true)
                }
            } else {
                list.filter { medModel ->
                    medModel.nameSpa.contains(text, ignoreCase = true) ||
                            medModel.brand.contains(text, ignoreCase = true)
                }
            }
            leakedData.clear()
            leakedData.addAll(marchingItems)
            findMedicamentListAdapter.setData(leakedData)
            setSearchMessage(leakedData)
        }
    }


    private fun searchUsingCamara(){
//        TODO: implementar código de cámara, para obtener el texto y pasarlo a la siguiente función
        searchMed("asp", initialList)
    }


    private fun displayDatailMedicament(medModel: MedModel) {
        findMedicamentViewModel.setClickedMed(medModel)
//        mainViewModel.navigateTo(NavigationScreen.ShowMed(medModel))
        println("Medicina: $medModel")
//        TODO: pasar la info a otro fragmento
    }


}