package ws.idroid.worldstatus.di

import org.koin.dsl.module
import ws.idroid.worldstatus.data.source.pref.AppPrefSource

/**
 *  malik abualzait@live.com 2019-06-14.
 */

val persistenceModule = module {
    single {
        AppPrefSource()
    }
}

