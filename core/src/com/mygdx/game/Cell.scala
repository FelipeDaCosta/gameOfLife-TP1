package com.mygdx.game

/**
  * Created by felipecosta on 10/1/17.
  */
class Cell {
    private var _alive = false

    def isAlive: Boolean = _alive

    def kill(): Unit  = { _alive = false }
    def revive(): Unit = { _alive = true }
}
