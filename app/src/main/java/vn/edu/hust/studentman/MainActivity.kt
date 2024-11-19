package vn.edu.hust.studentman

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
  private lateinit var studentAdapter: StudentAdapter
  private val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
    StudentModel("Đỗ Minh Đức", "SV005"),
    StudentModel("Vũ Thị Hoa", "SV006"),
    StudentModel("Hoàng Văn Hải", "SV007"),
    StudentModel("Bùi Thị Hạnh", "SV008"),
    StudentModel("Đinh Văn Hùng", "SV009"),
    StudentModel("Nguyễn Thị Linh", "SV010"),
    StudentModel("Phạm Văn Long", "SV011"),
    StudentModel("Trần Thị Mai", "SV012"),
    StudentModel("Lê Thị Ngọc", "SV013"),
    StudentModel("Vũ Văn Nam", "SV014"),
    StudentModel("Hoàng Thị Phương", "SV015"),
    StudentModel("Đỗ Văn Quân", "SV016"),
    StudentModel("Nguyễn Thị Thu", "SV017"),
    StudentModel("Trần Văn Tài", "SV018"),
    StudentModel("Phạm Thị Tuyết", "SV019"),
    StudentModel("Lê Văn Vũ", "SV020")
    // Add more students as needed
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_students)
    val buttonAddNew = findViewById<Button>(R.id.btn_add_new)

    studentAdapter = StudentAdapter(this, students,
      onEdit = { student -> showEditDialog(student) },
      onDelete = { removedStudent, position ->
        showDeleteSnackbar(removedStudent, position)
      })

    recyclerView.apply {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    buttonAddNew.setOnClickListener {
      showAddNewDialog()
    }
  }

  private fun showAddNewDialog() {
    val dialogView = layoutInflater.inflate(R.layout.dialog_student_form, null)
    val editName = dialogView.findViewById<EditText>(R.id.edit_student_name)
    val editId = dialogView.findViewById<EditText>(R.id.edit_student_id)

    AlertDialog.Builder(this)
      .setTitle("Add New Student")
      .setView(dialogView)
      .setPositiveButton("Add") { _, _ ->
        val name = editName.text.toString()
        val id = editId.text.toString()
        if (name.isNotEmpty() && id.isNotEmpty()) {
          val newStudent = StudentModel(name, id)
          students.add(newStudent)
          studentAdapter.notifyItemInserted(students.size - 1)
        }
      }
      .setNegativeButton("Cancel", null)
      .show()
  }

  private fun showEditDialog(student: StudentModel) {
    val dialogView = layoutInflater.inflate(R.layout.dialog_student_form, null)
    val editName = dialogView.findViewById<EditText>(R.id.edit_student_name)
    val editId = dialogView.findViewById<EditText>(R.id.edit_student_id)

    editName.setText(student.studentName)
    editId.setText(student.studentId)

    AlertDialog.Builder(this)
      .setTitle("Edit Student")
      .setView(dialogView)
      .setPositiveButton("Save") { _, _ ->
        val updatedName = editName.text.toString()
        val updatedId = editId.text.toString()
        student.studentName = updatedName
        student.studentId = updatedId
        studentAdapter.notifyDataSetChanged()
      }
      .setNegativeButton("Cancel", null)
      .show()
  }

  private fun showDeleteSnackbar(removedStudent: StudentModel, position: Int) {
    Snackbar.make(findViewById(R.id.root_layout), "Student deleted", Snackbar.LENGTH_LONG)
      .setAction("Undo") {
        students.add(position, removedStudent)
        studentAdapter.notifyItemInserted(position)
      }
      .show()
  }
}