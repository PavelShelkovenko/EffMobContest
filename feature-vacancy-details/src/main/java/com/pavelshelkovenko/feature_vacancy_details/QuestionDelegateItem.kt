package com.pavelshelkovenko.feature_vacancy_details

import com.pavelshelkovenko.ui.delegate_adapter.DelegateItem

class QuestionDelegateItem(
    val id: String,
    val value: String
): DelegateItem {
    override fun id(): Any = id

    override fun content(): Any = value

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as QuestionDelegateItem).value == this.value
    }
}