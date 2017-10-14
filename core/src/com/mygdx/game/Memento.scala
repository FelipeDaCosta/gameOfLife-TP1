package com.mygdx.game

/**
  * Created by felipecosta on 10/14/17.
  */
class Memento(newState: Array[Array[Cell]]) {
    val size = newState.length
    var state: Array[Array[Cell]] = Array.ofDim(size, size)

    for(i <- 0 until size) {
        for(j <- 0 until size) {
            state(i)(j) = new Cell()
            if(newState(i)(j).isAlive) {
                state(i)(j).revive()
            }
        }
    }

    def getState: Array[Array[Cell]] = {
        state
    }

}
