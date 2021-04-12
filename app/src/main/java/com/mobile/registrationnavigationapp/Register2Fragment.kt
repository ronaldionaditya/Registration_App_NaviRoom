package com.mobile.registrationnavigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlinx.android.synthetic.main.fragment_register2.*


class Register2Fragment : Fragment() {

    var get_name : String? = null
    var get_email : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        get_name = arguments?.getString("name")
        get_email = arguments?.getString("email")

        btnFinish.setOnClickListener{
            //pencekan
            if(etPassword2.text.toString().isEmpty()){
                etPassword2.error = "Password harus diisi"
            }else if (etConfirmPassword2.text.toString().isEmpty()){
                etConfirmPassword2.error = "Confirmation Password harus diisi"
            }else{
                if (etPassword2.text.toString().equals(etConfirmPassword2.text.toString())){
                    val bundle = bundleOf("name" to get_name, "email" to get_email, "password" to etPassword2.text.toString())
                    Navigation.findNavController(it)
                        .navigate(R.id.action_register2Fragment2_to_resultFragment2, bundle)
                }else{
                    Toast.makeText(context, "Password tidak sama dengan Confirmation Password", Toast.LENGTH_LONG).show()
                }
            }
        }

        backreg2.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_register2Fragment2_to_register1Fragment2)
        }
        tvHalo.text = "Halo $get_name untuk melanjutkan register silahkan mengisi password"
    }
}