package com.onix.internship.survay.ui.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.onix.internship.survay.R
import com.onix.internship.survay.databinding.FragmentSignInBinding
import java.util.*

class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)

        binding.viewPager.adapter =
            SignInViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

        val titleList = arrayListOf(
            resources.getString(R.string.login).toUpperCase(Locale.ROOT),
            resources.getString(R.string.register).toUpperCase(Locale.ROOT)
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()

        return binding.root
    }
}