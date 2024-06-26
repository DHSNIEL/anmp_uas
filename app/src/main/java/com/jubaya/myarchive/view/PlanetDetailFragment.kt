package com.jubaya.myarchive.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jubaya.myarchive.R
import com.jubaya.myarchive.databinding.FragmentHomeBinding
import com.jubaya.myarchive.databinding.FragmentPlanetDetailBinding
import com.jubaya.myarchive.model.Global
import com.jubaya.myarchive.model.PDetail
import com.jubaya.myarchive.viewmodel.DetailViewModel
import com.jubaya.myarchive.viewmodel.ProfileViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PlanetDetailFragment : Fragment() {
    private lateinit var binding:FragmentPlanetDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
        if(arguments != null){
            id = PlanetDetailFragmentArgs.fromBundle(requireArguments()).id
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refresh(id)

        observeViewModel()

    }

    fun observeViewModel(){
        viewModel.planetLD.observe(viewLifecycleOwner, Observer {
            var planet = it

            (requireActivity() as AppCompatActivity).supportActionBar?.title = " " + it.name
            //activity?.title = it.name

            binding.txtName.text = it.name
            binding.txtAuthor.text = "@" + it.authorname

            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }

            picasso.build().load(it.img_url).into(binding.imgPlanet, object:
                Callback {
                override fun onSuccess() {
                    binding.progressImage.visibility = View.INVISIBLE
                    binding.imgPlanet.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            }
            )

        })
        viewModel.detailsLD.observe(viewLifecycleOwner, Observer {
            val detail = it
            var position = 0

            updateDetail(position, detail)

            binding.btnNext.setOnClickListener {
                if (position < detail.size - 1) {
                    position += 1
                    updateDetail(position, detail)
                }
            }

            binding.btnPrev.setOnClickListener {
                if (position > 0) {
                    position -= 1
                    updateDetail(position, detail)
                }
            }
        })
    }

    fun updateDetail(position:Int, details:ArrayList<PDetail>){
        if(position == 0){
            binding.btnPrev.visibility = View.INVISIBLE
            binding.btnNext.visibility = View.VISIBLE
        } else if (position == details.size - 1){
            binding.btnPrev.visibility = View.VISIBLE
            binding.btnNext.visibility = View.INVISIBLE
        } else {
            binding.btnPrev.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
        }

        val page = position + 1
        binding.txtDetail.text = details[position].detail
        binding.txtPage.text = page.toString() + "/" + details.size.toString()
    }

}