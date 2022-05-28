package com.example.androidappmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androidappmvvm.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    val dbUserName = "admin"
    val dbPassword = "admin"


    lateinit var binding: FragmentLoginBinding
    lateinit var userName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ++ Binding init for Fragments ++
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        // +Remove This line +Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = binding.userName
        var password = binding.password

        binding.loginBtn.setOnClickListener{
            if (!username.text.toString().isNullOrEmpty() && !password.text.toString().isNullOrEmpty()
                && (username.text.toString().equals(dbUserName) && password.text.toString()
                    .equals(dbPassword))
            ) {
                Toast.makeText(context, "logged in successfully", Toast.LENGTH_SHORT).show()
                var action = LoginFragmentDirections.actionLoginFragmentToListFragment(username.text.toString())
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Username or password are not correct", Toast.LENGTH_SHORT).show()
            }
        }
    }


}