package com.arijit.rise.components

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import kotlin.math.max
import kotlin.math.min


class CircularProgressView : View {
    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arcRect = RectF()
    private var progress = 0f

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        // Background
        trackPaint.setStyle(Paint.Style.STROKE)
        trackPaint.setStrokeWidth(20f)
        trackPaint.setStrokeCap(Paint.Cap.ROUND)
        trackPaint.setColor(Color.parseColor("#29221D"))

        // Orange progress
        progressPaint.setStyle(Paint.Style.STROKE)
        progressPaint.setStrokeWidth(20f)
        progressPaint.setStrokeCap(Paint.Cap.ROUND)
        progressPaint.setColor(Color.parseColor("#FFA000"))

        // Percentage text
        textPaint.setStyle(Paint.Style.FILL)
        textPaint.setTextAlign(Paint.Align.CENTER)
        textPaint.setColor(Color.WHITE)
        textPaint.setTextSize(13 * getResources().getDisplayMetrics().scaledDensity)
        textPaint.setTypeface(
            Typeface.create(
                "sans",
                Typeface.BOLD
            )
        )

        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = getWidth() / 2f
        val centerY = getHeight() / 2f

        val strokeWidth = progressPaint.getStrokeWidth()

        val radius =
            (min(getWidth(), getHeight()) / 2f
                    - strokeWidth)

        arcRect.set(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )

        // Background ring
        canvas.drawArc(
            arcRect,
            0f,
            360f,
            false,
            trackPaint
        )

        // Progress ring
        val sweepAngle = progress * 3.6f

        canvas.drawArc(
            arcRect,
            -90f,
            sweepAngle,
            false,
            progressPaint
        )

        // Center percentage
        val metrics = textPaint.getFontMetrics()

        val textY =
            (centerY
                    - (metrics.ascent + metrics.descent) / 2f)

        canvas.drawText(
            Math.round(progress).toString() + "%",
            centerX,
            textY,
            textPaint
        )
    }

    fun setProgress(progress: Float) {
        this.progress = max(
            0f,
            min(progress, 100f)
        )

        invalidate()
    }

    fun getProgress(): Float {
        return progress
    }

    fun animateProgress(targetProgress: Float) {
        val animator =
            ValueAnimator.ofFloat(
                progress,
                targetProgress
            )

        animator.setDuration(600)

        animator.addUpdateListener(AnimatorUpdateListener { animation: ValueAnimator? ->
            progress = animation!!.getAnimatedValue() as Float
            invalidate()
        })

        animator.start()
    }
}