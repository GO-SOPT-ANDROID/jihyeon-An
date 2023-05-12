package org.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminar1.R
import com.example.seminar1.databinding.ActivityHomeBinding
import org.go.sopt.Adapter.ViewPagerAdapter
import org.go.sopt.model.ResponseUser
import retrofit2.Call
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val reqresService = ReqresServicePool.reqresService
    private  lateinit var viewPagerAdapter : ViewPagerAdapter

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var userImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter = ViewPagerAdapter()
        binding.viewPager.adapter=viewPagerAdapter
        initViewPager()
    }

    private fun initViewPager() {
        reqresService.getUser(
            1
        ).enqueue(object : retrofit2.Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        viewPagerAdapter.setItemList(it)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}