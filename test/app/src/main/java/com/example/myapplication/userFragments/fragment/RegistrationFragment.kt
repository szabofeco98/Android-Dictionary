package com.example.myapplication.userFragments.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.database.model.User
import com.example.myapplication.databinding.RegistrationFragmentBinding
import com.example.myapplication.userFragments.factory.RegistrationViewModellFactory
import com.example.myapplication.userFragments.viewmodel.RegistrationViewModel


class RegistrationFragment : Fragment() {
    private lateinit var binding: RegistrationFragmentBinding

    private val user: User = User()

    companion object {
        fun newInstance() =
            RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDao
        val viewModelFactory = RegistrationViewModellFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(RegistrationViewModel::class.java)

        binding.lifecycleOwner = this

        binding.user=user

        binding.registration.setOnClickListener {
            registration()
        }

        return binding.root
    }

      fun registration(){
          Log.i("test","meghivodik")
          binding. apply {
              Log.e("e","itt")
              user?.username=username.text.toString()
              user?.password=password.text.toString()
              user?.email=email.text.toString()
              user?.name=name.text.toString()

              Log.i("user",user.toString())

              val reg :String?=viewModel.registration(user)

              Log.e("logged",reg!!)

          }
      }
}
