package com.mvanalytic.search.ui.medicament.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.Group
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
import java.util.Locale


class FindMedicamentFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var medicamentsList: RecyclerView
    private lateinit var noMedicamentsGroup: Group
    private lateinit var lenguage: String
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findMedicamentViewModel.onViewReady()
    }

    private fun initViews(view: View){
        with(view){
            medicamentsList = findViewById(R.id.medicaments_list)
            medicamentsList.adapter = findMedicamentListAdapter
            medicamentsList.layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            searchView = findViewById(R.id.search_view)
            noMedicamentsGroup = findViewById(R.id.no_medicaments_group)
        }
        lenguage = Locale.getDefault().language

    }


    private fun observe(){
        findMedicamentViewModel.medListLiveData.observe(viewLifecycleOwner) {
            list ->
            queryText(list)
            if (list.isEmpty()){
                noMedicamentsGroup.visibility = View.VISIBLE
                medicamentsList.visibility = View.GONE
            } else {
                noMedicamentsGroup.visibility = View.GONE
                medicamentsList.visibility = View.VISIBLE
            }
        }
    }

    private fun queryText(list: List<MedModel>){
        findMedicamentListAdapter.setData(list)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchMed(query.toString(), list)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchMed(newText.toString(), list)
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
        }
    }


    private fun displayDatailMedicament(medModel: MedModel) {
        println("Medicina: $medModel")
//        TODO: pasar la info a otro fragmento
    }


}