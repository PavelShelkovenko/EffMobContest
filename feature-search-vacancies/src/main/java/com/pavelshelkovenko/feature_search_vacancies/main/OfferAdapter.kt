package com.pavelshelkovenko.feature_search_vacancies.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pavelshelkovenko.domain.models.Offer
import com.pavelshelkovenko.feature_search_vacancies.databinding.OfferCardBinding
import com.pavelshelkovenko.ui.delegate_adapter.DelegateAdapter
import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible

typealias Link = String

class OfferAdapter :
    DelegateAdapter<OfferDelegateItem, OfferAdapter.ViewHolder>(OfferDelegateItem::class.java) {

    private val iconResourcesIds = mapOf(
        "near_vacancies" to com.pavelshelkovenko.ui.R.drawable.ic_near_vacancies,
        "level_up_resume" to com.pavelshelkovenko.ui.R.drawable.ic_level_up_resume,
        "temporary_job" to com.pavelshelkovenko.ui.R.drawable.ic_temporary_job
    )

    var onOfferClickListener: ((Link) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            OfferCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        model: OfferDelegateItem,
        viewHolder: ViewHolder,
        payloads: List<DelegateItem.Payloadable>
    ) {
        viewHolder.bind(model.value)
    }

    inner class ViewHolder(private val binding: OfferCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Offer) {
            with(binding) {
                offerCardContent.setOnClickListener {
                    onOfferClickListener?.invoke(model.link)
                }
                when (model.id) {
                    in iconResourcesIds -> offerIcon.setImageResource(iconResourcesIds[model.id]!!)
                    else -> offerIcon.gone()
                }
                if (model.buttonTitle.isEmpty()) {
                    offerButton.gone()
                    recommendationTitle.text = model.title.trim()
                    recommendationTitle.maxLines = RECOMMENDATION_TITLE_MAX_LINES
                } else {
                    offerButton.visible()
                    offerButton.text = model.buttonTitle
                    recommendationTitle.text = model.title
                    recommendationTitle.maxLines = RECOMMENDATION_TITLE_DEFAULT_LINES
                }
            }
        }
    }

    companion object {
        private const val RECOMMENDATION_TITLE_MAX_LINES = 3
        private const val RECOMMENDATION_TITLE_DEFAULT_LINES = 2
    }
}