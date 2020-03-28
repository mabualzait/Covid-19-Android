package ws.idroid.worldstatus.ui.adapter.viewholders

import android.view.View
import ws.idroid.worldstatus.R
import ws.idroid.worldstatus.databinding.ItemTextBinding
import ws.idroid.worldstatus.ui.adapter.BaseViewHolder
import ws.idroid.worldstatus.ui.base.BaseViewItem
import ws.idroid.worldstatus.util.ext.gone
import ws.idroid.worldstatus.util.ext.visible


data class TextItem(
    val textResId: Int? = null,
    val textActionResId: Int? = null
) : BaseViewItem {
    override fun layoutResId(): Int = R.layout.item_text
}

class TextItemViewHolder(itemView: View) : BaseViewHolder<TextItem>(itemView) {
    private val binding: ItemTextBinding = ItemTextBinding.bind(itemView)

    override fun setOnClickListener(listener: (View) -> Unit) {
        binding.textAction.setOnClickListener(listener)
    }

    override fun bind(item: TextItem) {
        with(binding) {
            root.context?.let { context ->
                textTitle.text = item.textResId?.let { context.getString(it) }.orEmpty()
                if (item.textActionResId != null) {
                    with(textAction) {
                        visible()
                        text = context.getString(item.textActionResId)
                    }
                } else {
                    textAction.gone()
                }
            }
        }
    }

    companion object {
        const val LAYOUT = R.layout.item_text
    }
}