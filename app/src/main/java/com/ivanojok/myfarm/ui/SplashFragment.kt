package com.ivanojok.myfarm.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ivanojok.myfarm.MainActivity
import com.ivanojok.myfarm.R
import com.ivanojok.myfarm.data.model.DataStoreService
import com.ivanojok.myfarm.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            val y = checkIsUserLoggedIn()
            if (y == "Owner Logged In"){
                //take to relevant activity
                findNavController().navigate(R.id.action_splashFragment_to_ownerFragment)
            } else if(y == "User Logged In") {
                //users page
                findNavController().navigate(R.id.action_splashFragment_to_workerFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }

            //activity?.finish()
        }

    }

    suspend fun checkIsUserLoggedIn(): String {
        val ds = DataStoreService().read(requireContext())
        Log.d("Auth", "$ds")
        var x = ""
        x = if (ds.id != null && ds.id != ""){
            if (ds.id == "1") {
                "Owner Logged In"
            } else {
                "User Logged In"
            }
        } else {
            "User absent"
        }
        return x
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}