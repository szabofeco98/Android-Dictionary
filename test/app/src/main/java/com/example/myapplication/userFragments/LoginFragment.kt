package com.example.myapplication.userFragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import com.example.myapplication.MyName
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding

    private val myName: MyName = MyName()

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.login_fragment,container,false)

        binding.test=myName

        binding.login.setOnClickListener {
            login()
        }

        return binding.root
    }

    fun login(){
        print("otttttttttttttttttttttttt")
        binding. apply {
            myName?.nickname =  username.text.toString()
            invalidateAll()
        }


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
