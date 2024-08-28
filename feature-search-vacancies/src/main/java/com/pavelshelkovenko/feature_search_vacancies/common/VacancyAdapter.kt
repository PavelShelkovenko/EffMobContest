package com.pavelshelkovenko.feature_search_vacancies.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.feature_search_vacancies.databinding.VacancyCardBinding
import com.pavelshelkovenko.ui.delegate_adapter.DelegateAdapter
import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem
import com.pavelshelkovenko.ui.extensions.gone
import com.pavelshelkovenko.ui.extensions.visible
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

typealias VacancyId = String

class VacancyAdapter :
    DelegateAdapter<VacancyDelegateItem, VacancyAdapter.ViewHolder>(VacancyDelegateItem::class.java) {

    var onFavoriteIconClickListener: ((VacancyId) -> Unit)? = null
    var onVacancyClickListener: ((VacancyId) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(
        VacancyCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        model: VacancyDelegateItem,
        viewHolder: ViewHolder,
        payloads: List<DelegateItem.Payloadable>
    ) {
        viewHolder.bind(model.value)
    }

    inner class ViewHolder(private val binding: VacancyCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Vacancy) {
            with(binding) {
                vacancyCardContent.setOnClickListener {
                    onVacancyClickListener?.invoke(model.id)
                }
                if (model.isFavorite) {
                    selectedFavoriteIcon.visible()
                    selectedFavoriteIcon.setOnClickListener {
                        onFavoriteIconClickListener?.invoke(model.id)
                    }
                    unselectedFavoriteIcon.gone()
                } else {
                    selectedFavoriteIcon.gone()
                    unselectedFavoriteIcon.visible()
                    unselectedFavoriteIcon.setOnClickListener {
                        onFavoriteIconClickListener?.invoke(model.id)
                    }
                }
                vacancyTitle.text = model.title
                companyName.text = model.company
                addressTown.text = model.address.town
                experiencePreviewText.text = model.experience.previewText
                publishedDate.text = root.resources.getString(
                    com.pavelshelkovenko.ui.R.string.published_info,
                    formatDate(model.publishedDate)
                )
                if (model.lookingNumber == 0) {
                    lookingForVacancyCountInfo.gone()
                } else {
                    lookingForVacancyCountInfo.text = root.resources.getQuantityString(
                        com.pavelshelkovenko.ui.R.plurals.looking_number,
                        model.lookingNumber,
                        model.lookingNumber,
                    )
                }
                if (model.salary.short.isEmpty()) {
                    salary.gone()
                } else {
                    salary.text = model.salary.short
                }
            }
        }
    }

    private fun formatDate(inputDate: String): String {
        val date = LocalDate.parse(inputDate)
        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        return date.format(formatter)
    }
}