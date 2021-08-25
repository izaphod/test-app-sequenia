package com.nimadugarov.testappsequenia.presentation.home.list

class ViewHoldersManagerImpl : ViewHoldersManager {

    private val holdersMap = mutableMapOf<Int, ViewHolderVisitor>()

    override fun registerViewHolder(itemType: Int, viewHolder: ViewHolderVisitor) {
        holdersMap += itemType to viewHolder
    }

    override fun getItemType(item: Any): Int {
        holdersMap.forEach { (itemType, holder) ->
            if (holder.acceptBinding(item)) return itemType
        }
        return ItemTypes.TYPE_UNKNOWN
    }

    override fun getViewHolder(itemType: Int) =
        holdersMap[itemType] ?: throw TypeCastException("Unknown RecyclerView item type")
}