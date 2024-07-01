package com.jubaya.myarchive.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jubaya.myarchive.R
import com.jubaya.myarchive.databinding.PlanetListItemBinding
import com.jubaya.myarchive.model.Planet
import com.jubaya.myarchive.viewmodel.PlanetViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.net.URL

class PlanetListAdapter(val planetList:ArrayList<Planet>)
    : RecyclerView.Adapter<PlanetListAdapter.PlanetViewHolder>(), PlanetDetailClickListener {
        private var onItemClickListener: ((Planet)-> Unit)? = null
    class PlanetViewHolder(var binding:PlanetListItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(planet: Planet, clickListener: PlanetDetailClickListener){
            binding.planet = planet
            binding.detailListener = clickListener
            Picasso.get()
                .load(planet.img_url)
                .into(binding.imgPlanet)

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val binding = PlanetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanetViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return planetList.size
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(planetList[position], this)
        //holder.binding.detailListener = this
    }

    fun updatePlanetList(newPlanets: List<Planet>){
        planetList.clear()
        planetList.addAll(newPlanets)

        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Planet)-> Unit){
        onItemClickListener = listener
    }

    override fun onPlanetDetailClick(v: View) {
        val planet = v.tag as Planet
        onItemClickListener?.invoke(planet)
//        val id = v.tag.toString()
//        val action = HomeFragmentDirections.actionHometoDetail(id)
//        Navigation.findNavController(v).navigate(action)
    }
}