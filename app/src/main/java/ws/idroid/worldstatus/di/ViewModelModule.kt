package ws.idroid.worldstatus.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ws.idroid.worldstatus.ui.dailygraph.DailyGraphViewModel
import ws.idroid.worldstatus.ui.detail.DetailViewModel
import ws.idroid.worldstatus.ui.overview.DashboardViewModel

/**
 *  malik abualzait 2020-03-24.
 */

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { DailyGraphViewModel(get(), get()) }
}