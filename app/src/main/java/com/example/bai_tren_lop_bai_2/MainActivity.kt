package com.example.bai_tren_lop_bai_2

// MainActivity.kt
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var studentList: List<Student>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchInput = findViewById<EditText>(R.id.searchInput)
        val listView = findViewById<ListView>(R.id.listView)

        // Khởi tạo danh sách sinh viên mẫu
        studentList = listOf(
            Student("Nguyễn Văn An", "20191234"),
            Student("Trần Thị Bích", "20204567"),
            Student("Lê Văn Cường", "20231234"),
            Student("Phạm Thị Dung", "20212345"),
            Student("Hoàng Văn Đạt", "20193456"),
            Student("Ngô Thị Hạnh", "20207890"),
            Student("Vũ Văn Khoa", "20226789"),
            Student("Phạm Minh Hải", "20231258"),
            Student("Trần Minh Châu", "20194567"),
            Student("Lê Ngọc Bảo", "20206789"),
            Student("Nguyễn Hữu Khải", "20196789"),
            Student("Bùi Quang Hải", "20214567"),
            Student("Đoàn Thanh Sơn", "20231233"),
            Student("Nguyễn Thị Tâm", "20192345"),
            Student("Trịnh Văn Phong", "20233456"),
            Student("Lê Thị Vân", "20196788"),
            Student("Phạm Quốc Huy", "20235678"),
            Student("Trần Mai Anh", "20198901"),
            Student("Nguyễn Đức Lâm", "20217890"),
            Student("Lý Hoàng Bảo", "20191235")
        )

        // Tạo một danh sách tên và MSSV cho Adapter
        val studentNames = studentList.map { "${it.name} - ${it.mssv}" }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
        listView.adapter = adapter

        // Xử lý tìm kiếm
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
                    }.map { "${it.name} - ${it.mssv}" }

                    adapter.clear()
                    adapter.addAll(filteredList)
                    adapter.notifyDataSetChanged()
                } else {
                    adapter.clear()
                    adapter.addAll(studentNames)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}

