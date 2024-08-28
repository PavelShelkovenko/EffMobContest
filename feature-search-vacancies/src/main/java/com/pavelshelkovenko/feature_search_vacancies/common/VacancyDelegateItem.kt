package com.pavelshelkovenko.feature_search_vacancies.common

import com.pavelshelkovenko.domain.models.Vacancy
import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

class VacancyDelegateItem(
    val id: String,
    val value: Vacancy
): DelegateItem {
    override fun id(): Any = id

    override fun content(): Any = value

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as VacancyDelegateItem).value == this.value
    }
}
