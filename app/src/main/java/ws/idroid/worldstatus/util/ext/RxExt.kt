package ws.idroid.worldstatus.util.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *  malik abualzait 2020-03-24.
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}