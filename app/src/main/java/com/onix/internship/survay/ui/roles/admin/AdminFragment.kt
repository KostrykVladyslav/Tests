package com.onix.internship.survay.ui.roles.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.data.local.SurveyDatabase
import com.onix.internship.survay.data.local.user.User
import com.onix.internship.survay.databinding.FragmentAdminBinding
import com.onix.internship.survay.ui.roles.admin.admin_adapter.AdminAdapter

class AdminFragment : Fragment() {

    private lateinit var binding: FragmentAdminBinding
    private lateinit var database: SurveyDatabase
    private val viewModel: AdminViewModel by viewModels {
        AdminViewModelFactory(SurveyDatabase.getDatabase(requireContext()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        database = SurveyDatabase.getDatabase(requireContext())

        binding.recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            viewModel.userList.observe(viewLifecycleOwner, {
                adapter = AdminAdapter(it)
            })

        }
        super.onViewCreated(view, savedInstanceState)
    }
}