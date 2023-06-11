package org.go.sopt

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.seminar1.R
import com.example.seminar1.databinding.ActivityMainBinding
import org.go.sopt.util.makeSnackBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userName: String? = ""
    private var userSkill: String? = ""

    private var backPressedTime: Long = 0
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime < 2000) {
                finish()
                return
            }

            binding.root.makeSnackBar("한번 더 누르면 앱이 종료됩니다.")
            backPressedTime = System.currentTimeMillis()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, callback)

        userName = intent.getStringExtra("userName")
        userSkill = intent.getStringExtra("userSkill")
        val bundle: Bundle = Bundle()
        bundle.putString("userName", userName)
        bundle.putString("userSkill", userSkill)

        changeFragment(HomeFragment())

        binding.mainBtn.setOnItemReselectedListener { item ->

            if (item.itemId == R.id.main_gallery) {
                val currentFragment =
                    supportFragmentManager.findFragmentById(R.id.main_fragment_container)
                if (currentFragment is GalleryFragment) {
                    currentFragment.scrollToTop()
                }
            }
        }

        //bottomNavigation 클릭 이벤트
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