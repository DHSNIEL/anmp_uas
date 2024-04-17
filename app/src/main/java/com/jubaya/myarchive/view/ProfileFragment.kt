package com.jubaya.myarchive.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jubaya.myarchive.databinding.FragmentProfileBinding
import com.jubaya.myarchive.model.Global
import com.jubaya.myarchive.viewmodel.ProfileViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel:ProfileViewModel
    var message = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.refresh(Global.userid.toString())

        observeViewModel()

        binding.btnLogOut.setOnClickListener {
            goToLoginPage()
        }
    }

    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            var user = it

            binding.txtFirst.setText(it.firstname)
            binding.txtLast.setText(it.lastname)
            binding.txtEmail.setText(it.email)
            binding.txtUsername.setText(it.username)

            binding.btnUpdate.setOnClickListener {
                viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
                viewModel.update(Global.userid.toString(), binding.txtFirst.text.toString(),
                    binding.txtLast.text.toString(), binding.txtNewPass.text.toString())

                observeUpdateViewModel()
            }

            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            picasso.build().load(it.img_url).into(binding.imgProfile, object:
                Callback {
                override fun onSuccess() {
                    //binding.progressImage.visibility = View.INVISIBLE
                    binding.imgProfile.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            }
            )

        })
    }

    fun observeUpdateViewModel(){
        viewModel.updateLD.observe(viewLifecycleOwner, Observer {
            message = it

            Log.d("Msg", message)
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })
    }

    fun goToLoginPage() {
        Global.userid = 0
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}