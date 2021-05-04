package com.yajatmalhotra.travelmate.ui.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.yajatmalhotra.travelmate.R
import com.yajatmalhotra.travelmate.activity.AuthActivity
import com.yajatmalhotra.travelmate.databinding.FragmentProfileBinding
import com.yajatmalhotra.travelmate.model.ProfileData
import com.yajatmalhotra.travelmate.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        sharedPref = activity?.getSharedPreferences("login_info", Context.MODE_PRIVATE)!!
        val name = sharedPref.getString("name", "")
        val email = sharedPref.getString("email", "")
        val img = sharedPref.getString("photoUrl", "")?.replace("s96-c", "s400-c");

        binding.profile.apply {
            profileData = ProfileData(name!!, email!!)
            profilePic.load(Uri.parse(img)) {
                crossfade(true)
                crossfade(200)
            }


            logout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(context, AuthActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }
}