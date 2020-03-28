package ws.idroid.worldstatus.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 *  malik abualzait 04/03/20.
 */
data class CovidOverviewItem(
    @Expose @SerializedName("detail") val detail: String? = null,
    @Expose @SerializedName("value") val value: Int = 0
)