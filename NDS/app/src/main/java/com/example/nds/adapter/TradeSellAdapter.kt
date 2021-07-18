package com.example.nds.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nds.databinding.TradeSellBinding
import com.example.nds.model.TradeSell

class TradeSellAdapter: ListAdapter<TradeSell, TradeSellAdapter.TradeSellViewHolder>(diffUtil) {
    inner class TradeSellViewHolder(private val binding: TradeSellBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tradeSellModel: TradeSell) {
            binding.bmTitleTextView.text = tradeSellModel.bmTitle
            binding.bmDateTextView.text = tradeSellModel.bmDate
            binding.bmPriceTextView.text = tradeSellModel.bmPrice
            binding.bmStatusTextView.text = tradeSellModel.bmStatus

            Glide
                .with(binding.biFileImageView.context)
                .load("http://192.168.0.24:9696/itemUpload/assets/img/itemupload/" + tradeSellModel.biFile)
                .into(binding.biFileImageView)

            if(tradeSellModel.bmStatus == "N") {
                binding.bmStatusTextView.text = "판매중"
                binding.bmStatusTextView.setBackgroundColor(Color.parseColor("#FFC107"))
            } else if (tradeSellModel.bmStatus == "S") {
                binding.bmStatusTextView.text = "거래중"
                binding.bmStatusTextView.setBackgroundColor(Color.parseColor("#007BFD"))
            } else if (tradeSellModel.bmStatus == "C") {
                binding.bmStatusTextView.text = "판매완료"
                binding.bmStatusTextView.setBackgroundColor(Color.parseColor("#EDA0C7"))
            }
        }
    }

    // 미리 만들어진 뷰홀더 없을때 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeSellViewHolder {
        return TradeSellViewHolder(TradeSellBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 뷰홀더가 바인딩 되었을 때 실제 데이터 그려주기
    override fun onBindViewHolder(holder: TradeSellViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<TradeSell>(){
            // 아이템이 같은지 체크
            override fun areItemsTheSame(oldItem: TradeSell, newItem: TradeSell): Boolean {
                return oldItem == newItem
            }

            // 내용이 같은지 체크
            override fun areContentsTheSame(oldItem: TradeSell, newItem: TradeSell): Boolean {
                return oldItem.bmNo == newItem.bmNo
            }
        }
    }
}