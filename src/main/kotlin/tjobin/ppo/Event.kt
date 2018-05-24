package tjobin.ppo

import java.time.LocalDate

data class Event(
    var date: LocalDate,
    var name: String,
    var done: Boolean = false
)