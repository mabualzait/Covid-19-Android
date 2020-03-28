package ws.idroid.worldstatus.ui.dailygraph

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ws.idroid.worldstatus.data.repository.Repository
import ws.idroid.worldstatus.ui.adapter.viewholders.DailyItem
import ws.idroid.worldstatus.ui.base.BaseViewModel
import ws.idroid.worldstatus.util.SingleLiveEvent
import ws.idroid.worldstatus.util.ext.addTo
import ws.idroid.worldstatus.util.rx.SchedulerProvider

/**
 *  malik abualzait 16/03/20.
 */
class DailyGraphViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {
    private val _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _loading = SingleLiveEvent<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _dailyItems = MutableLiveData<List<DailyItem>>()
    val dailyItems: LiveData<List<DailyItem>>
        get() = _dailyItems

    fun loadCacheDailyData() {
        /*Assume daily data just got fresh data from remote api on previous page
          for UX Purpose, we directly load cache
        */
        _dailyItems.postValue( ws.idroid.worldstatus.data.mapper.CovidDailyDataMapper.transform(appRepository.getCacheDaily().orEmpty()))
    }

    fun loadRemoteDailyData() {
        appRepository.daily().subscribe({ response ->
            _loading.postValue(false)
            response.data?.let {
                _dailyItems.postValue( ws.idroid.worldstatus.data.mapper.CovidDailyDataMapper.transform(it))
            }
        }, {
            _loading.postValue(false)

        }).addTo(compositeDisposable)
    }
}