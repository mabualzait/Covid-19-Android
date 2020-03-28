package ws.idroid.worldstatus.di

import ws.idroid.worldstatus.data.repository.AppRepository
import ws.idroid.worldstatus.data.repository.Repository
import org.koin.dsl.module

/**
 *  malik abualzait 2020-03-24.
 */
 val repositoryModule = module {
    factory<Repository> {
        AppRepository(get(), get())
    }
}