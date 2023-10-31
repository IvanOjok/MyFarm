package com.ivanojok.myfarm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivanojok.myfarm.R
import com.ivanojok.myfarm.data.adapter.WorkerAdapter
import com.ivanojok.myfarm.data.room.DBBuilder
import com.ivanojok.myfarm.databinding.FragmentViewWorkersBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ViewWorkersFragment : Fragment() {

    var _binding:FragmentViewWorkersBinding ?= null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewWorkersBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            getUsersFromLocalDB()
        }

    }

    suspend fun getUsersFromLocalDB() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = DBBuilder(requireContext()).initializeDB().getUserDao()
            val users = db.getUsers()

            withContext(Dispatchers.Main) {
                with(binding) {
                    val adapter = WorkerAdapter(requireContext(), users)
                    workerRecycler.adapter = adapter
                    workerRecycler.layoutManager = LinearLayoutManager(requireContext())
                }
            }

        }
    }

}