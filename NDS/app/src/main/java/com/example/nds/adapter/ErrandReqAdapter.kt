package com.example.nds.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nds.databinding.ErrandReqBinding
import com.example.nds.model.ErrandReq

class ErrandReqAdapter: ListAdapter<ErrandReq, ErrandReqAdapter.ErrandReqViewHolder>(diffUtil) {
    inner class ErrandReqViewHolder(private val binding: ErrandReqBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(errandModel: ErrandReq) {
            binding.errandStatusTextView.text = errandModel.errandStatus
            binding.errandRequestDateTextView.text = errandModel.errandRequestDate
            binding.errandContentTextView.text = errandModel.errandItem
            binding.errandTotalPriceTextView.text = errandModel.errandTotalPrice

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrandReqViewHolder {
        return ErrandReqViewHolder(ErrandReqBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 뷰홀더가 바인딩 되었을 때 실제 데이터 그려주기
    override fun onBindViewHolder(holder: ErrandReqViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ErrandReq>(){
            // 아이템이 같은지 체크
            override fun areItemsTheSame(oldItem: ErrandReq, newItem: ErrandReq): Boolean {
                return oldItem == newItem
            }

            // 내용이 같은지 체크
            override fun areContentsTheSame(oldItem: ErrandReq, newItem: ErrandReq): Boolean {
                return oldItem.memEmail == newItem.memEmail
            }
        }
    }
}