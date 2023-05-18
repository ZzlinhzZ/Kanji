package com.example.kanji

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import com.example.kanji.adapter.GridAdapter

class DanhSachLuu : AppCompatActivity(), AdapterView.OnItemClickListener {

    private  var db:CopyDBHelper? = null
    lateinit var cs : Cursor
    lateinit var kanjiLV : GridView
//    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var listAdapter: GridAdapter
    companion object{
        lateinit var kjlist:ArrayList<Kanji>
    }
    lateinit var kanjiList:ArrayList<String>
    //array nay de luu thong tin kanji chi tiet
    lateinit var kanjidetal:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_luu)
        ShowGridView()
        buttonAction()
    }

    fun ShowGridView(){
        db= CopyDBHelper(this)
        kanjiLV = findViewById(R.id.grid)
        //tạo 1 danh sách lưu các chữ kanji

        kanjiList = ArrayList()
        kjlist = ArrayList()

//        mở csdl để thêm kanji vào danh sách
        cs = db!!.getSaveKanji()!!
        while(cs.moveToNext()){
            var x = cs.getString(3).split(",")
            val fistword = x.first().replace("[","").replace("\"","").replace("]","")
            var kj = Kanji(cs.getString(0),fistword,cs.getString(4),cs.getString(5),cs.getString(6))
            kjlist.add(kj)
        }

//        hiển thị ra cái listView
        listAdapter = GridAdapter(applicationContext, kjlist!!)
        kanjiLV?.adapter = listAdapter
        kanjiLV?.onItemClickListener = this
    }

    fun buttonAction(){
        val btn_QS : Button = findViewById(R.id.btn_viewQS)
        btn_QS.setOnClickListener {
            val intent = Intent(this, MultiQuestionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var kanji: Kanji = kjlist[p2]
        val intent = Intent(this@DanhSachLuu, DetailKanjiActivity::class.java)
        intent.putExtra("kanji", kanji.kanji)
        startActivity(intent)
//        Toast.makeText(applicationContext, kanji.kanji, Toast.LENGTH_LONG).show()
    }
}