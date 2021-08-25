package com.nimadugarov.testappsequenia.presentation.home.list

interface ViewHoldersManager {
    fun registerViewHolder(itemType: Int, viewHolder: ViewHolderVisitor)
    fun getItemType(item: Any): Int
    fun getViewHolder(itemType: Int): ViewHolderVisitor
}