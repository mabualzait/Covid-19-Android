package ws.idroid.worldstatus.ui.adapter

import android.view.View
import ws.idroid.worldstatus.ui.adapter.viewholders.*
import ws.idroid.worldstatus.ui.base.BaseViewItem

abstract class ItemTypeFactory {
    abstract fun onCreateViewHolder(containerView: View, viewType: Int): BaseViewHolder<out BaseViewItem>
}

class ItemTypeFactoryImpl: ItemTypeFactory() {
    override fun onCreateViewHolder(
        containerView: View,
        viewType: Int
    ): BaseViewHolder<out BaseViewItem> {
        return when(viewType) {
            DailyItemViewHolder.LAYOUT -> DailyItemViewHolder(containerView)
            OverviewItemViewHolder.LAYOUT -> OverviewItemViewHolder(containerView)
            TextItemViewHolder.LAYOUT -> TextItemViewHolder(containerView)
            PinnedItemViewHolder.LAYOUT -> PinnedItemViewHolder(containerView)
            LocationItemViewHolder.LAYOUT -> LocationItemViewHolder(containerView)
            LoadingStateItemViewHolder.LAYOUT -> LoadingStateItemViewHolder(containerView)
            ErrorStateItemViewHolder.LAYOUT -> ErrorStateItemViewHolder(containerView)
            else -> onCreateViewHolder(containerView, viewType)
        }
    }
}