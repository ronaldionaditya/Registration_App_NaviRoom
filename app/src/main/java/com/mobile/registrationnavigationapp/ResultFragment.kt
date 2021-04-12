package com.mobile.registrationnavigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlinx.android.synthetic.main.fragment_register2.*
import kotlinx.android.synthetic.main.fragment_result.*


class ResultFragment : Fragment() {

    var get_name: String? = null
    var get_email: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        get_name = arguments?.getString("name")
        get_email = arguments?.getString("email")

        btnBacktoLogin.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_resultFragment2_to_mainFragment2)
        }

        backresult.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_resultFragment2_to_register2Fragment2)
        }

        tvName.text = get_name
        tvEmail.text = get_email
    }
}