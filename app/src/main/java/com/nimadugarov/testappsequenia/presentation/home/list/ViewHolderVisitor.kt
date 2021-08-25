package com.nimadugarov.testappsequenia.presentation.home.list

import android.view.View

interface ViewHolderVisitor {
    val layout: Int
    fun acceptBinding(item: Any): Boolean
    fun bind(itemView: View, item: Any, clickListener: AdapterClickListener, position: Int)
}