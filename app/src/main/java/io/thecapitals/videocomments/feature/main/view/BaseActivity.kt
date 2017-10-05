package io.thecapitals.videocomments.feature.main.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity(), LifecycleOwner {

    lateinit var binding: B

    lateinit var viewModel: VM

    abstract fun prepareUseCase()

    abstract fun prepareViewModel(): VM

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = prepareViewModel()
        prepareUseCase()
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
    }
}