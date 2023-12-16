package com.app.nearbyapp.base.views

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class ViewBindingActivity<Binding : ViewBinding> : AppCompatActivity() {

    protected val binding: Binding
        get() = _binding
    private lateinit var _binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding(layoutInflater)
        setContentView(_binding.root)
        setupViews()
    }

    abstract fun setupViews()

    abstract fun inflateBinding(layoutInflater: LayoutInflater): Binding
}