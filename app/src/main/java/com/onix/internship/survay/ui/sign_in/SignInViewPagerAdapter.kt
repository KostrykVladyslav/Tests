package com.onix.internship.survay.ui.sign_in

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.onix.internship.survay.ui.sign_in.login.LoginFragment
import com.onix.internship.survay.ui.sign_in.register.RegisterFragment


class SignInViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private val list = arrayListOf(
        LoginFragment(),
        RegisterFragment()
    )
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}