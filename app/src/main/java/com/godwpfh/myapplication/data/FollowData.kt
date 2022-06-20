package com.godwpfh.myapplication.data

import android.os.Parcelable
import java.io.Serializable


data class FollowData(
    val follow_image: String,
    val follow_name: String
):Serializable
