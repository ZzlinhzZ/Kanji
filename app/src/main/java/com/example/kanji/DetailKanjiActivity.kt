package com.example.kanji

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetailKanjiActivity : AppCompatActivity() {
    private var z = 0
    private var onsize = 0
    private var kunsize = 0
    private var namesize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kanji)
        setValuetoView()

    }

    private fun setTextView(int: Int){
        val tvMean1 : TextView = findViewById(R.id.mean2)
        val tvMean2 : TextView = findViewById(R.id.mean3)
        val tvMean3 : TextView = findViewById(R.id.mean4)
        if (int < 2){
            tvMean1.visibility = View.GONE
            tvMean2.visibility = View.GONE
            tvMean3.visibility = View.GONE
        }else if(int < 3){
            tvMean2.visibility = View.GONE
            tvMean3.visibility = View.GONE
        }else if (int < 4){
            tvMean3.visibility = View.GONE
        }
    }
    private fun setTextViewOn(int: Int){
        val tvOn1 : TextView = findViewById(R.id.tvOn2)
        val tvOn2 : TextView = findViewById(R.id.tvOn3)
        val tvOn3 : TextView = findViewById(R.id.tvOn4)
        if (int < 2){
            tvOn1.visibility = View.GONE
            tvOn2.visibility = View.GONE
            tvOn3.visibility = View.GONE
        }else if(int < 3){
            tvOn2.visibility = View.GONE
            tvOn3.visibility = View.GONE
        }else if (int < 4){
            tvOn3.visibility = View.GONE
        }
    }
    private fun setTextViewKun(int: Int){
        val tvKun1 : TextView = findViewById(R.id.tvKun2)
        val tvKun2 : TextView = findViewById(R.id.tvKun3)
        val tvKun3 : TextView = findViewById(R.id.tvKun4)
        if (int < 2){
            tvKun1.visibility = View.GONE
            tvKun2.visibility = View.GONE
            tvKun3.visibility = View.GONE
        }else if(int < 3){
            tvKun2.visibility = View.GONE
            tvKun3.visibility = View.GONE
        }else if (int < 4){
            tvKun3.visibility = View.GONE
        }
    }
    private fun setTextViewName(int: Int){
        val tvName1 : TextView = findViewById(R.id.tvName2)
        val tvName2 : TextView = findViewById(R.id.tvName3)
        val tvName3 : TextView = findViewById(R.id.tvName4)
        if (int < 2){
            tvName1.visibility = View.GONE
            tvName2.visibility = View.GONE
            tvName3.visibility = View.GONE
        }else if(int < 3){
            tvName2.visibility = View.GONE
            tvName3.visibility = View.GONE
        }else if (int < 4){
            tvName3.visibility = View.GONE
        }
    }
    private fun setValuetoView(){
        //        font chữ
        val myFont : Typeface = Typeface.createFromAsset(this.assets, "font/KanjiStroke.ttf")
        val tvKanji : TextView = findViewById(R.id.textView)
        tvKanji.typeface = myFont
        // Khai báo TextView
        val tvMean0 : TextView = findViewById(R.id.textView2)
        val tvMean1 : TextView = findViewById(R.id.mean2)
        val tvMean2 : TextView = findViewById(R.id.mean3)
        val tvMean3 : TextView = findViewById(R.id.mean4)

        val tvOn0 : TextView = findViewById(R.id.textView3)
        val tvOn1 : TextView = findViewById(R.id.tvOn2)
        val tvOn2 : TextView = findViewById(R.id.tvOn3)
        val tvOn3 : TextView = findViewById(R.id.tvOn4)

        val tvKun0 : TextView = findViewById(R.id.textView4)
        val tvKun1 : TextView = findViewById(R.id.tvKun2)
        val tvKun2 : TextView = findViewById(R.id.tvKun3)
        val tvKun3 : TextView = findViewById(R.id.tvKun4)

        val tvName0 : TextView = findViewById(R.id.textView5)
        val tvName1 : TextView = findViewById(R.id.tvName2)
        val tvName2 : TextView = findViewById(R.id.tvName3)
        val tvName3 : TextView = findViewById(R.id.tvName4)

        var btn_Save : Button = findViewById(R.id.button)
        val copyDBHelper = CopyDBHelper(this)
        var kanjiStr = intent.getStringExtra("kanji").toString()
        val cursor = copyDBHelper.searchKanji(kanjiStr)
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                var kanji = cursor.getString(cursor.getColumnIndexOrThrow("kanji"))
                var meaning = cursor.getString(cursor.getColumnIndexOrThrow("meanings"))
                var on = cursor.getString(cursor.getColumnIndexOrThrow("on_readings"))
                var kun = cursor.getString(cursor.getColumnIndexOrThrow("kun_readings"))
                var name = cursor.getString(cursor.getColumnIndexOrThrow("name_readings"))
                var save = cursor.getString(cursor.getColumnIndexOrThrow("save"))
//                Toast.makeText(this, "hmm $save", Toast.LENGTH_SHORT).show()
                // làm gì đó với kanji và meaning ở đây ...
                tvKanji.text = kanji
                tvMean0.text = meaning
                tvOn0.text = on
                tvKun0.text = kun
                tvName0.text = name
                btn_Save.setOnClickListener {
                    if (save.toInt() == 1) {
                        copyDBHelper.setSave(kanji)?.moveToFirst()
                    }else {
                        copyDBHelper.setSave1(kanji)?.moveToFirst()
                    }
                    recreate()
                }
                if (save.toInt() == 1){
                    val newColor = Color.parseColor("#FF0000")
                    btn_Save.setBackgroundColor(newColor)
                    btn_Save.setText("This word had been save!")
                }
            }
