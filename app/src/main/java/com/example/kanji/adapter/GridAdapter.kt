package com.example.kanji.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kanji.Kanji
import com.example.kanji.R

class GridAdapter(var context:Context, var arrayList: ArrayList<Kanji>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.grid_item, null)
        var kanjitv: TextView = view.findViewById(R.id.kanji)
        var meantv: TextView = view.findViewById(R.id.mean)

        var kanji : Kanji = arrayList[p0]
        kanjitv.text = kanji.kanji
        meantv.text = kanji.meanings

        return view
    }
}