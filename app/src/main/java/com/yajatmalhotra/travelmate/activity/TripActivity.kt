package com.yajatmalhotra.travelmate

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yajatmalhotra.travelmate.activity.MainActivity
import com.yajatmalhotra.travelmate.databinding.ActivityTripBinding

class TripActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTripBinding
    private var uid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTripBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = this.getSharedPreferences("login_info", Context.MODE_PRIVATE)!!
        uid = sharedPref.getString("uid", "").toString()

        binding.addTrip.setOnClickListener {
            addTrip()
        }

    }

    override fun onStart() {
        super.onStart()
        val sharedPref = this.getSharedPreferences("login_info", Context.MODE_PRIVATE)!!
        val location = sharedPref.getString("location", "").toString()
        if (location != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun addTrip() {

        val v: View = LayoutInflater
            .from(this)
            .inflate(R.layout.dialog_addtrip, null)

        val dialog = MaterialAlertDialogBuilder(this)
            .setView(v)
            .setBackground(
                AppCompatResources.getDrawable(
                    this,
                    R.color.transparent
                )
            )
            .create()

        dialog.show()

        val add = v.findViewById<Button>(R.id.add)
        val date = v.findViewById<Button>(R.id.pickDate)
        val editText = v.findViewById<EditText>(R.id.add_item_text)

        date.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Select dates")
                    .build()

        }

        add.setOnClickListener {
            val sharedPref = getSharedPreferences("login_info", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("location", editText.text.toString())
                apply()
            }
            val db = FirebaseFirestore.getInstance()
            val l = hashMapOf(
                "location" to editText.text.toString(),
            )
            db.collection("users")
                .document(uid)
                .collection("trips")
                .document(editText.text.toString())
                .set(l)
                .addOnSuccessListener { documentReference ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }
        }


    }
}