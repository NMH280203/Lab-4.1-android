package com.example.btvn_android_29_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter
    private lateinit var emailList: List<Email>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Tạo dữ liệu mẫu cho danh sách email
        emailList = listOf(
            Email("Edurila.com", "$19 Only - Bestselling courses", "Are you looking to learn web designing?", "12:34 PM"),
            Email("Chris Abad", "Campaign Monitor", "Help us make Campaign Monitor better!", "11:22 AM"),
            Email("Tuto.com", "Formation gratuite", "Photoshop, SEO, Blender, CSS...", "10:45 AM"),
            Email("Support", "Suivi de vos services", "Nous avons un suivi pour vous", "9:15 AM"),
            Email("Matt from Ionic", "The New Ionic Creator", "Announcing the all-new Creator...", "8:30 AM"),
            Email("Google Alerts", "New Alert for 'android development'", "Top news on Android development", "7:20 AM"),
            Email("Udemy", "Discounted Courses", "50% off on all courses", "6:15 AM"),
            Email("Medium", "Latest on tech", "How AI is changing the world", "5:45 AM"),
            Email("LinkedIn", "Connections Update", "You have 5 new connection requests", "5:00 AM"),
            Email("GitHub", "New Pull Request", "There is a new PR in your repo", "4:20 AM")
        )

        // Thiết lập Adapter cho RecyclerView
        emailAdapter = EmailAdapter(emailList)
        recyclerView.adapter = emailAdapter
    }
}
