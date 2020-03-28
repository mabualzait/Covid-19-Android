package ws.idroid.worldstatus.ui.adapter.viewholders

import android.view.View
import ws.idroid.worldstatus.R
import ws.idroid.worldstatus.databinding.ItemPinnedBinding
import ws.idroid.worldstatus.ui.adapter.BaseViewHolder
import ws.idroid.worldstatus.ui.base.BaseViewItem
import ws.idroid.worldstatus.util.NumberUtils

data class PinnedItem(
    val confirmed: Int? = null,
    val recovered: Int? = null,
    val deaths: Int? = null,
    val locationName: String,
    val lastUpdate: Long
): BaseViewItem {
    override fun layoutResId(): Int = R.layout.item_pinned
}

class PinnedItemViewHolder(itemView: View) : BaseViewHolder<PinnedItem>(itemView) {
    private val binding: ItemPinnedBinding = ItemPinnedBinding.bind(itemView)

    override fun setOnClickListener(listener: (View) -> Unit) {
        //Listener
    }

    override fun bind(item: PinnedItem) {
        with(binding) {
            val lastUpdate = NumberUtils.formatTime(item.lastUpdate)
            txtLocation.text = item.locationName
            txtUpdate.text = itemView.context.getString(R.string.information_last_update, lastUpdate)
            txtData.text = "${item.confirmed ?: '-'}"
            txtRcv.text = "${item.recovered ?: '-'}"
            txtDeath.text = "${item.deaths ?: '-'}"
        }
    }

    companion object {
        const val LAYOUT = R.layout.item_pinned
    }
}