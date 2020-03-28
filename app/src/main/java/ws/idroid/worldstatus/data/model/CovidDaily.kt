package ws.idroid.worldstatus.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ws.idroid.worldstatus.util.IncrementStatus


/**
 *  malik abualzait 04/03/20.
 */
data class CovidDaily(
    @Expose @SerializedName("deltaConfirmed") val deltaConfirmed: Int = 0,
    @Expose @SerializedName("deltaRecovered") val deltaRecovered: Int = 0,
    @Expose @SerializedName("mainlandChina") val mainlandChina: Int = 0,
    @Expose @SerializedName("objectid") val objectid: Int = 0,
    @Expose @SerializedName("otherLocations") val otherLocations: Int = 0,
    @Expose @SerializedName("reportDate") val reportDate: Long = 0,
    @Expose @SerializedName("reportDateString") val reportDateString: String? = null,
    @Expose @SerializedName("totalConfirmed") val totalConfirmed: Int = 0,
    @Expose @SerializedName("totalRecovered") val totalRecovered: Int = 0,
    @Expose @SerializedName("incrementRecovered") var incrementRecovered: Int = IncrementStatus.FLAT,
    @Expose @SerializedName("incrementConfirmed") var incrementConfirmed: Int = IncrementStatus.FLAT
)