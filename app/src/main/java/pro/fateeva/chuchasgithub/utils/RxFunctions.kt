package pro.fateeva.chuchasgithub.utils

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T : Any, R> toSingle(call: Call<T>, mapper: (T?) -> R) : Single<R> = Single.create { emitter ->
    call.enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            try {
                emitter.onSuccess(mapper.invoke(response.body()))
            } catch (throwable: Throwable){
                emitter.tryOnError(throwable)
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            emitter.tryOnError(t)
        }
    })
}