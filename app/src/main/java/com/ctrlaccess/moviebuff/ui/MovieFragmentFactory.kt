package com.ctrlaccess.moviebuff.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.ctrlaccess.moviebuff.ui.fragments.FragmentDetails
import com.ctrlaccess.moviebuff.ui.fragments.FragmentMain
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val glide : RequestManager
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            FragmentMain::class.java.name -> FragmentMain(glide)
            FragmentDetails::class.java.name -> FragmentDetails(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}
