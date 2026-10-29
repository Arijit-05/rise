package com.arijit.rise.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class TimerRingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val density = resources.displayMetrics.density

    private val strokeWidth = 6f * density

    private var progress = 1f

    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF352719.toInt()
        style = Paint.Style.STROKE
        strokeWidth = this@TimerRingView.strokeWidth
    }

    private val ringPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFFFFA500.toInt()
        style = Paint.Style.STROKE
        strokeWidth = this@TimerRingView.strokeWidth
        strokeCap = Paint.Cap.ROUND
    }

    private val bounds = RectF()

    fun setProgress(value: Float) {
        progress = value.coerceIn(0f, 1f)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val diameter = min(width, height).toFloat()
        val radius = diameter / 2f - strokeWidth

        val cx = width / 2f
        val cy = height / 2f

        bounds.set(
            cx - radius,
            cy - radius,
            cx + radius,
            cy + radius
        )

        // Subtle dark track behind the orange ring
        canvas.drawCircle(cx, cy, radius, trackPaint)

        // Orange countdown arc
        canvas.drawArc(
            bounds,
            -90f,
            360f * progress,
            false,
            ringPaint
        )
    }
}