package com.mygdx.game

import scala.collection.mutable

/**
  * Created by felipecosta on 10/14/17.
  */
class CareTaker {
    val SIZE = 10
    var mementoList: Array[Memento] = Array.ofDim[Memento](SIZE)
    var head = 0

    def append(memento: Memento): Unit = {
        if(head >= SIZE) {
            for(i <- 0 until SIZE - 1) {
                mementoList(i) = mementoList(i+1)
            }
            head -= 1
        }
        mementoList(head) = memento
        if(head < SIZE) {
            head += 1
        }

    }

    def prev(): Memento = {
        if(head > 0) { head -= 1 }
        mementoList(head)
    }

}
