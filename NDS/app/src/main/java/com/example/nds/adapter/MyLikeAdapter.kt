package com.example.nds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nds.databinding.MyLikeBinding
import com.example.nds.model.Mylike

class MyLikeAdapter: ListAdapter<Mylike, MyLikeAdapter.MyLikeViewHolder>(diffUtil) {
    inner class MyLikeViewHolder(private val binding: MyLikeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(myLikeModel: Mylike) {
            binding.bmTitleTextView.text = myLikeModel.bmTitle
            binding.bmDateTextView.text = myLikeModel.bmDate
            binding.bmPriceTextView.text = myLikeModel.bmPrice

            // 이미지 가져오기
            Glide
                .with(binding.biFileimageView.context)
                .load("http://192.168.0.24:9696/itemUpload/assets/img/itemupload/" + myLikeModel.biFile)
                .into(binding.biFileimageView)
        }
    }

    // 미리 만들어진 뷰홀더 없을때 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLikeViewHolder {
        return MyLikeViewHolder(MyLikeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 뷰홀더가 바인딩 되었을 때 실제 데이터 그려주기
    override fun onBindViewHolder(holder: MyLikeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Mylike>(){
            // 아이템이 같은지 체크
            override fun areItemsTheSame(oldItem: Mylike, newItem: Mylike): Boolean {
                return oldItem == newItem
            }

            // 내용이 같은지 체크
            override fun areContentsTheSame(oldItem: Mylike, newItem: Mylike): Boolean {
                return oldItem.bmNo == newItem.bmNo
            }
        }
    }
}