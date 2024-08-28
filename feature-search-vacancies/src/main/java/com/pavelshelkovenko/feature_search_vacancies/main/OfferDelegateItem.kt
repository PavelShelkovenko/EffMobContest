package com.pavelshelkovenko.feature_search_vacancies.main

import com.pavelshelkovenko.domain.models.Offer
import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

class OfferDelegateItem(
    val id: String,
    val value: Offer
): DelegateItem {
    override fun id(): Any = id

    override fun content(): Any = value

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as OfferDelegateItem).value == this.value
    }
}
