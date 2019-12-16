package com.example.myapplication.appFragments.json

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.example.myapplication.R
import com.example.myapplication.database.model.Word
import com.example.myapplication.databinding.JsonFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class jsonFragment : Fragment() {

    companion object {
        fun newInstance() = jsonFragment()
    }

    private lateinit var viewModel: JsonViewModel

    private lateinit var binding: JsonFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.json_fragment, container, false)

        val adapter=JsonAdapter()

        JsonApi().getData().enqueue(object: Callback<List<JsonModel>> {
            override fun onFailure(call: Call<List<JsonModel>>, t: Throwable) {
                Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<JsonModel>>, response: Response<List<JsonModel>>) {

                val inf = response.body()

                inf?.let {
                    adapter.data=it
                }
            }

        })

        binding.jsonList.adapter=adapter


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(JsonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
