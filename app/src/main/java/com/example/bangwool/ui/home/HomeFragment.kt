package com.example.bangwool.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.bangwool.R
import com.example.bangwool.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    var itemList: ArrayList<HomeItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeRecyclerView.setOnClickListener {
            val intent = Intent(activity, TimerActivity::class.java)
            startActivity(intent)
        }

        initDummyData()
        init()
        return binding.root
    }

    fun init() {
        binding.apply {
//            homeRecyclerView.layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            homeRecyclerView.adapter = HomeAdapter(requireContext(), itemList)

            val swipeHelperCallback = SwipeHelperCallback().apply {
                setClamp(dpToPx(160f, requireContext()))
            }
            val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
            itemTouchHelper.attachToRecyclerView(homeRecyclerView)

            homeRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = HomeAdapter(requireContext(), itemList)

                setOnTouchListener { _, _ ->
                    swipeHelperCallback.removePreviousClamp(this)
                    false
                }
            }

            homeAddTaskBtn.setOnClickListener {
                Toast.makeText(requireContext(), "+ 버튼 클릭됨", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // dp 값을 px 값으로 변환하는 함수
    fun dpToPx(dp: Float, context: Context): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }


    fun initDummyData() {
        val dummydata = HomeItem(R.color.primary_100, "잠자기", "03:33", 0)
        itemList.add(dummydata)
        itemList.add(dummydata)
        itemList.add(dummydata)
        itemList.add(dummydata)
        itemList.add(dummydata)
        itemList.add(dummydata)
    }
}