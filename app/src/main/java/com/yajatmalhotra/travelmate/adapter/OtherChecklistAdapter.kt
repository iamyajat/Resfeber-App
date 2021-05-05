package com.yajatmalhotra.travelmate.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.yajatmalhotra.travelmate.R
import com.yajatmalhotra.travelmate.model.ChecklistData


    class ImpChecklistAdapter(private val dataSet: ArrayList<ChecklistData>, private val uid: String) :
        RecyclerView.Adapter<ImpChecklistAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val check: CheckBox = view.findViewById(R.id.checkbox)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_checklist, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val data = dataSet[position]

            viewHolder.check.text = data.title
            viewHolder.check.isChecked = data.isChecked

            viewHolder.check.setOnCheckedChangeListener { buttonView, isChecked ->
                val checkbox = hashMapOf(
                    "title" to data.title,
                    "isChecked" to isChecked
                )
                val db = FirebaseFirestore.getInstance()
                db.collection("users")
                    .document(uid)
                    .collection("important_checklist")
                    .document(data.id)
                    .set(checkbox)
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

    }
