package tjobin.ppo

import processing.core.PApplet
import processing.core.PGraphics

class Button {

    val title: String
    val x: Float
    val y: Float
    val width: Float
    val action: () -> Unit

    constructor(title: String, x: Float, y: Float, width: Float, action: () -> Unit) {
        this.title = title
        this.x = x
        this.y = y
        this.width = width
        this.action = action
    }

    val left: Float
        get() = x
    val right: Float
        get() = x + width
    val top: Float
        get() = y
    val bottom: Float
        get() = y + height

    fun isWithin(x: Float, y: Float) = x in left..right && y in top..bottom

    private val height = 40f

    fun draw(g: PGraphics) {
        g.fill(176)
        g.stroke(64)
        g.rect(x, y, width, height)
        g.textFont(Launcher.font)
        g.fill(0)
        g.textAlign(PApplet.CENTER)
        g.text(title, x + width / 2, y + height - 12)
    }
}
