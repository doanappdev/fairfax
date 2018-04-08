package com.doanappdev.fairfax.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun List<Date>.quickSort(items: List<Date>) : List<Date> {

    val temp = mutableListOf<Date>()

    temp.add(items[0])

    return emptyList()
}

