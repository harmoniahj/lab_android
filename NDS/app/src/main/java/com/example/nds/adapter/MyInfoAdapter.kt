package com.example.nds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nds.databinding.ActivityMyInfoBinding
import com.example.nds.model.MyInfo

class MyInfoAdapter: ListAdapter<MyInfo, MyInfoAdapter.MyInfoViewHolder>(diffUtil) {
    inner class MyInfoViewHolder(private val binding: ActivityMyInfoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(myInfoModel: MyInfo) {
        //    binding.memStarRatingBar.rating = myInfoModel.memStar
            binding.memEmailTextView.text = myInfoModel.memEmail
            binding.memNicknameTextView.text = myInfoModel.memNickname

            // 이미지 가져오기
            Glide
                .with(binding.memImgImageView.context)
                .load("http://192.168.0.24:9696/myPage/assets/img/profile/" + myInfoModel.memImg)
                .into(binding.memImgImageView)
        }
    }

    // 미리 만들어진 뷰홀더 없을때 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInfoViewHolder {
        return MyInfoViewHolder(ActivityMyInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 뷰홀더가 바인딩 되었을 때 실제 데이터 그려주기
    override fun onBindViewHolder(holder: MyInfoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<MyInfo>(){
            // 아이템이 같은지 체크
            override fun areItemsTheSame(oldItem: MyInfo, newItem: MyInfo): Boolean {
                return oldItem == newItem
            }

            // 내용이 같은지 체크
            override fun areContentsTheSame(oldItem: MyInfo, newItem: MyInfo): Boolean {
                return oldItem.memEmail == newItem.memEmail
            }
        }
    }
}