package com.example.nds.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nds.databinding.ErrandResBinding
import com.example.nds.model.ErrandRes
import kotlin.coroutines.coroutineContext

class ErrandResAdapter: ListAdapter<ErrandRes, ErrandResAdapter.ErrandResViewHolder>(diffUtil) {
    inner class ErrandResViewHolder(private val binding: ErrandResBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(errandModel: ErrandRes) {
            binding.errandStatusTextView.text = errandModel.errandStatus
            binding.errandRequestDateTextView.text = errandModel.errandRequestDate
            binding.errandContentTextView.text = errandModel.errandItem
            binding.errandTotalPriceTextView.text = errandModel.errandTotalPrice
            binding.memNicknameTextView.text = errandModel.memNickname

            if(errandModel.errandStatus == "P") {
                binding.errandStatusTextView.text = "진행중"
                binding.errandStatusTextView.setTextColor(Color.parseColor("#000000"))
                binding.errandStatusTextView.setBackgroundColor(Color.parseColor("#FFC107"))
            } else if (errandModel.errandStatus == "S") {
                binding.errandStatusTextView.text = "완료"
                binding.errandStatusTextView.setTextColor(Color.parseColor("#FFFFFF"))
                binding.errandStatusTextView.setBackgroundColor(Color.parseColor("#627EA4"))
            }
        }
    }

    // 미리 만들어진 뷰홀더 없을때 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrandResViewHolder {
        return ErrandResViewHolder(ErrandResBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 뷰홀더가 바인딩 되었을 때 실제 데이터 그려주기
    override fun onBindViewHolder(holder: ErrandResViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ErrandRes>(){
            // 아이템이 같은지 체크
            override fun areItemsTheSame(oldItem: ErrandRes, newItem: ErrandRes): Boolean {
                return oldItem == newItem
            }

            // 내용이 같은지 체크
            override fun areContentsTheSame(oldItem: ErrandRes, newItem: ErrandRes): Boolean {
                return oldItem.memEmail == newItem.memEmail
            }
        }
    }
}