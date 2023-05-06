package org.go.sopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seminar1.databinding.FragmentHomeBinding
import org.go.sopt.Adapter.RVAdapter

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    private val rvItemList = listOf<RVItem>(
        RVItem(
            "제목1",
            "짱구"
        ),
        RVItem(
            "제목2",
            "짱구"
        ),
        RVItem(
            "제목3",
            "짱구"
        ),
        RVItem(
            "제목4",
            "짱구"
        ),
        RVItem(
            "제목5",
            "짱구"
        ),
        RVItem(
            "제목6",
            "짱구"
        ),
        RVItem(
            "제목7",
            "짱구"
        ),
        RVItem(
            "제목8",
            "짱구"
        ),
        RVItem(
            "제목9",
            "짱구"
        ),
        RVItem(
            "제목10",
            "짱구"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvAdapter = RVAdapter(requireContext())
        binding.rv.adapter = rvAdapter
        binding.rv.layoutManager = LinearLayoutManager(context)
        rvAdapter.setRvItemList(rvItemList)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}