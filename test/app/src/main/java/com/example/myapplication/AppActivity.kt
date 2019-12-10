package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*


class AppActivity : AppCompatActivity(){

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.appNavHostFragment)

       // bottom_nav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)

        val pref=getSharedPreferences("pref",Context.MODE_PRIVATE)
        Log.d("preg",pref.getString("user","nagyabaj"))


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        val inflater = menuInflater
        inflater.inflate(R.menu.app_menu, menu)
        val s = SpannableString(menu!!.findItem(R.id.logoutFragment).title.toString())
        s.setSpan(ForegroundColorSpan(Color.BLACK), 0, s.length, 0)
        menu!!.findItem(R.id.logoutFragment).setTitle(s)
        return super.onCreateOptionsMenu(menu)
    }

     override fun  onOptionsItemSelected(item:MenuItem):Boolean{
        if(item.itemId==R.id.logoutFragment){
            setApp()
            Log.e("e",item.title.toString())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun setApp(){
        val pref=getSharedPreferences("pref",Context.MODE_PRIVATE)
        val edit =pref.edit()
        edit.clear()
        edit.apply()

        val intent = Intent( this, MainActivity::class.java)
        this?.startActivity(intent)
    }

    override fun onBackPressed() {
    }
}