//            MEAN
            var x = tvMean0.text.split(",")
            z = x.size
//            Toast.makeText(this, "size: ${x.size}", Toast.LENGTH_SHORT).show()
            tvMean0.text = x[0].replace("[","").replace("\"","").replace("]","")
            if (x.size > 1)
                tvMean1.text = x[1].replace("[","").replace("\"","").replace("]","")
                tvMean1.visibility = View.VISIBLE
                tvMean2.visibility = View.GONE
                tvMean3.visibility = View.GONE
            if (x.size > 2)
                tvMean2.text = x[2].replace("[","").replace("\"","").replace("]","")
                tvMean2.visibility = View.VISIBLE
                tvMean3.visibility = View.GONE
            if (x.size > 3)
                tvMean3.text = x[3].replace("[","").replace("\"","").replace("]","")
                tvMean3.visibility = View.VISIBLE

//            ONYOMI
            var onyomi = tvOn0.text.split(",")
            onsize = onyomi.size
//            Toast.makeText(this, "size: ${x.size}", Toast.LENGTH_SHORT).show()
            tvOn0.text = onyomi[0].replace("[","").replace("\"","").replace("]","")
            if (onyomi.size > 1)
                tvOn1.text = onyomi[1].replace("[","").replace("\"","").replace("]","")
                tvOn1.visibility = View.VISIBLE
                tvOn2.visibility = View.GONE
                tvOn3.visibility = View.GONE
            if (onyomi.size > 2)
                tvOn2.text = onyomi[2].replace("[","").replace("\"","").replace("]","")
                tvOn2.visibility = View.VISIBLE
                tvOn3.visibility = View.GONE
            if (onyomi.size > 3)
                tvOn3.text = onyomi[3].replace("[","").replace("\"","").replace("]","")
                tvOn3.visibility = View.VISIBLE


//            KUNYOMI
            var kunyomi = tvKun0.text.split(",")
            kunsize = kunyomi.size
//            Toast.makeText(this, "size: ${x.size}", Toast.LENGTH_SHORT).show()
            tvKun0.text = kunyomi[0].replace("[","").replace("\"","").replace("]","")
            if (kunyomi.size > 1)
                tvKun1.text = kunyomi[1].replace("[","").replace("\"","").replace("]","")
            tvKun1.visibility = View.VISIBLE
            tvKun2.visibility = View.GONE
            tvKun3.visibility = View.GONE
            if (kunyomi.size > 2)
                tvKun2.text = kunyomi[2].replace("[","").replace("\"","").replace("]","")
            tvKun2.visibility = View.VISIBLE
            tvKun3.visibility = View.GONE
            if (kunyomi.size > 3)
                tvKun3.text = kunyomi[3].replace("[","").replace("\"","").replace("]","")
            tvKun3.visibility = View.VISIBLE

//            NAMEYOMI
            var nameyomi = tvName0.text.split(",")
            namesize = nameyomi.size
//            Toast.makeText(this, "size: ${x.size}", Toast.LENGTH_SHORT).show()
            tvName0.text = nameyomi[0].replace("[","").replace("\"","").replace("]","")
            if (nameyomi.size > 1)
                tvName1.text = nameyomi[1].replace("[","").replace("\"","").replace("]","")
            tvName1.visibility = View.VISIBLE
            tvName2.visibility = View.GONE
            tvName3.visibility = View.GONE
            if (nameyomi.size > 2)
                tvName2.text = nameyomi[2].replace("[","").replace("\"","").replace("]","")
            tvName2.visibility = View.VISIBLE
            tvName3.visibility = View.GONE
            if (nameyomi.size > 3)
                tvName3.text = nameyomi[3].replace("[","").replace("\"","").replace("]","")
            tvName3.visibility = View.VISIBLE
        }
        setTextView(z)
        setTextViewOn(onsize)
        setTextViewKun(kunsize)
        setTextViewName(namesize)
        cursor?.close()
    }
}
