package ws.idroid.worldstatus.ui.adapter.viewholders

import android.view.View
import ws.idroid.worldstatus.R
import ws.idroid.worldstatus.databinding.ItemLocationBinding
import ws.idroid.worldstatus.ui.adapter.BaseViewHolder
import ws.idroid.worldstatus.ui.base.BaseViewItem
import ws.idroid.worldstatus.util.CaseType
import ws.idroid.worldstatus.util.CaseTypes
import ws.idroid.worldstatus.util.NumberUtils
import ws.idroid.worldstatus.util.ext.visible

data class LocationItem(
    val confirmed: Int = 0,
    val recovered: Int = 0,
    val deaths: Int = 0,
    val locationName: String,
    val lastUpdate: Long,
    val lat: Double,
    val long: Double,
    val countryRegion: String,
    val provinceState: String?,
    @CaseTypes val caseType: Int,
    val isPinned: Boolean = false
): BaseViewItem {
    override fun layoutResId(): Int = R.layout.item_location
    fun compositeKey() = countryRegion + provinceState
}

class LocationItemViewHolder(itemView: View) : BaseViewHolder<LocationItem>(itemView) {
    private val binding: ItemLocationBinding = ItemLocationBinding.bind(itemView)

    override fun setOnClickListener(listener: (View) -> Unit) {
        binding.root.setOnClickListener { listener.invoke(it) }
        binding.relativePinned.setOnClickListener { listener.invoke(it) }
    }

    override fun setOnLongClickListener(listener: (View) -> Unit) {
        binding.root.setOnLongClickListener {
            listener.invoke(it)
            true
        }
    }

    override fun bind(item: LocationItem) {
        with(binding) {

            relativePinned.visibility = if(item.isPinned) View.VISIBLE else View.GONE

            val context = itemView.context
            txtLocation.text = item.locationName
            txtInformation.text = context.getString(
                R.string.information_last_update,
                NumberUtils.formatTime(item.lastUpdate)
            )
            txtData.text = context.getString(
                R.string.confirmed_case_count,
                NumberUtils.numberFormat(item.confirmed)
            )
            txtRcv.text = context.getString(
                R.string.recovered_case_count,
                NumberUtils.numberFormat(item.recovered)
            )
            txtDeath.text = context.getString(
                R.string.death_case_count,
                NumberUtils.numberFormat(item.deaths)
            )

            when (item.caseType) {
                CaseType.CONFIRMED -> txtData.visible()
                CaseType.RECOVERED -> txtRcv.visible()
                CaseType.DEATHS -> txtDeath.visible()
                else -> {
                    txtData.visible()
                    txtRcv.visible()
                    txtDeath.visible()
                }
            }
        }
    }

    companion object {
        const val LAYOUT = R.layout.item_location
    }
}