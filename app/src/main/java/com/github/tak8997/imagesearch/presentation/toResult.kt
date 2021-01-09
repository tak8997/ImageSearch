package com.github.tak8997.imagesearch.presentation

import androidx.annotation.CheckResult
import com.github.tak8997.imagesearch.util.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single

@CheckResult
fun <T> Single<T>.toResult(schedulerProvider: SchedulerProvider):
        Observable<Result<T>> {
    return toObservable().toResult(schedulerProvider)
}

@CheckResult fun <T> Observable<T>.toResult(schedulerProvider: SchedulerProvider):
        Observable<Result<T>> {
    return compose { item ->
        item
            .map { Result.success(it) }
            .onErrorReturn { e -> Result.failure(e.message ?: "unknown", e) }
            .observeOn(schedulerProvider.ui())
            .startWith(Result.inProgress())
    }
}