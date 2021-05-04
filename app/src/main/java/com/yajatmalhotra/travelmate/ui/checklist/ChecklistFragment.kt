package com.yajatmalhotra.travelmate.ui.checklist

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

class ChecklistFragment : Fragment() {

    private lateinit var checklistViewModel: ChecklistViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        checklistViewModel =
                ViewModelProvider(this).get(ChecklistViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_checklist, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        checklistViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}