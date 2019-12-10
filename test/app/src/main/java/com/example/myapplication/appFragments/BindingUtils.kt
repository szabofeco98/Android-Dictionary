package com.example.myapplication.appFragments

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.myapplication.database.model.Word

@BindingAdapter("hunWord")
fun TextView.setHunWord(item : Word){
    text=item.hun
}


@BindingAdapter("engWord")
fun TextView.engWord(item : Word){
    text=item.eng
}