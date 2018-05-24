package tjobin.ppo

import processing.core.PApplet
import processing.core.PFont
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Launcher : PApplet() {

    companion object {
        lateinit var font: PFont
    }

    override fun settings() {
        size(1200, 800)
    }

    private var events = mutableListOf<Event>()

    override fun setup() {
        loadFile()

        font = createFont("Arial", 16f, true)
        background(255)
        drawTimeLine()

    }

    private fun loadFile() {
        val file = File("${System.getProperty("user.home")}/timeEvent.txt")
        file.readLines()
            .filter { it.isNotEmpty() }
            .filter { !it.contains("//") }
            .filter { it.contains(":") }
            .forEach { line ->
                val (dateStr, done, name) = line.split(":", limit = 3)
                events.add(Event(LocalDate.parse(dateStr.trim()), name.trim(), done.trim().toBoolean()))
            }
    }

    fun drawTimeLine() {

        val yPosition = height / 2f
        val today = LocalDate.now()
        val firstDate = events.first().date
        val lastDate = events.last().date
        val days = ChronoUnit.DAYS.between(firstDate, lastDate)
        val margin = 50
        val lineWidth = width - margin * 3
        val lineThick = 5
        val ychange = 60
        val indicator = 20f
        val indicatorX = (ChronoUnit.DAYS.between(firstDate, today)).toFloat() / days.toFloat() * lineWidth + margin

        fill(100)
        rect(margin.toFloat(), yPosition - lineThick, lineWidth.toFloat(), lineThick.toFloat())

        stroke(150f, 150f, 200f)
        strokeWeight(4f)
        line(indicatorX, yPosition - indicator - lineThick, indicatorX, yPosition + indicator)
        strokeWeight(1f)
        stroke(0)

        events.forEachIndexed { idx, event ->
            val daysFromFirst = ChronoUnit.DAYS.between(firstDate, event.date)
            val ratio = daysFromFirst / days.toFloat()
            val x = lineWidth * ratio + margin
            val mody = if (idx % 2 == 0) -1 else 1
            val displayText = "${event.name}\n${event.date.format(DateTimeFormatter.ISO_DATE)}"

            line(x, 400f, x, 400f - ychange * mody)

            fill(0)
            val buttonTest = EventButton(displayText, x, 400f - ychange * mody * 2, textWidth(displayText) + 10, (ychange * mody).toFloat(), event.done) {
                text("lol", 15f, 70f)
            }
            buttonTest.draw(graphics)

        }
    }

//    override fun mouseClicked(event: MouseEvent?) {
//        event?.let {
//            allButtons
//                    .filter { it.isWithin(event.x.toFloat(), event.y.toFloat()) }
//                    .forEach { it.action() }
//        }
//    }
//}
}