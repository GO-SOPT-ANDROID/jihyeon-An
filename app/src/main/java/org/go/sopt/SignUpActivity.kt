package org.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminar1.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.idEditTv
        val pw = binding.pwEditTv
        val name = binding.nameEditTv
        val talent = binding.talentEditTv

        binding.signUpBtn.setOnClickListener {

            if(id.length() < 6){
                Snackbar.make(
                    binding.root,
                    "아이디는 6~10글자 이내입니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else {
                if (pw.length() < 8){
                    Snackbar.make(
                        binding.root,
                        "비밀번호는 8~12글자 이내입니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("id", id.text.toString())
                    intent.putExtra("pw", pw.text.toString())
                    intent.putExtra("name", name.text.toString())
                    intent.putExtra("talent", talent.text.toString())

                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}