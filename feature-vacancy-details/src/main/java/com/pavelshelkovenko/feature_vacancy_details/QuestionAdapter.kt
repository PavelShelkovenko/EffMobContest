package com.pavelshelkovenko.feature_vacancy_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pavelshelkovenko.feature_vacancy_details.databinding.QuestionItemBinding
import com.pavelshelkovenko.ui.delegate_adapter.DelegateAdapter
import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

typealias Question = String

class QuestionAdapter :
    DelegateAdapter<QuestionDelegateItem, QuestionAdapter.ViewHolder>(QuestionDelegateItem::class.java) {

    var onQuestionClickListener: ((Question) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(
        QuestionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        model: QuestionDelegateItem,
        viewHolder: ViewHolder,
        payloads: List<DelegateItem.Payloadable>
    ) {
        viewHolder.bind(model.value)
    }

    inner class ViewHolder(private val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Question) {
            with(binding) {
                question.text = model
                question.setOnClickListener {
                    onQuestionClickListener?.invoke(model)
                }
            }
        }
    }
}