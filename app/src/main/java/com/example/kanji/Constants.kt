package com.example.kanji

import android.widget.Toast
import java.lang.reflect.Array.get

object Constants{
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
    private fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).shuffled().first()
    }
    fun getQuestion(): ArrayList<Question>{

        var int = DanhSachLuu.kjlist.size - 1
        val questionList = ArrayList<Question>()
        var x = rand(0,int)
        val que1 = Question(1, DanhSachLuu.kjlist[x].kanji.toString(),
            DanhSachLuu.kjlist[x].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            1
        )
        questionList.add(que1)
        var y = rand(0, int)
        val que2 = Question(1, DanhSachLuu.kjlist[y].kanji.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[y].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            3
        )

        questionList.add(que2)
        var z = rand(0, int)
        val que3 = Question(1, DanhSachLuu.kjlist[z].kanji.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[z].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            2
        )
        questionList.add(que3)

        var q = rand(0, int)
        val que4 = Question(1, DanhSachLuu.kjlist[q].kanji.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[q].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            2
        )
        questionList.add(que4)

        var w = rand(0, int)
        val que5 = Question(1, DanhSachLuu.kjlist[w].kanji.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[w].meanings.toString(),
            4
        )
        questionList.add(que5)

        var e = rand(0, int)
        val que6 = Question(1, DanhSachLuu.kjlist[e].kanji.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[e].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            3
        )
        questionList.add(que6)

        var t = rand(0, int)
        val que7 = Question(1, DanhSachLuu.kjlist[t].kanji.toString(),
            DanhSachLuu.kjlist[t].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            1
        )
        questionList.add(que7)

        var u = rand(0, int)
        val que8 = Question(1, DanhSachLuu.kjlist[u].kanji.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[u].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            2
        )
        questionList.add(que8)

        var l = rand(0, int)
        val que9 = Question(1, DanhSachLuu.kjlist[l].kanji.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[l].meanings.toString(),
            4
        )
        questionList.add(que9)

        var n = rand(0, int)
        val que10 = Question(1, DanhSachLuu.kjlist[n].kanji.toString(),
            DanhSachLuu.kjlist[n].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            DanhSachLuu.kjlist[rand(0,int)].meanings.toString(),
            1
        )
        questionList.add(que10)
        questionList.shuffle()
        return questionList
    }
}
