package com.mvanalytic.search.ui.medicament.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
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


class FindMedicamentFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var medicamentsList: RecyclerView
    private lateinit var noMedicamentsGroup: Group

    private lateinit var mainViewModel: MainViewModel

    private lateinit var findMedicamentViewModel: FindMedicamentViewModel

    var medicina = arrayOf("alberto", "Alvaro", "Ana", "Amparo",
        "Bartolo", "Bernardo", "Carla", "Carolina")

    private lateinit var medicamentAdapter : ArrayAdapter<String>
//    = ArrayAdapter(this,
//    android.R.layout.simple_dropdown_item_1line, medicina)
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

    private fun displayDatailMedicament(medModel: MedModel) {
        println("Medicina: $medModel")
//        TODO: pasar la info a otro fragmento
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
            medicamentAdapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, medicina)
            searchView = findViewById(R.id.search_view)
//            queryText()
            noMedicamentsGroup = findViewById(R.id.no_medicaments_group)
            medicamentsList = findViewById(R.id.medicaments_list)
            medicamentsList.adapter = findMedicamentListAdapter
//            medicamentsList.addItemDecoration(
//                DividerItemDecoration(
//                    context,
//                    RecyclerView.VERTICAL
//                )
//            )
            medicamentsList.layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
        }

    }

    private fun verDetalle(view: View){
        println("ver detalle: $view")
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
                searchMed(query.toString(), list)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchMed(newText.toString(), list)
                return false
            }
        })
    }

    private fun searchMed(text: String, list: List<MedModel>) {
        val leakedData = mutableListOf<MedModel>()
        list?.let { list ->
            val marchingItems = list.filter { medModel ->
                medModel.nameEng.contains(text, ignoreCase = true) ||
                        medModel.nameSpa.contains(text, ignoreCase = true) ||
                        medModel.brand.contains(text, ignoreCase = true)
            }
            if (marchingItems.isEmpty()) {
                findMedicamentListAdapter.setData(list)
                Toast.makeText(context, "no hay coincidencia", Toast.LENGTH_SHORT).show()
            } else {
                leakedData.addAll(marchingItems)
                findMedicamentListAdapter.setData(leakedData)
            }
        }
    }





}