package com.jubaya.myarchive.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jubaya.myarchive.databinding.FragmentPlanetDetailBinding
import com.jubaya.myarchive.model.PDetail
import com.jubaya.myarchive.viewmodel.DetailViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class PlanetDetailFragment : Fragment() {
    private lateinit var binding: FragmentPlanetDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
        arguments?.let {
            id = PlanetDetailFragmentArgs.fromBundle(it).id
        }
//        if(arguments != null){
//            id = PlanetDetailFragmentArgs.fromBundle(requireArguments()).id
//        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refresh(id)
// hmmm
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.planetLD.observe(viewLifecycleOwner, Observer { planet ->
            planet?.let {
                (requireActivity() as AppCompatActivity).supportActionBar?.title = " " + it.name
            }
        })

        viewModel.detailsLD.observe(viewLifecycleOwner, Observer { details ->
            details?.let {
                if (details.isNotEmpty()) {
                    var position = 0
                    updateDetail(position, details)

                    binding.btnNext.setOnClickListener {
                        if (position < details.size - 1) {
                            position++
                            updateDetail(position, details)
                        }
                    }
                    binding.btnPrev.setOnClickListener {
                        if (position > 0) {
                            position--
                            updateDetail(position, details)
                        }
                    }
                } else {
                    binding.txtDetail.text = "No details available"
                    binding.txtPage.text = "0/0"
                    binding.btnPrev.visibility = View.INVISIBLE
                    binding.btnNext.visibility = View.INVISIBLE
                }
            }
        })
    }

//    @SuppressLint("SetTextI18n")
//    fun observeViewModel() {
//        viewModel.planetLD.observe(viewLifecycleOwner, Observer { planet ->
//            planet?.let {
//                (requireActivity() as AppCompatActivity).supportActionBar?.title = " " + it.name
//                binding.txtName.text = it.name
//                binding.txtAuthor.text = "testHelloWorld"
//
//                Picasso.get()
//                    .load(it.img_url)
//                    .into(binding.imgPlanet, object : Callback {
//                        override fun onSuccess() {
//                            binding.progressImage.visibility = View.INVISIBLE
//                            binding.imgPlanet.visibility = View.VISIBLE
//                        }
//
//                        override fun onError(e: Exception?) {
//                            print(e.toString())
//                        }
//                    })
//            }
//            /*var planet = it
//
//            (requireActivity() as AppCompatActivity).supportActionBar?.title = " " + it.name
//            //activity?.title = it.name
//
//            binding.txtName.text = it.name
//            binding.txtAuthor.text = "@" + it.authorname
//
//            val picasso = Picasso.Builder(requireContext())
//            picasso.listener { picasso, uri, exception ->
//                exception.printStackTrace()
//            }
//
//            picasso.build().load(it.img_url).into(binding.imgPlanet, object:
//                Callback {
//                override fun onSuccess() {
//                    binding.progressImage.visibility = View.INVISIBLE
//                    binding.imgPlanet.visibility = View.VISIBLE
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//            }
//            )*/
//
//        })
//
//        viewModel.detailsLD.observe(viewLifecycleOwner, Observer { details->
//            details?.let {
//                if(details.isNotEmpty()){
//                    var position = 0
//                    updateDetail(position, details)
//
//                    binding.btnNext.setOnClickListener {
//                        if (position < details.size - 1) {
//                            position++
//                            updateDetail(position, details)
//                        }
//                    }
//                    binding.btnPrev.setOnClickListener {
//                        if (position > 0) {
//                            position--
//                            updateDetail(position, details)
//                        }
//                    }
//                }else{
//                    binding.txtDetail.text = "No details available"
//                    binding.txtPage.text = "0/0"
//                    binding.btnPrev.visibility = View.INVISIBLE
//                    binding.btnNext.visibility = View.INVISIBLE
//                }
//            }
///*            val detail = it
////            var position = 0
////
////            updateDetail(position, detail)
////
////            binding.btnNext.setOnClickListener {
////                if (position < detail.size - 1) {
////                    position += 1
////                    updateDetail(position, detail)
////                }
////            }
////
////            binding.btnPrev.setOnClickListener {
////                if (position > 0) {
////                    position -= 1
////                    updateDetail(position, detail)
////                }
////            }*/
//        })
//    }
    private fun updateDetail(position: Int, details: List<PDetail>) {
        binding.txtDetail.text = details[position].detail
        val page = position + 1
        binding.txtPage.text = "$page/${details.size}"

        binding.btnPrev.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
        binding.btnNext.visibility = if (position == details.size - 1) View.INVISIBLE else View.VISIBLE
    }

/*    private fun updateDetail(position: Int, details: ArrayList<PDetail>) {
//        if (position == 0) {
//            binding.btnPrev.visibility = View.INVISIBLE
//            binding.btnNext.visibility = View.VISIBLE
//        } else if (position == details.size - 1) {
//            binding.btnPrev.visibility = View.VISIBLE
//            binding.btnNext.visibility = View.INVISIBLE
//        } else {
//            binding.btnPrev.visibility = View.VISIBLE
//            binding.btnNext.visibility = View.VISIBLE
//        }
//
//        val page = position + 1
//        binding.txtDetail.text = details[position].detail
//        binding.txtPage.text = page.toString() + "/" + details.size.toString()
//    }*/

}