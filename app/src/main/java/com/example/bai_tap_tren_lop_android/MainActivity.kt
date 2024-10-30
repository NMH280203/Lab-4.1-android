package com.example.bai_tap_tren_lop_android

// MainActivity.kt
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNumber = findViewById<EditText>(R.id.inputNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnShow.setOnClickListener {
            val n = inputNumber.text.toString().toIntOrNull()

            if (n == null || n <= 0) {
                tvError.text = "Vui lòng nhập số nguyên dương hợp lệ!"
                tvError.visibility = TextView.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            tvError.visibility = TextView.GONE
            val numbersList = when {
                radioEven.isChecked -> (0..n).filter { it % 2 == 0 }
                radioOdd.isChecked -> (1..n).filter { it % 2 != 0 }
                radioSquare.isChecked -> (0..n).filter { Math.sqrt(it.toDouble()).toInt() * Math.sqrt(it.toDouble()).toInt() == it }
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbersList)
            listView.adapter = adapter
        }
    }
}
