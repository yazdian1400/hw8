package ir.homework.hw8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import ir.homework.hw8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btnSubmit.setOnClickListener {
            if (binding.etName.text.isNullOrBlank())
                binding.etName.error = "لطفا نام و نام خانوادگی را وارد کنید"
            if (binding.etNationalCode.text.isNullOrBlank())
                binding.etNationalCode.error = "لطفا کد ملی خود را وارد کنید"
            else if (binding.etNationalCode.text.toString().trim().length != 10)
                binding.etNationalCode.error = "کد ملی وارد شده معتبر نیست"
            if (binding.etPlaceBirth.text.isNullOrBlank())
                binding.etPlaceBirth.error = "لطفا محل تولد خود را وارد کنید"
            if (binding.etAddress.text.isNullOrBlank())
                binding.etAddress.error = "لطفا آدرس خود را وارد کنید"
            if (binding.etZipCode.text.isNullOrBlank())
                binding.etZipCode.error = "لطفا کد پستی خود را وارد کنید"
        }
    }
}