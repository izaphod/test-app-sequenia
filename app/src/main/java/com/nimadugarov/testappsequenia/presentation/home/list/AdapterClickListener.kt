package com.nimadugarov.testappsequenia.presentation.home.list

import android.view.View
import com.nimadugarov.testappsequenia.presentation.model.ItemViewModel

class AdapterClickListener(private val clickListener: (item: ItemViewModel, position: Int) -> Unit) {
    fun onClick(item: ItemViewModel, position: Int) = View.OnClickListener {
        clickListener(item, position)
    }
}