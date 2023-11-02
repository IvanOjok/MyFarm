package com.ivanojok.myfarm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ivanojok.myfarm.R
import com.ivanojok.myfarm.data.model.showError
import com.ivanojok.myfarm.data.retrofit.RetrofitService
import com.ivanojok.myfarm.data.room.DBBuilder
import com.ivanojok.myfarm.databinding.FragmentAddWorkerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddWorkerFragment : Fragment() {

    var _binding: FragmentAddWorkerBinding ?= null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddWorkerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            imageView7.setOnClickListener {
                findNavController().navigateUp()
            }

            button.setOnClickListener {
                when {
                    fName.text.isEmpty() -> {}
                    else -> {
                        val firstName = fName.text.toString()
                        val lastName = lName.text.toString()
                        val title = wTitle.text.toString()
                        val phone = wPhone.text.toString()
                        val password = wPassword.text.toString()
                        val gender = gender.text.toString()
                        val age = age.text.toString()


                        lifecycleScope.launch(Dispatchers.IO) {

                            try {
                                val data = HashMap<String, Any?>().apply {
                                    this["f_name"] = firstName
                                    this["l_name"] = lastName
                                    this["title"] = title
                                    this["phone"] = phone
                                    this["password"] = password
                                    this["gender"] = gender
                                    //this["image"] = null
                                    this["age"] = age
                                }
                                val token  = "mynameis+fefewedfdksadjedfjed_qgrefgreff"
                                val apiResponse =
                                    RetrofitService().createResponseService().addWorker(data, "Basic $token")
                                downLoadUserData()
                                val users = apiResponse.response

                                withContext(Dispatchers.Main) {
                                    showDialog("Worker Added Successful", "")
                                }

                            } catch (t: Throwable) {
                                Log.d("Error Of Users", "$t")
                                withContext(Dispatchers.Main) {
                                    showErrorDialog("Worker Adding Failed", showError(t))
                                }
                            }
                        }
                    }
                }
            }



        }
    }

    private fun showDialog(title:String, message:String) {
        val alert = AlertDialog.Builder(requireContext()).setTitle(title).setMessage(message)
            //.setNegativeButton("CANCEL") { p0, p1 -> p0?.dismiss() }
            .setPositiveButton("OK") {p0, p1 ->
                findNavController().navigateUp()
                p0.dismiss()}
        alert.create()
        alert.show()
    }

    private fun showErrorDialog(title:String, message:String) {
        val alert = AlertDialog.Builder(requireContext()).setTitle(title).setMessage(message)
            .setNegativeButton("RETRY") { p0, p1 -> p0?.dismiss() }
        alert.create()
        alert.show()
    }


    suspend fun downLoadUserData() {
        lifecycleScope.launch(Dispatchers.IO) {

            try {
                val apiResponse = RetrofitService().createResponseService().getUsers()
                val users = apiResponse.response

                val room = DBBuilder(requireContext()).initializeDB().getUserDao()

                for (i in users) {
                    room.insertUser(i)
                }

            } catch (t: Throwable) {
                Log.d("Error Of Users", showError(t))
            }
        }
    }

    fun checkUserInput(x:String):String {
        return if (x.isDigitsOnly()) {
            "Digits Found"
        } else if(x.contains("name")) {
            "Name has been found"
        } else {
            "Nothing Found"
        }
    }

}