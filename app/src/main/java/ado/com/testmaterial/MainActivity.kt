package ado.com.testmaterial

import android.animation.Animator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewAnimationUtils

import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Point
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        imageView.visibility = View.INVISIBLE
        fab.setOnClickListener { view -> changeVisibilityAnimated() }
    }

    fun changeVisibilityAnimated() {
        val clickCoords = IntArray(2)
        fab.getLocationOnScreen(clickCoords)
        clickCoords[0] += fab.width / 2
        clickCoords[1] += fab.height / 2
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val maxRadius = size.y
        if (imageView.visibility == View.VISIBLE) {
            val animator = ViewAnimationUtils.
                  createCircularReveal(imageView, clickCoords[0], clickCoords[1], maxRadius.toFloat() , 0.0f)
            animator.apply {
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {
                        animator.removeListener(this)
                        imageView.visibility = View.INVISIBLE
                    }
                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {}
                })
                start()
            }
        } else {
            imageView.visibility = View.VISIBLE
            ViewAnimationUtils.createCircularReveal(imageView, clickCoords[0], clickCoords[1], 0.0f,
                    maxRadius.toFloat()).start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_about -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
