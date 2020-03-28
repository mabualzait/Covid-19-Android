package ws.idroid.worldstatus.data.source.pref

import com.orhanobut.hawk.Hawk
import ws.idroid.worldstatus.data.model.CovidDaily
import ws.idroid.worldstatus.data.model.CovidDetail
import ws.idroid.worldstatus.data.model.CovidOverview
import ws.idroid.worldstatus.util.CacheKey

/**
 *  malik abualzait@live.com 2019-06-14.
 */

class AppPrefSource {
    fun getOverview():  CovidOverview? = Hawk.get(CacheKey.OVERVIEW, null)

    fun setOverview(covidOverview:  CovidOverview) = Hawk.put(CacheKey.OVERVIEW, covidOverview)

    fun getDaily(): List< CovidDaily> = Hawk.get(CacheKey.DAYS, emptyList())

    fun setDaily(covid: List< CovidDaily>) = Hawk.put(CacheKey.DAYS, covid)

    fun getConfirmed(): List< CovidDetail>? = Hawk.get(CacheKey.CONFIRMED, null)

    fun setConfirmed(covid: List< CovidDetail>) = Hawk.put(CacheKey.CONFIRMED, covid)

    fun getDeath(): List< CovidDetail>? = Hawk.get(CacheKey.DEATH, null)

    fun setDeath(covid: List< CovidDetail>) = Hawk.put(CacheKey.DEATH, covid)

    fun getRecovered(): List< CovidDetail>? = Hawk.get(CacheKey.RECOVERED, null)

    fun setRecovered(covid: List< CovidDetail>) = Hawk.put(CacheKey.RECOVERED, covid)

    fun getCountry():  CovidOverview? = Hawk.get(CacheKey.COUNTRY, null)

    fun setCountry(covidOverview:  CovidOverview) = Hawk.put(CacheKey.COUNTRY, covidOverview)

    fun getFullStats(): List< CovidDetail>? = Hawk.get(CacheKey.FULL_STATS, null)

    fun setFullStats(covid: List< CovidDetail>) = Hawk.put(CacheKey.FULL_STATS, covid)

    fun getPrefCountry():  CovidDetail? = Hawk.get(CacheKey.PREF_COUNTRY, null)

    fun setPrefCountry(covid:  CovidDetail?) = Hawk.put(CacheKey.PREF_COUNTRY, covid)
}