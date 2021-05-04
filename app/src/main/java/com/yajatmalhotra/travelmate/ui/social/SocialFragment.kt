package com.yajatmalhotra.travelmate.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yajatmalhotra.travelmate.R
import com.yajatmalhotra.travelmate.viewmodel.ChecklistViewModel
import com.yajatmalhotra.travelmate.viewmodel.SocialViewModel

class SocialFragment : Fragment() {

    private lateinit var socialViewModel: SocialViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        socialViewModel =
                ViewModelProvider(this).get(SocialViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_social, container, false)

        return root
    }
}