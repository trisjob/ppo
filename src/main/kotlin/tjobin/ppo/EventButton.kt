package tjobin.ppo

class EventButton : Button {
    private val density = 5

    var done: Boolean = false
    override val bgColor: Triple<Int, Int, Int>
        get() = if (done) Triple(150, 200, 150) else super.bgColor


    constructor(title: String, x: Float, y: Float, width: Float, heigth: Float, done: Boolean, action: () -> Unit) : super(title, x, y, width, heigth, action) {
        this.done = done
    }
}