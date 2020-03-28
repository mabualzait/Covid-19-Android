package ws.idroid.worldstatus.data.mapper

import ws.idroid.worldstatus.data.model.CovidDaily
import ws.idroid.worldstatus.data.model.CovidDetail
import ws.idroid.worldstatus.data.model.CovidOverview
import ws.idroid.worldstatus.ui.adapter.viewholders.DailyItem
import ws.idroid.worldstatus.ui.adapter.viewholders.LocationItem
import ws.idroid.worldstatus.ui.adapter.viewholders.OverviewItem
import ws.idroid.worldstatus.ui.adapter.viewholders.PinnedItem
import ws.idroid.worldstatus.util.CaseTypes

object CovidDailyDataMapper {

    fun transform(responses: List<CovidDaily>?) = responses?.map { response ->
        DailyItem(
            response.objectid,
            response.deltaConfirmed,
            response.deltaRecovered,
            response.mainlandChina,
            response.otherLocations,
            response.reportDate,
            response.incrementRecovered,
            response.incrementConfirmed
        )
    }.orEmpty()
}

object CovidOverviewDataMapper {

    fun transform(response: CovidOverview?) = OverviewItem(
        response?.confirmed?.value ?: 0,
        response?.recovered?.value ?: 0,
        response?.deaths?.value ?: 0)
}

object CovidPinnedDataMapper {

    fun transform(response: CovidDetail?) : PinnedItem? = if(response != null) PinnedItem(
        response.confirmed,
        response.recovered,
        response.deaths,
        response.locationName,
        response.lastUpdate)
    else null
}

object CovidDetailDataMapper {

    fun transform(responses: List< CovidDetail>?, @CaseTypes caseType: Int) = responses?.map { response ->
        LocationItem(
            response.confirmed,
            response.recovered,
            response.deaths,
            response.locationName,
            response.lastUpdate,
            response.lat,
            response.long,
            response.countryRegion,
            response.provinceState,
            caseType
        )
    }.orEmpty()
}