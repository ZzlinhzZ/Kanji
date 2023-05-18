package com.example.kanji.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.kanji.Kanji
import com.example.kanji.R
import java.util.*
import kotlin.collections.ArrayList
//
//class MainAdapter(val context: Context, var arraylist: ArrayList<Kanji>) : BaseAdapter(){
//
//    override fun getItem(position: Int): Any {
//        return arraylist[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return arraylist.size
//    }
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var view: View = View.inflate(context, R.layout.list_item, null)
//        var kanjitv: TextView = view.findViewById(R.id.tvkanji)
//        var meantv: TextView = view.findViewById(R.id.tvmean)
//        var ontv: TextView = view.findViewById(R.id.tvon)
//        var kuntv: TextView = view.findViewById(R.id.tvkun)
//
//        var kanji : Kanji = arraylist[position]
//        kanjitv.text = kanji.kanji
//        meantv.text = kanji.meanings
//        ontv.text = kanji.on_readings
//        kuntv.text = kanji.kun_readings
//        return view
//    }
//}
class MainAdapter(context: Context, data: ArrayList<Kanji>) : ArrayAdapter<Kanji>(context, R.layout.list_item, data),
    Filterable {

    private var itemList: ArrayList<Kanji> = data
    private var itemFilterList: ArrayList<Kanji> = data

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, null)

        // Lấy data hiển thị ở vị trí position
        val itemString = itemFilterList[position]

        // Đặt giá trị cho TextView trong ListView
        val textView = view.findViewById<TextView>(R.id.tvkanji)
        val textView1 = view.findViewById<TextView>(R.id.tvmean)
        val textView2 = view.findViewById<TextView>(R.id.tvon)
        val textView3 = view.findViewById<TextView>(R.id.tvkun)
        textView.text = itemString.kanji
        textView1.text = itemString.meanings
        textView2.text = itemString.on_readings
        textView3.text = itemString.kun_readings

        return view
    }

    override fun getCount(): Int {
        return itemFilterList.size
    }

    override fun getItem(position: Int): Kanji {
        return itemFilterList[position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                itemFilterList = if (charString.isBlank()) {
                    itemList
                } else {
                    val filteredList = ArrayList<Kanji>()
                    for (row in itemList) {
                        if (row.kanji?.toLowerCase()?.contains(charString.toLowerCase()) == true) {
                            filteredList.add(row)
                        }else if (row.meanings?.contains(charString)==true){
                            filteredList.add(row)
                        }else if(row.on_readings?.contains(charString) == true){
                            filteredList.add(row)
                        }else if(row.kun_readings?.contains(charString) == true){
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = itemFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemFilterList = results?.values as ArrayList<Kanji>
                notifyDataSetChanged()
            }
        }
    }
}
