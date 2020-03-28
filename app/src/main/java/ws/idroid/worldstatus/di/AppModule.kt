package ws.idroid.worldstatus.di

import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.dsl.module
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import ws.idroid.worldstatus.R
import ws.idroid.worldstatus.util.rx.AppSchedulerProvider
import ws.idroid.worldstatus.util.rx.SchedulerProvider

/**
 *  malik abualzait 2020-03-24.
 */
const val DEFAULT_FONT = "fonts/GoogleSans-Regular.ttf"

val appModule = module {

    single {
        CalligraphyConfig.Builder()
            .setDefaultFontPath(DEFAULT_FONT)
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

    factory<SchedulerProvider> {
        AppSchedulerProvider()
    }


    factory {
        LinearLayoutManager(get())
    }

}