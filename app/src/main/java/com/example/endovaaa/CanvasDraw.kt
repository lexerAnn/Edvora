package com.example.endovaaa

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.endovaaa.util.Shapes.ARROW
import com.example.endovaaa.util.Shapes.CIRLCE
import com.example.endovaaa.util.Shapes.NON_SELECTED
import com.example.endovaaa.util.Shapes.PENCIL_DRAW
import com.example.endovaaa.util.Shapes.RECTANGLE


class CanvasDraw (context: Context?, attribs: AttributeSet?) : View(context, attribs) {
    var isDrawing = true
    lateinit var mPaint: Paint
    lateinit var mPath: Path
    lateinit var mBitmap: Bitmap
    lateinit var mCanvas: Canvas
    var shape = NON_SELECTED


    var mStartY = 0
    var mEndX = 0
    var mEndY = 0
    var mx = 0
    var my = 0
    var x = 0
    var y = 0
    lateinit var startPoint: PointF
    lateinit var endPoint: PointF
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
    }

    init {
        init()
    }

     fun init() {
        mPaint = Paint(Paint.DITHER_FLAG)
        mPaint.isAntiAlias = true
        mPaint.isDither = true
         mPaint.setColor(Color.BLACK);
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 3f
        mPath = Path()
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        mx = event.x.toInt()
        my = event.y.toInt()
        when (shape) {
            RECTANGLE -> onTouchEventRectangle(event)
            PENCIL_DRAW -> onTouchEventSmoothLine(event)
            CIRLCE -> onTouchEventCircle(event)
            ARROW -> arrowHead(event)
        }
        return true
    }

    private fun onTouchEventRectangle(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isDrawing = true
                mStartX = mx
                mStartY = my
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> invalidate()
            MotionEvent.ACTION_UP -> {
                isDrawing = false
                drawRectangle(mCanvas, mPaint)
                invalidate()
            }
        }
    }

    private fun onTouchEventCircle(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isDrawing = true
                mStartX = mx
                mStartY = my
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> invalidate()
            MotionEvent.ACTION_UP -> {
                isDrawing = false
                mCanvas.drawCircle(
                    mStartX.toFloat(), mStartY.toFloat(),
                    calculateRadius(
                        mStartX.toFloat(),
                        mStartY.toFloat(),
                        mx.toFloat(),
                        my.toFloat()
                    ), mPaint
                )
                invalidate()
            }
        }
    }

    fun arrowHead(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath.reset()
                mPath.moveTo(mx.toFloat(), my.toFloat())
                x = mx
                my = y
                startPoint = PointF(event.x, event.y)
                endPoint = PointF()
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                mx = x
                mx = y
                endPoint.x = event.x
                endPoint.y = event.y
                isDrawing = true
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                mPath.lineTo(mx.toFloat(), my.toFloat())
                val deltaX = endPoint.x - startPoint.x
                val deltaY = endPoint.y - startPoint.y
                val frac = 0.1.toFloat()
                val point_x_1 = startPoint.x + ((1 - frac) * deltaX + frac * deltaY)
                val point_y_1 = startPoint.y + ((1 - frac) * deltaY - frac * deltaX)
                val point_x_2 = endPoint.x
                val point_y_2 = endPoint.y
                val point_x_3 = startPoint.x + ((1 - frac) * deltaX - frac * deltaY)
                val point_y_3 = startPoint.y + ((1 - frac) * deltaY + frac * deltaX)
                mPath.moveTo(point_x_1, point_y_1)
                mPath.lineTo(point_x_2, point_y_2)
                mPath.lineTo(point_x_3, point_y_3)
                mCanvas.drawPath(mPath, mPaint)
                endPoint.x = event.x
                endPoint.y = event.y
                isDrawing = false
                invalidate()
            }
            else -> {
            }
        }
    }

    private fun onTouchEventSmoothLine(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isDrawing = true
                mStartX = mx
                mStartY = my
                mPath.reset()
                mPath.moveTo(mx.toFloat(), my.toFloat())
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = Math.abs(mx - mStartX).toFloat()
                val dy = Math.abs(my - mStartY).toFloat()
                if (dx >= 2.12 || dy >= 2.32) {
                    mPath.quadTo(
                        mStartX.toFloat(),
                        mStartY.toFloat(),
                        ((mx + mStartX) / 2).toFloat(),
                        ((my + mStartY) / 2).toFloat()
                    )
                    mStartX = mx
                    mStartY = my
                }
                mCanvas.drawPath(mPath, mPaint)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                isDrawing = false
                mPath.lineTo(mStartX.toFloat(), mStartY.toFloat())
                mCanvas.drawPath(mPath, mPaint)
                mPath.reset()
                invalidate()
            }
        }
    }

    protected fun calculateRadius(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return Math.sqrt(
            Math.pow((x1 - x2).toDouble(), 2.0) +
                    Math.pow((y1 - y2).toDouble(), 2.0)
        ).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
        if (isDrawing) {
            when (shape) {
                RECTANGLE -> onDrawRectangle(canvas)
                PENCIL_DRAW -> onDrawSwik(canvas)
                CIRLCE -> onDrawCircle(canvas)
            }
        }
    }

    private fun drawRectangle(canvas: Canvas?, paint: Paint?) {
        val right = if (mStartX > mx) mStartX.toFloat() else mx.toFloat()
        val left = if (mStartX > mx) mx.toFloat() else mStartX.toFloat()
        val bottom = if (mStartY > my) mStartY.toFloat() else my.toFloat()
        val top = if (mStartY > my) my.toFloat() else mStartY.toFloat()
        canvas!!.drawRect(left, top, right, bottom, paint!!)
    }

    private fun drawSwik(canvas: Canvas?, paint: Paint?) {
        canvas!!.drawPath(mPath, paint!!)
    }

    private fun onDrawRectangle(canvas: Canvas) {
        drawRectangle(canvas, mPaint)
    }

    private fun onDrawSwik(canvas: Canvas) {
        drawSwik(canvas, mPaint)
    }

    private fun onDrawCircle(canvas: Canvas) {
        canvas.drawCircle(
            mStartX.toFloat(),
            mStartY.toFloat(),
            calculateRadius(mStartX.toFloat(), mStartY.toFloat(), mx.toFloat(), my.toFloat()),
            mPaint
        )
    }
}