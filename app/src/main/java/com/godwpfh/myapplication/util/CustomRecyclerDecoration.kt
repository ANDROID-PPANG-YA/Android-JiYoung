package com.godwpfh.myapplication.util

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerDecoration(
    private val height: Float,
    private val padding: Float,
    private val color:Int
): RecyclerView.ItemDecoration() {
    private val paint= Paint()

    init {
        paint.color=color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val left=parent.paddingStart+padding
        val right=parent.width-parent.paddingEnd-padding

        for(i in 0 until parent.childCount){
            val child=parent.getChildAt(i)
            val params=child.layoutParams as RecyclerView.LayoutParams

            val top=(child.bottom+params.bottomMargin).toFloat()
            val bottom= top+height

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}