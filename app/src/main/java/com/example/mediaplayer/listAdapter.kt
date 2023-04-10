package com.example.mediaplayer

import android.R
import android.accounts.AccountManager.get
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get


class listAdapter(var lis: MutableList<listItem>): BaseAdapter() {

lateinit var sound:soundActivity
    lateinit var view: View
    lateinit var img: ImageView
    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }


    override fun getView(i: Int, convertView: View, parent: ViewGroup): View {
        TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent?.context)
        view =convertView ?: layoutInflater.inflate(R.layout.row_item,null)
        img= view.findViewById<View>(R.id.image) as ImageView
        val title = view.findViewById<View>(R.id.text_title) as TextView



        return view
    }
}