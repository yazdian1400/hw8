package ir.homework.hw8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.homework.hw8.databinding.ActivityShowInfoBinding

class ShowInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_info)
        binding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}