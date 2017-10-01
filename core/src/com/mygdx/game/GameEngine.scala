package com.mygdx.game

import scala.collection.mutable.ListBuffer

/**
  * Created by felipecosta on 10/1/17.
  */
abstract class GameEngine {
    var height: Int
    var width: Int

    var cells: Array[Array[Cell]]

    def shouldRevive(i: Int, j: Int): Boolean

    def shouldKeepAlive(i: Int, j: Int): Boolean


    def nextGeneration(): Unit = {
        val mustRevive = new ListBuffer[Cell]
        val mustKill = new ListBuffer[Cell]

        for(i <- 0 until height) {
            for(j <- 0 until width) {
                if(shouldRevive(i, j)) {
                    mustRevive += cells(i)(j)
                }
                else if((!shouldKeepAlive(i, j)) && cells(i)(j).isAlive) {
                    mustKill += cells(i)(j)
                }
            }
        }

        for(cell <- mustRevive) {
            cell.revive()
        }

        for(cell <- mustKill) {
            cell.kill()
        }
    }

    private def valid(i: Int, j: Int): Boolean = {
        i >= 0 && i < height && j >= 0 && j < width
    }

    @throws(classOf[IllegalArgumentException])
    def makeCellAlive(i: Int, j: Int): Unit = {
        if(valid(i, j)) {
            cells(i)(j).revive()
        } else {
            throw new IllegalArgumentException()
        }
    }

    @throws(classOf[IllegalArgumentException])
    def isCellAlive(i: Int, j: Int): Boolean = {
        if(valid(i, j)) {
            cells(i)(j).isAlive // Return
        } else {
            throw new IllegalArgumentException
        }
    }

    def numberOfAliveCells: Int = {
        var aliveCells = 0
        for(i <- 0 until height) {
            for(j <- 0 until width) {
                if(isCellAlive(i, j)) { aliveCells += 1 }
            }
        }
        aliveCells // Return
    }

    def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
        var alive = 0
        for(a <- (i - 1 to i + 1)) {
            for(b <- (j - 1 to j + 1)) {
                if(valid(a, b)) {
                    if((a != i || b != j) && cells(a)(b).isAlive) {
                        alive += 1
                    }
                }
            }
        }
        alive // Return
    }

}
