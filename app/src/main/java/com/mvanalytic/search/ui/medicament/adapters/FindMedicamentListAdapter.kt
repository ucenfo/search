package com.mvanalytic.search.ui.medicament.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.textview.MaterialTextView
import com.mvanalytic.search.R
import com.mvanalytic.search.domian.models.MedModel

class FindMedicamentListAdapter(
    private val displayDetailOfMedication: (medModel: MedModel) -> Unit
): RecyclerView.Adapter<FindMedicamentViewHolder>() {

    private val data = mutableListOf<MedModel>()
//
    fun setData(dataSource: List<MedModel>) {
        data.clear()
        data.addAll(dataSource)
    println("tamanio ${data.size}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FindMedicamentViewHolder  {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.medicament_item_layout, parent, false)
        return FindMedicamentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FindMedicamentViewHolder, position: Int) {
        holder.bind(position, data[position], displayDetailOfMedication)
    }
}

class FindMedicamentViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(position: Int,
             medModel: MedModel,
             displayDetailOfMedication: (medModel: MedModel) -> Unit
    ){
        println("Numero de elementos: ${medModel.id}")
        with(itemView) {
            val containerMedicament = findViewById<View>(R.id.medicament_container)
            containerMedicament.setOnClickListener {
                displayDetailOfMedication.invoke(medModel)
                return@setOnClickListener
            }
            findViewById<MaterialTextView>(R.id.medicament_name).text = medModel.nameSpa
            findViewById<MaterialTextView>(R.id.brand).text = medModel.brand
            findViewById<MaterialTextView>(R.id.dose).text = medModel.dose
            findViewById<MaterialTextView>(R.id.route).text = medModel.routeSpa
            val image = findViewById<ImageView>(R.id.medicament_image)
            println("ruta: ${medModel.photoSmall}")
//            Picasso.get().load(medModel.photoSmall).into(ic_image)
            Glide
                .with(this)
                .load(medModel.photoSmall)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_image)
                .into(image)
            if (position % 2 ==0) {
                containerMedicament.background = resources.getDrawable(R.drawable.border_card_even)
            }
        }
    }
}