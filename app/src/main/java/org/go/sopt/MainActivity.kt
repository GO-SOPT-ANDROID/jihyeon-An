package org.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.seminar1.R
import com.example.seminar1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userName: String? = ""
    private var userSkill: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("userName")
        userSkill = intent.getStringExtra("userSkill")
        val bundle: Bundle = Bundle()
        bundle.putString("userName", userName)
        bundle.putString("userSkill", userSkill)

        changeFragment(HomeFragment())

        binding.mainBtn.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.main_home -> {
                    changeFragment(HomeFragment())
                }
                R.id.main_gallery -> {
                    changeFragment(GalleryFragment())
                }
                R.id.main_my_page -> {
                    val fragmentMyPage = MyPageFragment()
                    fragmentMyPage.arguments = bundle
                    changeFragment(fragmentMyPage)
                }
                else -> {
                    changeFragment(HomeFragment())
                }
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }
}