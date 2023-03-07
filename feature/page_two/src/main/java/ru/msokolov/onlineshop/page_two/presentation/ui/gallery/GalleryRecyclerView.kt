package ru.msokolov.onlineshop.page_two.presentation.ui.gallery

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min

class GalleryRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var minScale: Float = 1.0f
    var maxScale: Float = 4.0f
    val linearLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)

    var position: Int = 0

    init {

        layoutManager = linearLayoutManager
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(this)
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val first = linearLayoutManager.findFirstVisibleItemPosition()
                val last = linearLayoutManager.findLastVisibleItemPosition()
                val snapView = pagerSnapHelper.findSnapView(linearLayoutManager) ?: return
                val snap = linearLayoutManager.getPosition(snapView)
                position = snap

                if (snap <= 1 || snap >= Int.MAX_VALUE - 1) {
                    scrollToPosition(adapter!!.itemCount / 2)
                }

                snapView.z = Float.MAX_VALUE
                val scaleSpan: Float = maxScale - minScale

                linearLayoutManager.findViewByPosition(first)?.apply {
                    pagerSnapHelper
                        .calculateDistanceToFinalSnap(linearLayoutManager, this)
                        ?.get(0)
                        ?.let { distance ->
                            val ratio: Float = min((width - abs(distance)).toFloat() / 800, 1f)
                            scaleX = ratio * scaleSpan + minScale
                            scaleY = scaleX
                        }
                }
                linearLayoutManager.findViewByPosition(last)?.apply {
                    pagerSnapHelper
                        .calculateDistanceToFinalSnap(linearLayoutManager, this)
                        ?.get(0)
                        ?.let { distance ->
                            val ratio: Float = min((width - abs(distance)).toFloat() / 800, 1f)
                            scaleX = ratio * scaleSpan + minScale
                            scaleY = scaleX
                        }
                }
            }
        })
    }

    fun next() {
        smoothScrollToPosition(position + 1)
    }

}