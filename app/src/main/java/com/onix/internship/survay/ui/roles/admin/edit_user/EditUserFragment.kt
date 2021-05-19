package com.onix.internship.survay.ui.roles.admin.edit_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onix.internship.survay.R
import com.onix.internship.survay.data.local.SurveyDatabase
import com.onix.internship.survay.databinding.FragmentEditUserBinding
import com.onix.internship.survay.ui.roles.admin.AdminViewModel
import com.onix.internship.survay.ui.roles.admin.AdminViewModelFactory

class EditUserFragment : Fragment() {
    lateinit var binding: FragmentEditUserBinding
    private val args: EditUserFragmentArgs by navArgs()
    private val viewModel: EditUserViewModel by viewModels {
        EditUserViewModelFactory(
            args.user,
            SurveyDatabase.getDatabase(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditUserBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.user_roles,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.userDetailsRole.adapter = adapter
        }

        binding.userDetailsRole.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.isSelected(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        binding.userDetailsRole.setSelection(viewModel.user.role)
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}