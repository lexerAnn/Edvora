package com.example.endovaaa

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.endovaaa.util.Shapes.RECTANGLE
import com.example.endovaaa.util.Shapes.ARROW
import com.example.endovaaa.util.Shapes.CIRLCE
import com.example.endovaaa.util.Shapes.PENCIL_DRAW


class MainActivity : AppCompatActivity() {
    private var myDrawView: CanvasDraw? = null
    private var swikImageView:ImageView? = null
    private var swikView:View? = null

    private var arrowImageView:ImageView? = null
    private var arrowView:View? = null

    private var recImageView:ImageView? = null
    private var recView:View? = null

    private var circleImageView:ImageView? = null
    private var circleView:View? = null

    private var paletteImageView:ImageView? = null
    private var paletteView:View? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        myDrawView = findViewById<View>(R.id.draw) as CanvasDraw;
        val rec = findViewById<View>(R.id.rect) as ImageView
        val cirle = findViewById<View>(R.id.circle) as ImageView
        val swik = findViewById<View>(R.id.swik) as ImageView
        val arrow = findViewById<View>(R.id.arrow) as ImageView
        val color = findViewById<View>(R.id.palette) as ImageView
        val pallete_colors = findViewById<View>(R.id.palette_option) as LinearLayout


        val red_color = findViewById<View>(R.id.red) as Button
        val green_color = findViewById<View>(R.id.green) as Button
        val blue_color = findViewById<View>(R.id.blue) as Button
        val black_color = findViewById<View>(R.id.black) as Button


        rec.setOnClickListener { view: View? ->
            val selectedOption = ContextCompat.getDrawable(this, R.drawable.selected);
            val notSelectedOption = ContextCompat.getDrawable(this, R.drawable.non_selected);

            recView = view
            recImageView = view as ImageView
            recImageView?.setImageResource(R.drawable.outline_rectangle_select_24)
            recImageView?.background = selectedOption


            swikView?.background = notSelectedOption
            swikImageView?.setImageResource(R.drawable.outline_edit_24)

            arrowView?.background = notSelectedOption
            arrowImageView?.setImageResource(R.drawable.outline_arrow_24)

            circleView?.background = notSelectedOption
            circleImageView?.setImageResource(R.drawable.outline_circle_24)

            paletteView?.background = notSelectedOption
            paletteImageView?.setImageResource(R.drawable.outline_palette_24)

            pallete_colors.visibility = View.INVISIBLE


            myDrawView?.shape = RECTANGLE


        }

        swik.setOnClickListener { view: View? ->
            val selectedOption = ContextCompat.getDrawable(this, R.drawable.selected);
            val notSelectedOption = ContextCompat.getDrawable(this, R.drawable.non_selected);
            swikView = view
            swikImageView = view as ImageView
            swikImageView?.setImageResource(R.drawable.outline_edit_select_24);
            swikView?.background = selectedOption

            arrowView?.background = notSelectedOption
            arrowImageView?.setImageResource(R.drawable.outline_arrow_24)

            recView?.background = notSelectedOption
            recImageView?.setImageResource(R.drawable.outline_rectangle_24)

            circleView?.background = notSelectedOption
            circleImageView?.setImageResource(R.drawable.outline_circle_24)

            paletteView?.background = notSelectedOption
            paletteImageView?.setImageResource(R.drawable.outline_palette_24)

            pallete_colors.visibility = View.INVISIBLE

            // swikView = view
            myDrawView?.shape = PENCIL_DRAW

        }

        cirle.setOnClickListener { view: View? ->
            val selectedOption = ContextCompat.getDrawable(this, R.drawable.selected);
            val notSelectedOption = ContextCompat.getDrawable(this, R.drawable.non_selected);


            circleView = view
            circleImageView = view as ImageView
            circleImageView?.setImageResource(R.drawable.outline_circle_select_24)
            circleView?.background = selectedOption

            arrowView?.background = notSelectedOption
            arrowImageView?.setImageResource(R.drawable.outline_arrow_24)


            recView?.background = notSelectedOption
            recImageView?.setImageResource(R.drawable.outline_rectangle_24)


            swikView?.background = notSelectedOption
            swikImageView?.setImageResource(R.drawable.outline_edit_24)

            paletteView?.background = notSelectedOption
            paletteImageView?.setImageResource(R.drawable.outline_palette_24)

            pallete_colors.visibility = View.INVISIBLE


            myDrawView?.shape = CIRLCE



        }

        arrow.setOnClickListener { view: View? ->
            val selectedOption = ContextCompat.getDrawable(this, R.drawable.selected);
            val notSelectedOption = ContextCompat.getDrawable(this, R.drawable.non_selected);

            arrowView = view
            arrowImageView = view as ImageView
            arrowImageView?.setImageResource(R.drawable.outline_arrow_select_24)
            arrowView?.background = selectedOption

            swikView?.background = notSelectedOption
            swikImageView?.setImageResource(R.drawable.outline_edit_24)

            recView?.background = notSelectedOption
            recImageView?.setImageResource(R.drawable.outline_rectangle_24)

            circleView?.background = notSelectedOption
            circleImageView?.setImageResource(R.drawable.outline_circle_24)

            paletteView?.background = notSelectedOption
            paletteImageView?.setImageResource(R.drawable.outline_palette_24)

            pallete_colors.visibility = View.INVISIBLE


            myDrawView?.shape = ARROW
        }

        color.setOnClickListener { view: View? ->
            val selectedOption = ContextCompat.getDrawable(this, R.drawable.selected);
            val notSelectedOption = ContextCompat.getDrawable(this, R.drawable.non_selected);

            paletteView = view
            paletteImageView = view as ImageView
            paletteImageView?.setImageResource(R.drawable.outline_palette_select_24)
            paletteView?.background = selectedOption


        pallete_colors.visibility = View.VISIBLE
            myDrawView?.mPaint?.setColor(Color.GREEN)


            swikView?.background = notSelectedOption
            swikImageView?.setImageResource(R.drawable.outline_edit_24)

            recView?.background = notSelectedOption
            recImageView?.setImageResource(R.drawable.outline_rectangle_24)

            circleView?.background = notSelectedOption
            circleImageView?.setImageResource(R.drawable.outline_circle_24)

            arrowView?.background = notSelectedOption
            arrowImageView?.setImageResource(R.drawable.outline_arrow_24)




        }
        red_color.setOnClickListener { _ ->
            pallete_colors.visibility = View.INVISIBLE
            myDrawView?.mPaint?.setColor(Color.RED)

        }
        green_color.setOnClickListener { _ ->
            pallete_colors.visibility = View.INVISIBLE
            myDrawView?.mPaint?.setColor(Color.GREEN)

        }

        blue_color.setOnClickListener { _->
            pallete_colors.visibility = View.INVISIBLE
            myDrawView?.mPaint?.setColor(Color.BLUE)

        }

        black_color.setOnClickListener { _ ->
            pallete_colors.visibility = View.INVISIBLE
            myDrawView?.mPaint?.setColor(Color.BLACK)

        }
    }
    companion object{
        var isPencilSelected = false

    }
}