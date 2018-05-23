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

//    private var save = Button("save", 50f, 20f, 30f, 20f) {
//}

    override fun settings() {
        size(1200, 800)
    }

    private var events = mutableListOf<Event>()

    private var title = mutableListOf<Event>()

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
                val (dateStr, name) = line.split(":", limit = 2)
                events.add(Event(LocalDate.parse(dateStr.trim()), name.trim()))
            }
    }

    fun drawTimeLine() {


        val firstDate = events.first().date
        val lastDate = events.last().date
        val days = ChronoUnit.DAYS.between(firstDate, lastDate)
        val margin = 50
        val lineWidth = width - margin * 3
        val lineThick = 5
        val ychange = 60

        fill(100)
        rect(margin.toFloat(), 400f - lineThick, lineWidth.toFloat(), lineThick.toFloat())

        events.forEachIndexed { idx, event ->
            val daysFromFirst = ChronoUnit.DAYS.between(firstDate, event.date)
            val ratio = daysFromFirst / days.toFloat()
            val x = lineWidth * ratio + margin
            val mody = if (idx % 2 == 0) -1 else 1
            val displayText = "${event.name}\n${event.date.format(DateTimeFormatter.ISO_DATE)}"

            line(x, 400f, x, 400f - ychange * mody)

            fill(0)
            val buttonTest = Button(displayText, x, 400f - ychange * mody * 2, textWidth(displayText) + 10, (ychange * mody).toFloat()) {
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