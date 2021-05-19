package com.onix.internship.survay.ui.sign_in

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.onix.internship.survay.R
import com.onix.internship.survay.databinding.FragmentSignInBinding
import java.util.*

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)

        binding.viewPager.adapter =
            SignInViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

        val titleList = arrayListOf(
            resources.getString(R.string.login).uppercase(Locale.ROOT),
            resources.getString(R.string.register).uppercase(Locale.ROOT)
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()

        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
        if (preferences != null) {
            if (preferences.getBoolean("is_signed", false)) {
                findNavController().navigate(R.id.action_signInFragment_to_AdminFragment)
            }
        }

        return binding.root
    }
}