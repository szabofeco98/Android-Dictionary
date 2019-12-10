package com.example.myapplication.appFragments

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.application_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).wordDao
        val viewModelFactory = AppViewModelFactory(dataSource)


        viewModel =  ViewModelProviders.of(this,viewModelFactory).get(ApplicationViewModel::class.java)

        Log.d("words",dataSource.getAllWord().value.toString())

        val words = ArrayList<Word>()
        words.add(Word(1,"sasa","en"))
        words.add(Word(2,"g","w"))
        words.add(Word(3,"sasa","en"))
        words.add(Word(4,"g","w"))
        val adapter=WordAdapter()
        adapter.words=words
        adapter.submitList(words)
        binding.wordList.adapter=adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    fun setApp(){
        val intent = Intent( activity, MainActivity::class.java)
        this?.startActivity(intent)
    }

}
