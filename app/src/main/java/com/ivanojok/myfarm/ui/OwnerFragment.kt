package com.ivanojok.myfarm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ivanojok.myfarm.R
import com.ivanojok.myfarm.data.model.DataStoreService
import com.ivanojok.myfarm.data.model.showError
import com.ivanojok.myfarm.data.retrofit.RetrofitService
import com.ivanojok.myfarm.data.room.DBBuilder
import com.ivanojok.myfarm.databinding.FragmentOwnerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OwnerFragment : Fragment() {
    var _binding: FragmentOwnerBinding?= null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOwnerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            downLoadUserData()

            val user = DataStoreService().read(requireContext())
            with(binding) {
                name.text = user.f_name + " " + user.l_name
                position.text = user.title

                getTotalSales()
                getTotalPurchases()

                workerCard.setOnClickListener {
                    findNavController().navigate(R.id.action_ownerFragment_to_viewWorkersFragment)
                }

            }
        }

    }

    suspend fun getTotalSales() {
         lifecycleScope.async(Dispatchers.IO) {
             try {
                 val retrofit = RetrofitService().createResponseService()
                 val x = retrofit.getSales().response

                 var sum = 0
                 for (i in x) {
                     if ((i.price ?: "0").isDigitsOnly()) {
                         sum += (i.price ?: "0").toInt()
                     }
                 }

                 withContext(Dispatchers.Main) {
                     with(binding) {
                         totalSales.text = "UGX. $sum"
                     }
                 }
             } catch (t: Throwable) {
                 withContext(Dispatchers.Main) {
                     with(binding) {
                         totalSales.text = "UGX. XXXXXX"
                     }
                 }
             }
        }

    }

    suspend fun getTotalPurchases() {
        val job = lifecycleScope.async(Dispatchers.IO) {
            try {
                val retrofit = RetrofitService().createResponseService()
                val x = retrofit.getPurchases().response

                var sum = 0
                for (i in x) {
                    if ((i.price ?: "0").isDigitsOnly()) {
                        sum += (i.price ?: "0").toInt()
                    }
                }
                withContext(Dispatchers.Main) {
                    with(binding) {
                        totalPurchases.text = "UGX. $sum"
                    }
                }
            } catch (t: Throwable) {
                with(binding) {
                    totalPurchases.text = "UGX. XXXXXX"
                }
            }
        }

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






}