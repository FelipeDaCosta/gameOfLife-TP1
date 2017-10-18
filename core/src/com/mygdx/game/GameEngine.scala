package com.mygdx.game

import scala.collection.mutable.ListBuffer

/**
  * Created by felipecosta on 10/1/17.
  */
abstract class GameEngine {
    var nome: String

    val util = new Util()
    val careTaker = new CareTaker()

    var height: Int = util.BOARD_HEIGHT
    var width: Int = util.BOARD_WIDTH

    var cells: Array[Array[Cell]] = Array.ofDim[Cell](height, width)

    for(i <- 0 until height) {
        for(j <- 0 until width) {
            cells(i)(j) = new Cell()
        }
    }

    var auto = false
    var count = 0
    var changeGenAuto = 50

    def shouldRevive(i: Int, j: Int): Boolean

    def shouldKeepAlive(i: Int, j: Int): Boolean

    def nextGeneration(): Unit = {
        val mustRevive = new ListBuffer[Cell]
        val mustKill = new ListBuffer[Cell]

        save() //Saving memento before changing state

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
            Statistics.recordRevive
        }

        for(cell <- mustKill) {
            cell.kill()
            Statistics.recordKill
        }
    }

    private def valid(i: Int, j: Int): Boolean = {
        i >= 0 && i < height && j >= 0 && j < width
    }

    private def makeValid(i: Int, j: Int): (Int, Int) = {
        var new_i: Int = (height + i)%height
        var new_j: Int = (width + j)%width
        (new_i, new_j)
    }

    @throws(classOf[IllegalArgumentException])
    def makeCellAlive(i: Int, j: Int): Unit = {
        val (c, d) = makeValid(i, j)
        if(valid(c, d)) {
            cells(c)(d).revive()
        } else {
            throw new IllegalArgumentException()
        }
    }

    @throws(classOf[IllegalArgumentException])
    def killCell(i: Int, j: Int): Unit = {
        val (c, d) = makeValid(i, j)
        if(valid(c, d)) {
            cells(c)(d).kill()
        } else {
            throw new IllegalArgumentException()
        }
    }

    @throws(classOf[IllegalArgumentException])
    def isCellAlive(i: Int, j: Int): Boolean = {
        val (c, d) = makeValid(i, j)
        if(valid(c, d)) {
            cells(c)(d).isAlive // Return
        } else {
            throw new IllegalArgumentException
        }
    }

    def killAllCells(): Unit ={
        for(i <- 0 until height) {
            for(j <- 0 until width) {
                if(isCellAlive(i, j)) { killCell(i,j) }
            }
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
        for(a <- i - 1 to i + 1) {
            for(b <- j - 1 to j + 1) {
                val (c, d) = makeValid(a, b)
                if((c != i || d != j) && cells(c)(d).isAlive) {
                    alive += 1
                }
            }
        }
        alive // Return
    }

    // Memento

    private def save(): Unit = {
        val memento = new Memento(cells)
        careTaker.append(memento)
    }

    def prevGeneration(): Unit = {
        val newMemento = careTaker.prev()
        if(newMemento != null) {
            this.cells = newMemento.getState.map(_.clone())
        }
    }

}