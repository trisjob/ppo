package tjobin.ppo

import processing.core.PApplet
import processing.core.PGraphics

open class Button {

    open val bgColor: Triple<Int, Int, Int>
        get() = Triple(200, 150, 150)
    val title: String
    val x: Float
    val y: Float
    val width: Float
    val heigth: Float
    val action: () -> Unit

    constructor(title: String, x: Float, y: Float, width: Float, heigth: Float, action: () -> Unit) {
        this.title = title
        this.x = x
        this.y = y
        this.width = width
        this.heigth = heigth
        this.action = action
    }

    val left: Float
        get() = x
    val right: Float
        get() = x + width
    val top: Float
        get() = y
    val bottom: Float
        get() = y + heigth

    fun isWithin(x: Float, y: Float) = x in left..right && y in top..bottom

    open fun draw(g: PGraphics) {
        g.fill(bgColor)
        g.stroke(64)
        g.rect(x, y, width, heigth)
        g.textFont(Launcher.font)
        g.fill(0)
        g.textAlign(PApplet.CENTER)
        g.text(title, x + width / 2, y + heigth / 2)
    }
}
