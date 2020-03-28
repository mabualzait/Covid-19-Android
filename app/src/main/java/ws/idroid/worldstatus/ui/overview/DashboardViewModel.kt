package ws.idroid.worldstatus.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.functions.Function3
import ws.idroid.worldstatus.R
import ws.idroid.worldstatus.data.model.CovidDaily
import ws.idroid.worldstatus.data.model.CovidDetail
import ws.idroid.worldstatus.data.model.CovidOverview
import ws.idroid.worldstatus.data.repository.Repository
import ws.idroid.worldstatus.data.repository.Result
import ws.idroid.worldstatus.ui.adapter.viewholders.ErrorStateItem
import ws.idroid.worldstatus.ui.adapter.viewholders.LoadingStateItem
import ws.idroid.worldstatus.ui.adapter.viewholders.TextItem
import ws.idroid.worldstatus.ui.base.BaseViewItem
import ws.idroid.worldstatus.ui.base.BaseViewModel
import ws.idroid.worldstatus.util.Constant
import ws.idroid.worldstatus.util.SingleLiveEvent
import ws.idroid.worldstatus.util.ext.addTo
import ws.idroid.worldstatus.util.rx.SchedulerProvider

/**
 *  malik abualzait
 */
class DashboardViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _liveItems = MutableLiveData<List<BaseViewItem>>()
    val items: LiveData<List<BaseViewItem>>
        get() = _liveItems

    private fun showLoadingState(){
        _loading.value = true
        if(_liveItems.value?.isEmpty() == null ||
                _liveItems.value?.firstOrNull { it is ErrorStateItem } != null){
            _liveItems.value = listOf(LoadingStateItem())
        }
    }

    private fun showErrorState(throwable: Throwable){
        _loading.value = false
        if(_liveItems.value?.isEmpty() == null ||
            _liveItems.value?.firstOrNull { it is ErrorStateItem || it is LoadingStateItem } != null){
            _liveItems.value = listOf(handleThrowable(throwable))
        }
    }

    fun loadDashboard() {
        showLoadingState()

        val overviewObservable = appRepository.overview()
            .observeOn(schedulerProvider.io()) //all stream below will be manage on io thread

        val dailyObservable = appRepository.daily()
            .observeOn(schedulerProvider.io())

        val pinnedObservable = appRepository.pinnedRegion()
            .observeOn(schedulerProvider.io())

        Observable.combineLatest(
                overviewObservable,
                dailyObservable,
                pinnedObservable,
                Function3<Result< CovidOverview>,
                        Result<List< CovidDaily>>,
                        Result< CovidDetail>,
                        Pair<List<BaseViewItem>, Throwable?>> { overview, daily, pinned ->

                    val items: MutableList<BaseViewItem> = mutableListOf()
                    var currentThrowable: Throwable? = null

                    with(overview){
                        items.add( ws.idroid.worldstatus.data.mapper.CovidOverviewDataMapper.transform(data))
                        error?.let { currentThrowable = it }
                    }

                    with(pinned){
                         ws.idroid.worldstatus.data.mapper.CovidPinnedDataMapper.transform(data)?.let {
                            items.add(it)
                        }
                        error?.let { currentThrowable = it }
                    }

                    with(daily){
                        val dailies =  ws.idroid.worldstatus.data.mapper.CovidDailyDataMapper.transform(data)
                        if(dailies.isNotEmpty()) {
                                items.add(TextItem(R.string.daily_updates, R.string.show_graph))
                                items.addAll(dailies)
                        }
                        error?.let { currentThrowable = it }
                    }
                    return@Function3 items.toList() to currentThrowable
                })
        .observeOn(schedulerProvider.ui()) //go back to ui thread
        .subscribe({ (result, throwable) ->
            _liveItems.postValue(result)
            if(throwable != null) _toastMessage.value = Constant.ERROR_MESSAGE
            _loading.value = false
        }, {
            _toastMessage.value = Constant.ERROR_MESSAGE
            showErrorState(it)
        }).addTo(compositeDisposable)
    }
}