package org.go.sopt

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.seminar1.R
import com.example.seminar1.databinding.FragmentMyPageBinding
import com.google.android.material.snackbar.Snackbar
class MyPageFragment : Fragment() {

    private var _binding : FragmentMyPageBinding? = null
    private val binding : FragmentMyPageBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    private var userName: String? = ""
    private var userSkill: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = arguments?.getString("userName")
        userSkill = arguments?.getString("userSkill")
        Log.e("userName", userName.toString())
        Log.e("userSkill", userSkill.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //사용자 정보 설정
        binding.nameTv.text = "NAME : $userName"
        binding.talentTv.text = "SKILL : $userSkill"
        //
        clickLogoutBtn()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun clickLogoutBtn() {
        binding.btnLogout.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
            dialog.apply {
                setMessage("로그아웃 하시겠습니까?")
                setPositiveButton("확인",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    (activity as MainActivity).deleteAutoLogin()
                })
                setNegativeButton("취소",null)
            }
            dialog.show()
        }
    }


}