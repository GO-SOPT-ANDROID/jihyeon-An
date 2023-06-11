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
    private lateinit var rvAdapter: GalleryRVAdapter
    private lateinit var rvHeaderAdapter: GalleryHeaderRVAdapter

    private val items = mutableListOf<GalleryItem>()

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

        initRecyclerView()
        binding.btnDelete.isEnabled = false
        clickItem()
        binding.btnDelete.setOnClickListener {
            deleteItem()
        }
    }

    private fun deleteItem() {
        val itemList = rvAdapter.getSelectedItemList()
        items.removeAll(itemList)
        rvAdapter.submitList(items)
        binding.rv.adapter = rvAdapter
    }

    private fun clickItem() {
        rvAdapter.setOnItemClickListener { response ->
            binding.btnDelete.isEnabled = rvAdapter.getSelectedItem() > 0
        }
    }

    private fun initRecyclerView() {
        items.add(GalleryItem("짱구의 레포지터리1", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리2", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리3", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리4", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리5", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리6", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리7", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리8", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리9", "짱구"))
        items.add(GalleryItem("짱구의 레포지터리10", "짱구"))


        val header = mutableListOf<String>("짱구의 레포지터리")

        rvAdapter = GalleryRVAdapter()
        rvAdapter.submitList(items)
        rvHeaderAdapter = GalleryHeaderRVAdapter()
        rvHeaderAdapter.submitList(header)
        val concatAdapter = ConcatAdapter(rvHeaderAdapter, rvAdapter)
        binding.rv.apply {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    fun scrollToTop() {
        binding.rv.scrollToPosition(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}