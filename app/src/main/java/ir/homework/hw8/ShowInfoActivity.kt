package ir.homework.hw8

import android.content.Context
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

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("kotlinSharedPreference", Context.MODE_PRIVATE)
        val hasRegistered = sharedPreferences.getBoolean(MainActivity.HAS_REGISTERED, false)
        if (hasRegistered){
            binding.tvFullName.text = sharedPreferences.getString(MainActivity.FULL_NAME,"")
            binding.tvNationalCode.text = sharedPreferences.getString(MainActivity.NATIONAL_CODE,"")
            binding.tvBirthPlace.text = sharedPreferences.getString(MainActivity.BIRTH_PLACE,"")
            binding.tvAddress.text = sharedPreferences.getString(MainActivity.ADDRESS,"")
            binding.tvZipCode.text = sharedPreferences.getString(MainActivity.ZIP_CODE,"")
            binding.tvGender.text = when(sharedPreferences.getString(MainActivity.ZIP_CODE,"")){
                "male" -> "مرد"
                else -> "زن"
            }

        }
    }
}