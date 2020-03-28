package ws.idroid.worldstatus.ui.adapter.viewholders

import android.view.View
import ws.idroid.worldstatus.R
import ws.idroid.worldstatus.databinding.ItemErrorStateBinding
import ws.idroid.worldstatus.ui.adapter.BaseViewHolder
import ws.idroid.worldstatus.ui.base.BaseViewItem


class ErrorStateItem(
    val titleResId: Int,
    val subtitleResId: Int
): BaseViewItem {
    override fun layoutResId(): Int = R.layout.item_error_state
}

class ErrorStateItemViewHolder(itemView: View) : BaseViewHolder<ErrorStateItem>(itemView) {

    private val binding: ItemErrorStateBinding = ItemErrorStateBinding.bind(itemView)

    override fun setOnClickListener(listener: (View) -> Unit) {
        //Listener
        binding.textRetry.setOnClickListener { listener.invoke(it) }
    }

    override fun bind(item: ErrorStateItem) {
        with(binding){
            textTitle.text = itemView.context.getString(item.titleResId)
            textSubtitle.text = itemView.context.getString(item.subtitleResId)
        }
    }

    companion object {
        const val LAYOUT = R.layout.item_error_state
    }
}