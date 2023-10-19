package com.ivanojok.myfarm.ui

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ivanojok.myfarm.R
import com.ivanojok.myfarm.data.model.showError
import com.ivanojok.myfarm.data.retrofit.RetrofitService
import com.ivanojok.myfarm.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment : Fragment() {

    var _binding:FragmentLoginBinding ?= null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val x = hashMapOf<String, Any>("Ivan" to 1, "Peter" to "Mary")
//        x.put("Tree",  "Zero")


        with(binding) {
            //button
            signIn.setOnClickListener {
                when {
                    phone.text.isEmpty() -> {
                        showDialog("Invalid Input", "Empty Phone Number")
                    }
                    password.text.isEmpty() -> {
                        showDialog("Invalid Input", "Empty Password")
                    }
                    else -> {
                        progress.visibility = View.VISIBLE
                        val phone = phone.text.toString()
                        val password = password.text.toString()
                        login(phone, password)
                    }
                }
            }
        }


    }

    private fun showDialog(title:String, message:String) {
        val alert = AlertDialog.Builder(requireContext()).setTitle(title).setMessage(message)
            .setNegativeButton("CANCEL") { p0, p1 -> p0?.dismiss() }
        alert.create()
        alert.show()
    }

    fun login(phone:String, password:String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val retrofitService = RetrofitService().createResponseService()
                val loginResponse = retrofitService.loginUser(phone, password)

                withContext(Dispatchers.Main) {
                    binding.progress.visibility = View.GONE
                    if (loginResponse.response.id == "1") {
                        findNavController().navigate(R.id.action_loginFragment_to_ownerFragment)
                    } else {
                        findNavController().navigate(R.id.action_loginFragment_to_workerFragment)
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    binding.progress.visibility = View.GONE
                    showDialog("An error Occurred", showError(t))
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}