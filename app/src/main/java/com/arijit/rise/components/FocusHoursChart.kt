package com.arijit.rise.components

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import kotlin.math.min

class FocusHoursChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    // ----------------------------------------------------
    // DIMENSIONS
    // ----------------------------------------------------

    private val density =
        resources.displayMetrics.density

    private val scaledDensity =
        resources.displayMetrics.scaledDensity

    private fun dp(value: Float): Float =
        value * density

    private fun sp(value: Float): Float =
        value * scaledDensity


    // ----------------------------------------------------
    // PAINTS
    // ----------------------------------------------------

    private val paint =
        Paint(Paint.ANTI_ALIAS_FLAG)

    private val textPaint =
        Paint(Paint.ANTI_ALIAS_FLAG)


    // ----------------------------------------------------
    // DATA
    // ----------------------------------------------------

    private val values = floatArrayOf(
        3.2f,
        3.8f,
        5.4f,
        3.5f,
        4.2f,
        2.1f,
        2.4f
    )

    private val days = arrayOf(
        "M",
        "T",
        "W",
        "T",
        "F",
        "S",
        "S"
    )

    private val selectedIndex = 2

    private val maxValue = 6f
    private val goal = 4f


    // ----------------------------------------------------
    // BAR ANIMATION
    // ----------------------------------------------------

    private var animationProgress = 1f
    private var animator: ValueAnimator? = null


    // ----------------------------------------------------
    // LIGHTNING ANIMATION
    // ----------------------------------------------------

    private var lightningOffset = 0f
    private var lightningAnimator: ValueAnimator? = null

    private val lightningPath = Path()


    // ----------------------------------------------------
    // INIT
    // ----------------------------------------------------

    init {

        paint.style = Paint.Style.FILL

        textPaint.typeface =
            Typeface.create(
                "sans-serif",
                Typeface.NORMAL
            )

        // Required for selected bar glow.
        setLayerType(
            LAYER_TYPE_SOFTWARE,
            null
        )
    }


    // ----------------------------------------------------
    // DRAW
    // ----------------------------------------------------

    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        drawGoalLine(
            canvas,
            width,
            height
        )

        drawBars(
            canvas,
            width,
            height
        )
    }


    // ----------------------------------------------------
    // GOAL LINE
    // ----------------------------------------------------

    private fun drawGoalLine(
        canvas: Canvas,
        width: Float,
        height: Float
    ) {

        val chartTop = dp(45f)

        val chartBottom =
            height - dp(55f)

        val chartHeight =
            chartBottom - chartTop

        val goalY =
            chartBottom -
                    (goal / maxValue) *
                    chartHeight


        // Dashed goal line

        paint.shader = null
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dp(1f)

        paint.color =
            Color.argb(
                80,
                130,
                115,
                105
            )

        paint.pathEffect =
            DashPathEffect(
                floatArrayOf(
                    dp(4f),
                    dp(7f)
                ),
                0f
            )

        canvas.drawLine(
            dp(20f),
            goalY,
            width - dp(20f),
            goalY,
            paint
        )

        paint.pathEffect = null
        paint.style = Paint.Style.FILL


        // "4h"

        textPaint.textAlign =
            Paint.Align.RIGHT

        textPaint.textSize =
            sp(11f)

        textPaint.color =
            Color.rgb(
                145,
                135,
                125
            )

        canvas.drawText(
            "4h",
            width - dp(15f),
            goalY + dp(4f),
            textPaint
        )
    }


    // ----------------------------------------------------
    // BARS
    // ----------------------------------------------------

    private fun drawBars(
        canvas: Canvas,
        width: Float,
        height: Float
    ) {

        val chartTop =
            dp(45f)

        val chartBottom =
            height - dp(55f)

        val chartHeight =
            chartBottom - chartTop


        // Horizontal spacing

        val horizontalPadding =
            dp(30f)

        val availableWidth =
            width -
                    horizontalPadding * 2

        val slotWidth =
            availableWidth /
                    values.size


        // Bar width

        val barWidth =
            min(
                dp(38f),
                slotWidth * 0.55f
            )


        values.forEachIndexed { index, value ->

            val centerX =
                horizontalPadding +
                        slotWidth * index +
                        slotWidth / 2f


            // Full height

            val barHeight =
                (value / maxValue) *
                        chartHeight


            // Animated height

            val animatedHeight =
                barHeight *
                        animationProgress

            val top =
                chartBottom -
                        animatedHeight

            val bottom =
                chartBottom


            val rect =
                RectF(
                    centerX -
                            barWidth / 2f,
                    top,
                    centerX +
                            barWidth / 2f,
                    bottom
                )


            // ------------------------------------------------
            // INACTIVE BAR
            // ------------------------------------------------

            if (index != selectedIndex) {

                paint.shader = null
                paint.style =
                    Paint.Style.FILL

                paint.color =
                    Color.rgb(
                        34,
                        27,
                        23
                    )

                canvas.drawRoundRect(
                    rect,
                    barWidth / 2f,
                    barWidth / 2f,
                    paint
                )


                // Border

                paint.style =
                    Paint.Style.STROKE

                paint.strokeWidth =
                    dp(1f)

                paint.color =
                    Color.rgb(
                        55,
                        45,
                        39
                    )

                canvas.drawRoundRect(
                    rect,
                    barWidth / 2f,
                    barWidth / 2f,
                    paint
                )

                paint.style =
                    Paint.Style.FILL
            }


            // ------------------------------------------------
            // SELECTED BAR
            // ------------------------------------------------

            else {

                paint.shader = null

                paint.setShadowLayer(
                    dp(14f),
                    0f,
                    0f,
                    Color.rgb(
                        255,
                        164,
                        20
                    )
                )


                val gradient =
                    LinearGradient(
                        0f,
                        top,
                        0f,
                        bottom,

                        Color.rgb(
                            255,
                            220,
                            120
                        ),

                        Color.rgb(
                            255,
                            158,
                            15
                        ),

                        Shader.TileMode.CLAMP
                    )

                paint.shader =
                    gradient

                canvas.drawRoundRect(
                    rect,
                    barWidth / 2f,
                    barWidth / 2f,
                    paint
                )

                paint.clearShadowLayer()
                paint.shader = null
            }


            // ------------------------------------------------
            // VALUE
            // ------------------------------------------------

            textPaint.textAlign =
                Paint.Align.CENTER

            textPaint.textSize =
                sp(11f)

            textPaint.color =
                if (index == selectedIndex) {
                    Color.rgb(
                        255,
                        170,
                        35
                    )
                } else {
                    Color.rgb(
                        145,
                        135,
                        125
                    )
                }

            textPaint.typeface =
                if (index == selectedIndex) {
                    Typeface.DEFAULT_BOLD
                } else {
                    Typeface.DEFAULT
                }


            // Value is positioned consistently
            // relative to each bar.

            val valueBaseline =
                top - dp(16f)

            canvas.drawText(
                String.format(
                    "%.1f",
                    value
                ),
                centerX,
                valueBaseline,
                textPaint
            )


            // ------------------------------------------------
            // LIGHTNING
            // ------------------------------------------------

            if (index == selectedIndex) {

                drawLightning(
                    canvas,
                    centerX,
                    valueBaseline - dp(40f)
                )
            }


            // ------------------------------------------------
            // DAY
            // ------------------------------------------------

            textPaint.textSize =
                sp(13f)

            textPaint.color =
                if (index == selectedIndex) {
                    Color.rgb(
                        255,
                        170,
                        35
                    )
                } else {
                    Color.rgb(
                        150,
                        140,
                        130
                    )
                }

            textPaint.typeface =
                if (index == selectedIndex) {
                    Typeface.DEFAULT_BOLD
                } else {
                    Typeface.DEFAULT
                }

            canvas.drawText(
                days[index],
                centerX,
                height - dp(17f),
                textPaint
            )
        }
    }


    // ----------------------------------------------------
    // LIGHTNING
    // ----------------------------------------------------

    private fun drawLightning(
        canvas: Canvas,
        centerX: Float,
        top: Float
    ) {

        /*
         * Small custom lightning bolt.
         *
         * It sits above the value with a small
         * consistent gap and gently bounces.
         */

        val size =
            dp(20f)

        val x =
            centerX

        val y =
            top + lightningOffset


        lightningPath.reset()


        // Top point

        lightningPath.moveTo(
            x + size * 0.18f,
            y
        )


        // Upper left

        lightningPath.lineTo(
            x - size * 0.30f,
            y + size * 0.50f
        )


        // Middle left

        lightningPath.lineTo(
            x - size * 0.02f,
            y + size * 0.50f
        )


        // Bottom point

        lightningPath.lineTo(
            x - size * 0.22f,
            y + size
        )


        // Right lower

        lightningPath.lineTo(
            x + size * 0.48f,
            y + size * 0.35f
        )


        // Right middle

        lightningPath.lineTo(
            x + size * 0.14f,
            y + size * 0.35f
        )


        lightningPath.close()


        paint.shader = null
        paint.style =
            Paint.Style.FILL

        paint.color =
            Color.rgb(
                255,
                190,
                35
            )

        canvas.drawPath(
            lightningPath,
            paint
        )
    }


    // ----------------------------------------------------
    // SET DATA
    // ----------------------------------------------------

    fun setData(
        newValues: FloatArray
    ) {

        if (newValues.size != 7)
            return

        newValues.copyInto(values)

        invalidate()
    }


    // ----------------------------------------------------
    // BAR ANIMATION
    // ----------------------------------------------------

    fun animateChart() {

        animator?.cancel()

        animationProgress = 0f

        animator =
            ValueAnimator.ofFloat(
                0f,
                1f
            ).apply {

                duration = 800L

                interpolator =
                    DecelerateInterpolator()

                addUpdateListener { animation ->

                    animationProgress =
                        animation.animatedValue
                                as Float

                    invalidate()
                }

                start()
            }
    }


    // ----------------------------------------------------
    // LIGHTNING ANIMATION
    // ----------------------------------------------------

    fun startLightningAnimation() {

        lightningAnimator?.cancel()

        lightningAnimator =
            ValueAnimator.ofFloat(
                -dp(2f),
                dp(2f)
            ).apply {

                // Subtle vertical bounce.

                duration = 650L

                repeatCount =
                    ValueAnimator.INFINITE

                repeatMode =
                    ValueAnimator.REVERSE

                interpolator =
                    AccelerateDecelerateInterpolator()

                addUpdateListener { animation ->

                    lightningOffset =
                        animation.animatedValue
                                as Float

                    invalidate()
                }

                start()
            }
    }


    // ----------------------------------------------------
    // CLEANUP
    // ----------------------------------------------------

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        post {
            animateChart()
            startLightningAnimation()
        }
    }

    override fun onDetachedFromWindow() {

        animator?.cancel()
        animator = null

        lightningAnimator?.cancel()
        lightningAnimator = null

        super.onDetachedFromWindow()
    }
}