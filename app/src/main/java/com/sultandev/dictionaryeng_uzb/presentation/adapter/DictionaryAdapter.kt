package com.sultandev.dictionaryeng_uzb.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.sultandev.dictionaryeng_uzb.R
import com.sultandev.dictionaryeng_uzb.data.entity.WordEntity
import com.sultandev.dictionaryeng_uzb.databinding.ItemWordBinding
import com.sultandev.dictionaryeng_uzb.utils.coloredString
import com.sultandev.dictionaryeng_uzb.utils.geWordData
import com.sultandev.dictionaryeng_uzb.utils.setSelectImage

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.WordViewHolder>() {

    private var cursor: Cursor? = null

    var query: String? = null

    private var onClickListener: ((WordEntity, Int) -> Unit)? = null

    private var btnSelectClickListener: ((WordEntity) -> Unit)? = null

    fun setOnClickListener(block: (WordEntity, Int) -> Unit) {
        onClickListener = block
    }

    fun btnSelectClickListener(block: (WordEntity) -> Unit) {
        btnSelectClickListener = block
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitCursor(cursor: Cursor) {
        this.cursor = cursor
        notifyDataSetChanged()
    }

    inner class WordViewHolder(private val binding: ItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            cursor!!.moveToPosition(adapterPosition)
            val data = cursor!!.geWordData()

            binding.tvWord.text = data.english

            binding.tvWord.text = data.english.coloredString(query)

            if (data.is_favourite == 1) setSelectImage(true, binding.btnSelect)
            else setSelectImage(false, binding.btnSelect)

            binding.root.setOnClickListener {
                onClickListener?.invoke(data, absoluteAdapterPosition)
            }

            binding.btnSelect.setOnClickListener {
                startAnimZoomIn(it)
                if (data.is_favourite == 0) {
                    data.is_favourite = 1
                    setSelectImage(true, binding.btnSelect)
                } else {
                    data.is_favourite = 0
                    setSelectImage(false, binding.btnSelect)
                }
                btnSelectClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    fun updateCursor(pos: Int) {
        this.notifyItemChanged(pos)
        this.cursor?.requery()
    }

    private fun startAnimZoomIn(view: View) {
        YoYo.with(Techniques.ZoomIn)
            .delay(0)
            .duration(500)
            .playOn(view)
    }

    private fun setAnimation(viewToAnimate: View, context: Context) {
        val animation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.bounce_anim)
        viewToAnimate.startAnimation(animation)
    }

}