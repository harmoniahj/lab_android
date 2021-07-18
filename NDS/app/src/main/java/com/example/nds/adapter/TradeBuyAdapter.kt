package com.example.nds.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nds.databinding.CoinBinding
import com.example.nds.databinding.TradeBuyBinding
import com.example.nds.model.CoinTrans
import com.example.nds.model.TradeBuy

class TradeBuyAdapter: ListAdapter<TradeBuy, TradeBuyAdapter.TradeBuyViewHolder>(diffUtil) {
    inner class TradeBuyViewHolder(private val binding: TradeBuyBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tradeBuyModel: TradeBuy) {
            binding.bmTitleTextView.text = tradeBuyModel.bmTitle
            binding.bmDateTextView.text = tradeBuyModel.bmDate
            binding.bmPriceTextView.text = tradeBuyModel.bmPrice

            Glide
                .with(binding.biFileImageView.context)
                .load("http://192.168.0.24:9696/itemUpload/assets/img/itemupload/" + tradeBuyModel.biFile)
                .into(binding.biFileImageView)
        }
    }

    // 미리 만들어진 뷰홀더 없을때 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeBuyViewHolder {
        return TradeBuyViewHolder(TradeBuyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 뷰홀더가 바인딩 되었을 때 실제 데이터 그려주기
    override fun onBindViewHolder(holder: TradeBuyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<TradeBuy>(){
            // 아이템이 같은지 체크
            override fun areItemsTheSame(oldItem: TradeBuy, newItem: TradeBuy): Boolean {
                return oldItem == newItem
            }

            // 내용이 같은지 체크
            override fun areContentsTheSame(oldItem: TradeBuy, newItem: TradeBuy): Boolean {
                return oldItem.bmNo == newItem.bmNo
            }
        }
    }
}