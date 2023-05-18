package com.example.kanji

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.kanji.adapter.MainAdapter
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
//    goi ham de copy db vao
    private  var db:CopyDBHelper? = null
    lateinit var cs : Cursor
    lateinit var kanjiLV : ListView
//    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var listAdapter: MainAdapter
    companion object{
        lateinit var kanjiList:ArrayList<Kanji>
    }

    lateinit var searchView: SearchView
    //array nay de luu thong tin kanji chi tiet
    lateinit var kanjidetal:ArrayList<Kanji>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //copy db
        db= CopyDBHelper(this)
        kanjiLV = findViewById(R.id.idLVkanji)
        searchView = findViewById(R.id.idSV)
        //tạo 1 danh sách lưu các chữ kanji
        kanjiList = ArrayList()
//        mở csdl để thêm kanji vào danh sách
        cs = db!!.getAllKanji()!!
        while(cs.moveToNext()){
            var x = cs.getString(3).split(",")
            val fistword = x.first().replace("[","").replace("\"","").replace("]","")
            var kj = Kanji(cs.getString(0),fistword,cs.getString(4),cs.getString(5),cs.getString(6))
            kanjiList.add(kj)
        }
        val adapter = MainAdapter(this, kanjiList)
        kanjiLV.adapter = adapter
        kanjiLV.setOnItemClickListener { parent, view, position, id ->
            val selectedKanji = adapter.getItem(position)
            val intent = Intent(this@MainActivity, DetailKanjiActivity::class.java)
            intent.putExtra("kanji", selectedKanji.kanji)
            startActivity(intent)
//            Toast.makeText(this, "Selected: ${selectedKanji.kanji}", Toast.LENGTH_SHORT).show()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        saveList()
        kanjiLvList()
    }
    fun saveList(){
        val btn_DSLike : Button = findViewById(R.id.btn_DSLike)
        btn_DSLike.setOnClickListener {
            val intent = Intent(this@MainActivity, DanhSachLuu::class.java)
            startActivity(intent)
        }
    }
    fun kanjiLvList(){
        val btn_N4 : Button = findViewById(R.id.btn_n4)
        val btn_N3 : Button = findViewById(R.id.btn_n3)
        val btn_N2 : Button = findViewById(R.id.btn_n2)
        val btn_N1 : Button = findViewById(R.id.btn_n1)

        btn_N4.setOnClickListener {
            val intent = Intent(this@MainActivity, KanjiLevelActivity::class.java)
            intent.putExtra("Level", 4)
            startActivity(intent)
        }
        btn_N3.setOnClickListener {
            val intent = Intent(this@MainActivity, KanjiLevelActivity::class.java)
            intent.putExtra("Level", 3)
            startActivity(intent)
        }
        btn_N2.setOnClickListener {
            val intent = Intent(this@MainActivity, KanjiLevelActivity::class.java)
            intent.putExtra("Level", 2)
            startActivity(intent)
        }
        btn_N1.setOnClickListener {
            val intent = Intent(this@MainActivity, KanjiLevelActivity::class.java)
            intent.putExtra("Level", 1)
            startActivity(intent)
        }
    }
}