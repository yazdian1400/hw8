package ir.homework.hw8

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.homework.hw8.MainActivity.Companion.HAS_REGISTERED
import ir.homework.hw8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object{
        const val HAS_REGISTERED = "hasRegistered"
        const val FULL_NAME = "fullName"
        const val NATIONAL_CODE = "nationalCode"
        const val BIRTH_PLACE = "birthPlace"
        const val ADDRESS = "address"
        const val ZIP_CODE = "zipCode"
        const val GENDER = "gender"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hasRegistered1 = intent.getBooleanExtra(HAS_REGISTERED,false)
        if (hasRegistered1){
            val sharedPreferences: SharedPreferences =
                getSharedPreferences("kotlinSharedPreference", Context.MODE_PRIVATE)
            binding.etName.setText(sharedPreferences.getString(FULL_NAME,""))
            binding.etNationalCode.setText(sharedPreferences.getString(NATIONAL_CODE,""))
            binding.etPlaceBirth.setText(sharedPreferences.getString(BIRTH_PLACE,""))
            binding.etAddress.setText(sharedPreferences.getString(ADDRESS,""))
            binding.etZipCode.setText(sharedPreferences.getString(ZIP_CODE,""))
            when (sharedPreferences.getString(GENDER,"")){
                "male" -> binding.rbMale.isChecked = true
                else -> binding.rbFemale.isChecked = true
            }
        } else {
            val sharedPreferences: SharedPreferences =
                getSharedPreferences("kotlinSharedPreference", Context.MODE_PRIVATE)
            val hasRegistered2 = sharedPreferences.getBoolean(HAS_REGISTERED, false)
            if (hasRegistered2){
                val intent = Intent(this, ShowInfoActivity::class.java)
                startActivity(intent)
            }
        }

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btnSubmit.setOnClickListener {
            var hasError = false
            if (binding.etName.text.isNullOrBlank()) {
                binding.etName.error = "لطفا نام و نام خانوادگی را وارد کنید"
                hasError = true
            }
            if (binding.etNationalCode.text.isNullOrBlank()) {
                binding.etNationalCode.error = "لطفا کد ملی خود را وارد کنید"
                hasError = true
            }
            else if (binding.etNationalCode.text.toString().trim().length != 10) {
                binding.etNationalCode.error = "کد ملی وارد شده معتبر نیست"
                hasError = true
            }
            if (binding.etPlaceBirth.text.isNullOrBlank()) {
                binding.etPlaceBirth.error = "لطفا محل تولد خود را وارد کنید"
                hasError = true
            }
            if (binding.etAddress.text.isNullOrBlank()) {
                binding.etAddress.error = "لطفا آدرس خود را وارد کنید"
                hasError = true
            }
            if (binding.etZipCode.text.isNullOrBlank()) {
                binding.etZipCode.error = "لطفا کد پستی خود را وارد کنید"
                hasError = true
            }
            if (!hasError){
                val sharedPreferences: SharedPreferences =
                    getSharedPreferences("kotlinSharedPreference", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean(HAS_REGISTERED , true)
                editor.putString(FULL_NAME, binding.etName.text.toString())
                editor.putString(NATIONAL_CODE, binding.etNationalCode.text.toString())
                editor.putString(BIRTH_PLACE, binding.etPlaceBirth.text.toString())
                editor.putString(ADDRESS, binding.etAddress.text.toString())
                editor.putString(ZIP_CODE, binding.etZipCode.text.toString())
                val gender = when (binding.rgGender.checkedRadioButtonId){
                    binding.rbFemale.id ->  "female"
                    else -> "male"
                }
                editor.putString(GENDER, gender)
                editor.apply()

                val intent = Intent(this,ShowInfoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getHasRegisteredFromShared(): Boolean {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("kotlinSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        return sharedPreferences.getBoolean(HAS_REGISTERED, false)
    }
}