package com.mobile.registrationnavigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register1.*


class Register1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnNext.setOnClickListener{

            if (etName1.text.toString().isEmpty()){
                etName1.error = "Nama harus diisi"
            }else if (etEmail1.text.toString().isEmpty()){
                etEmail1.error = "Email harus diisi"
            }else{
                val bundle = bundleOf("name" to etName1.text.toString(), "email" to etEmail1.text.toString())
                Navigation.findNavController(it)
                    .navigate(R.id.action_register1Fragment2_to_register2Fragment2, bundle)
            }
        }

        backreg1.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_register1Fragment2_to_mainFragment2)

        }
    }
}