package tjobin.ppo

import processing.core.PGraphics

fun PGraphics.fill(color: Triple<Int, Int, Int>) {
    fill(color.first.toFloat(), color.second.toFloat(), color.third.toFloat())
}