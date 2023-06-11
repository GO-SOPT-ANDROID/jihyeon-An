package org.go.sopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seminar1.databinding.FragmentGalleryBinding
import org.go.sopt.Adapter.GalleryHeaderRVAdapter
import org.go.sopt.Adapter.GalleryItem
import org.go.sopt.Adapter.GalleryRVAdapter

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = mutableListOf<GalleryItem>(
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
            GalleryItem(
                "짱구의 레포지터리",
                "짱구"
            ),
        )

        val header = mutableListOf<String>("짱구의 레포지터리")

        val rvAdapter = GalleryRVAdapter()
        rvAdapter.submitList(items)
        val rvHeaderAdapter = GalleryHeaderRVAdapter()
        rvHeaderAdapter.submitList(header)
        val concatAdapter = ConcatAdapter(rvHeaderAdapter, rvAdapter)
        binding.rv.apply {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun scrollToTop(){
        binding.rv.scrollToPosition(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}