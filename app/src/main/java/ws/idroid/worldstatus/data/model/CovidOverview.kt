package ws.idroid.worldstatus.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *  malik abualzait 04/03/20.
 */
data class CovidOverview(
    @Expose @SerializedName("confirmed") val confirmed:  CovidOverviewItem? = null,
    @Expose @SerializedName("recovered") val recovered:  CovidOverviewItem? = null,
    @Expose @SerializedName("deaths") val deaths:  CovidOverviewItem? = null
)