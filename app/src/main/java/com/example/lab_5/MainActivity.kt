package com.example.lab_5

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            val list = savedInstanceState.getStringArrayList("layoutItems")
            list?.forEach {
                addLabel(it)
            }

            val posY = savedInstanceState.getInt("scrollPos_Y")
            val scrollMain: ScrollView = findViewById(R.id.scroll_main)
            scrollMain.scrollY = posY
        }

        // Кнопки
        val btnAdd: Button = findViewById(R.id.btn_add)
        val btnDel: Button = findViewById(R.id.btn_del)

        // Layouts
        val layoutMain: LinearLayout = findViewById(R.id.layout_main)

        fun rand(): String {
            return (-1000..1000).random().toString()
        }

        btnAdd.setOnClickListener {
            addLabel(rand())
        }

        btnDel.setOnClickListener {
            layoutMain.removeAllViews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val layoutMain: LinearLayout = findViewById(R.id.layout_main)
        outState.putStringArrayList("layoutItems", getLabelsList())

        val scrollMain: ScrollView = findViewById(R.id.scroll_main)
        outState.putInt("scrollPos_Y", scrollMain.scrollY)
    }

    private fun getLabelsList(): ArrayList<String> {
        val layoutMain: LinearLayout = findViewById(R.id.layout_main)
        val list = ArrayList<String>()
        for (i in 0 until layoutMain.childCount) {
            val v: View = layoutMain.getChildAt(i)
            if (v is TextView) {
                list.add(v.text.toString())
            }
        }
        return list
    }

    private fun addLabel(txt: String) {
        val label = TextView(this)
        label.text = txt
        label.textSize = 30f

        val layoutMain: LinearLayout = findViewById(R.id.layout_main)
        layoutMain.addView(label)
    }
}