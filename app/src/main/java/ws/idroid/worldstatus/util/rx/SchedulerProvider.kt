package ws.idroid.worldstatus.util.rx

import io.reactivex.Scheduler

/**
 *  malik abualzait 25/02/20.
 */
interface SchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}