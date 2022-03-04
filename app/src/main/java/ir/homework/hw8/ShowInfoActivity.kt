package ir.homework.hw8

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

        showInfo()

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnEditInfo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.HAS_REGISTERED, true)
            startActivity(intent)
        }
        binding.btnNewUser.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.HAS_REGISTERED, false)
            val sharedPreferences: SharedPreferences =
                getSharedPreferences("kotlinSharedPreference", MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            startActivity(intent)
        }
        binding.btnExit.setOnClickListener {
            this.finishAffinity()
        }
    }

    private fun showInfo() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("kotlinSharedPreference", MODE_PRIVATE)
        val hasRegistered = sharedPreferences.getBoolean(MainActivity.HAS_REGISTERED, false)
        if (hasRegistered) {
            binding.tvFullName.text = "نام و نام خانوادگی: " + sharedPreferences.getString(MainActivity.FULL_NAME, "")
            binding.tvNationalCode.text = "کد ملی: " + sharedPreferences.getString(MainActivity.NATIONAL_CODE, "")
            binding.tvBirthPlace.text = "محل تولد: " + sharedPreferences.getString(MainActivity.BIRTH_PLACE, "")
            binding.tvAddress.text = "آدرس: " + sharedPreferences.getString(MainActivity.ADDRESS, "")
            binding.tvZipCode.text = "کد پستی: " + sharedPreferences.getString(MainActivity.ZIP_CODE, "")
            binding.tvGender.text = when (sharedPreferences.getString(MainActivity.GENDER, "")) {
                "male" -> "جنسیت: مرد"
                else -> "جنسیت: زن"
            }
        }
    }
}