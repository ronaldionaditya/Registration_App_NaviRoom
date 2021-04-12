package com.mobile.registrationnavigationapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    //function untuk aksi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnLogin.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment2_to_homeActivity)
        }

        btnRegister.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment2_to_register1Fragment2)
        }
    }
}