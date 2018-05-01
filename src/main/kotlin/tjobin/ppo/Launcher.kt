package tjobin.ppo

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PFont
import processing.event.MouseEvent

class Launcher : PApplet() {
    companion object {
        lateinit var font: PFont
    }

    private val space = 15f
    private val buttonsY = 5f

    private val startButton = Button("Start", space, buttonsY, 75f) {
        consoleText += "Wooow !\n"
    }
    private val stopButton = Button("Stop", startButton.right + space, buttonsY, 75f) {
        consoleText += "Downer\n"
    }
    private val moreButton = Button("More", stopButton.right + space, buttonsY, 75f) {
        consoleText += "Give it to me\n"
    }
    private val againButton = Button("Again", moreButton.right + space, buttonsY, 75f) {
        consoleText += "Hourra\n"
    }

    private val allButtons: List<Button> = listOf(startButton, stopButton, moreButton, againButton)

    private var consoleText = ""

    override fun settings() {
        size(400, 400)
    }

    override fun setup() {
        font = createFont("Arial", 16f)
        background(255)
        allButtons.forEach { it.draw(graphics) }
    }

    override fun draw() {
        textAlign(PConstants.LEFT)
        text(consoleText, 15f, 70f)
    }

    override fun mouseClicked(event: MouseEvent?) {
        event?.let {
            allButtons
                .filter { it.isWithin(event.x.toFloat(), event.y.toFloat()) }
                .forEach { it.action() }
        }
    }
}
