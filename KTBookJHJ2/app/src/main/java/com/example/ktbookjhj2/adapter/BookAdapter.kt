package com.example.ktbookjhj2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktbookjhj2.databinding.ItemBookBinding
import com.example.ktbookjhj2.model.Book

class BookAdapter: ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffUtil) {
    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(bookModel: Book){
            binding.titleTextView.text = bookModel.title
            binding.descriptionTextView.text = bookModel.description
            //서버에서 이미지 정보를 가져와서 이미지 로딩 처리하기
            Glide
                .with(binding.coverImageView.context)
                .load(bookModel.coveerSmallUrl)
                .into(binding.coverImageView)

        }
    } // end of inner

    // 미리 만들어진 뷰홀더가 없을 때 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BookItemViewHolder{
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 뷰홀더가 바인딩 되었을 때 실제 데이터를 그려주기
    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int){
        holder.bind(currentList[position])
    }

    // RecyclerView의 View 포지션의 값이 변경되었을 때 똑같은 값일 때 또 가져올 필요가 없으므로 효율적 처리를 위해...
    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Book>(){
            // 아이템이 같은지 체크
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            // 내용이 같은지 체크
            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}