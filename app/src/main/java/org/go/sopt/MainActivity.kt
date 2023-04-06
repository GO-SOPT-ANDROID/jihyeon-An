package org.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.seminar1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var getResult : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.idEditTv
        val pw = binding.pwEditTv

        getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result ->
            if(result.resultCode == RESULT_OK){
                val getId = result.data?.getStringExtra("id") ?: ""
                val getPw = result.data?.getStringExtra("pw") ?: ""
                val getName = result.data?.getStringExtra("name") ?: ""
                val getTalent = result.data?.getStringExtra("talent") ?: ""

                Snackbar.make(
                    binding.root,
                    "회원가입이 완료되었습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()

                binding.loginBtn.setOnClickListener {
                    if(id.text.toString() == getId && pw.text.toString() == getPw){
                        val intent = Intent(this, MyPageActivity::class.java)
                        intent.putExtra("name", getName)
                        intent.putExtra("talent", getTalent)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResult.launch(intent)
        }

    }
}