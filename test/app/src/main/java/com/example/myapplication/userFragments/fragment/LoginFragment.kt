package com.example.myapplication.userFragments.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.AppActivity
import com.example.myapplication.R
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.database.model.User
import com.example.myapplication.databinding.LoginFragmentBinding
import com.example.myapplication.userFragments.factory.LoginViewModellFactory
import com.example.myapplication.userFragments.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    private val user: User = User()

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        Log.i("Login Frangmet", "Called ViewModelProviders.of")

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDao
        val viewModelFactory =
            LoginViewModellFactory(
                dataSource,
                application
            )

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)

        binding.model = viewModel

        binding.lifecycleOwner = this

        binding.user=user

        binding.login.setOnClickListener {
            login()
        }

        val pref=this.activity!!.getSharedPreferences("pref",Context.MODE_PRIVATE)
        val usr=pref.getString("user","badUser")
        if(!usr.equals("badUser")){
            Log.d("usr",usr)
            setApp()
        }
        return binding.root
    }


    @SuppressLint("WrongConstant")
    fun login(){
        Log.e("e","*******************************")
        Log.i("test","meghivodik")
        Log.e("e","*******************************")

        var loggedIn: Boolean
        binding. apply {
            user?.username=username.text.toString()
            user?.password=password.text.toString()
            Log.i("user",user.toString())
            loggedIn=viewModel.login(user)
            Log.e("logged",loggedIn.toString())
        }

        if(loggedIn) {
            Log.e("e","*******************************")
            Log.i("test","sikeres")
            Log.e("e","*******************************")
            val pref=this.activity!!.getSharedPreferences("pref",Context.MODE_PRIVATE)
            val editor=pref.edit()
            editor.putString("user",user.username)
            editor.apply()
            setApp()
        }else{
            Log.e("e","*******************************")
            Log.i("test","sikeres")
            Log.e("e","*******************************")
           val toast= Toast.makeText(context,"Sikertelen",Toast.LENGTH_SHORT )
            toast.duration=100
            toast.show()
        }


    }

    fun setApp(){
        val intent = Intent(activity, AppActivity::class.java)
        activity?.startActivity(intent)
    }

}
