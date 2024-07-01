package com.jubaya.myarchive.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jubaya.myarchive.databinding.PlanetListItemBinding
import com.jubaya.myarchive.model.Planet
import com.jubaya.myarchive.viewmodel.PlanetViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PlanetListAdapter(val planetList:ArrayList<Planet>)
    : RecyclerView.Adapter<PlanetListAdapter.PlanetViewHolder>(){
    class PlanetViewHolder(var binding:PlanetListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val binding = PlanetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlanetViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return planetList.size
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.binding.txtName.text = planetList[position].name
        //holder.binding.txtAuthor.text = "@" + planetList[position].authorname
        holder.binding.txtDetail.text = planetList[position].summary
        holder.binding.btnRead.setOnClickListener {
            val action = HomeFragmentDirections.actionHometoDetail(planetList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(planetList[position].img_url).into(holder.binding.imgPlanet, object:
            Callback {
            override fun onSuccess() {
                holder.binding.progressImage.visibility = View.INVISIBLE
                holder.binding.imgPlanet.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("picasso_error", e.toString())
            }
        }
        )

    }

    fun updatePlanetList(newStudentList:ArrayList<Planet>){
        planetList.clear()
        planetList.addAll(newStudentList)

        notifyDataSetChanged()
    }
}