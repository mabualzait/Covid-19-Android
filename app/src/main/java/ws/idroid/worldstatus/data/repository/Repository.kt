package ws.idroid.worldstatus.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ws.idroid.worldstatus.data.model.CovidDaily
import ws.idroid.worldstatus.data.model.CovidDetail
import ws.idroid.worldstatus.data.model.CovidOverview

interface Repository {
    fun overview(): Observable<Result<CovidOverview>>
    fun daily(): Observable<Result<List<CovidDaily>>>
    fun confirmed(): Observable<List<CovidDetail>>
    fun deaths(): Observable<List<CovidDetail>>
    fun recovered(): Observable<List<CovidDetail>>
    fun country(id: String): Observable<CovidOverview>
    fun fullStats(): Observable<List<CovidDetail>>
    fun pinnedRegion(): Observable<Result<CovidDetail>>
    fun putPinnedRegion(data: CovidDetail): Completable
    fun removePinnedRegion(): Completable
    fun getCachePinnedRegion(): CovidDetail?
    fun getCacheOverview(): CovidOverview?
    fun getCacheDaily(): List<CovidDaily>?
    fun getCacheConfirmed(): List<CovidDetail>?
    fun getCacheDeath(): List<CovidDetail>?
    fun getCacheRecovered(): List<CovidDetail>?
    fun getCacheFull(): List<CovidDetail>?
    fun getCacheCountry(id: String): CovidOverview?
}