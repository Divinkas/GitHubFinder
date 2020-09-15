package com.divinkas.app.githubfinder.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseScreen(@LayoutRes protected val layoutRes: Int) : Fragment() {
    open fun setupNavArgs() {}
    open fun setupUi() {}
    open fun setupLiveDataObservers() {}
    open fun setupDataBinding(view: View) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutRes, container, false)
        setupDataBinding(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupNavArgs()
        setupUi()
        setupLiveDataObservers()
    }

    fun navigateTo(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}