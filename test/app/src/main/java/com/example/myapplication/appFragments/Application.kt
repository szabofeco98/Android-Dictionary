package com.example.myapplication.appFragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.myapplication.AppActivity
import com.example.myapplication.MainActivity

import com.example.myapplication.R
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.database.model.Word
import com.example.myapplication.databinding.ApplicationFragmentBinding
import com.example.myapplication.databinding.LoginFragmentBinding
import com.example.myapplication.userFragments.factory.LoginViewModellFactory

class Application : Fragment() {

    private lateinit var binding: ApplicationFragmentBinding

    companion object {
        fun newInstance() = Application()
    }

    private lateinit var viewModel: ApplicationViewModel

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.application_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).wordDao
        val viewModelFactory = AppViewModelFactory(dataSource)


        viewModel =  ViewModelProviders.of(this,viewModelFactory).get(ApplicationViewModel::class.java)

        val adapter = WordAdapter(WordListener { wordId ->
           val toast= Toast.makeText(context, "${wordId}", Toast.LENGTH_LONG)
            toast.show()
            toast.duration=20
            //  viewModel.onSleepNightClicked(wordId)
        })

        viewModel.words.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        binding.wordList.adapter=adapter
        binding.json.setOnClickListener {
            goJson()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    fun goJson(){
        val action=ApplicationDirections.actionApplicationToJsonFragment()
        view!!.findNavController().navigate(action)
    }

    fun setApp(){
        val intent = Intent( activity, MainActivity::class.java)
        this?.startActivity(intent)
    }

}
