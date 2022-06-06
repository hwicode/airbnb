package com.example.airbnb.ui.common

import android.graphics.*
import android.graphics.drawable.Drawable

class TextDrawable(private val text: String) : Drawable() {

    private val paint: Paint = Paint().apply {
        this.color = Color.BLACK
        this.textSize = 40f
        this.style = Paint.Style.FILL
        this.textAlign = Paint.Align.CENTER
    }
    private val borderPaint = Paint().apply {
        this.style = Paint.Style.FILL_AND_STROKE
        this.color = Color.WHITE
        this.strokeWidth = 1f
        this.setShadowLayer(6f, 0f, 0f, Color.BLACK);
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(RectF(0f, 0f, 200f, 100f), 20f, 20f, borderPaint)
        canvas.drawText(text, 100f, 65f, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}