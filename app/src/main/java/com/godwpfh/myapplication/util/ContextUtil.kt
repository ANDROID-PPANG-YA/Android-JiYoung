package com.godwpfh.myapplication.util

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <T> Call<T>.enqueueUtil(
    onSuccess : (T) -> Unit,
    onError : ((stateCode :Int) -> Unit)?=null
) {
    this.enqueue(object: Callback<T>{
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if(response.isSuccessful)
                    onSuccess.invoke(response.body()?:return)
            else
                    onError?.invoke(response.code())
        }

        override fun onFailure(call: Call<T>, t: Throwable) {

        }

    })
}

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
