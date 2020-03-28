package ws.idroid.worldstatus.data.source.remote

import ws.idroid.worldstatus.data.model.CovidDaily
import ws.idroid.worldstatus.data.model.CovidDetail
import ws.idroid.worldstatus.data.model.CovidOverview
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

@JvmSuppressWildcards
interface Api {
    @GET("api")
    fun overview(): Observable< CovidOverview>

    @GET("api/daily")
    fun daily(): Observable<List< CovidDaily>>

    @GET("api/confirmed")
    fun confirmed(): Observable<List< CovidDetail>>

    @GET("api/deaths")
    fun deaths(): Observable<List< CovidDetail>>

    @GET("api/recovered")
    fun recovered(): Observable<List< CovidDetail>>

    @GET("api/countries/{country}")
    fun country(@Path("country") country: String): Observable< CovidOverview>

}