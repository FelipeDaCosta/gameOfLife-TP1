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
        mementoList(head) = memento
        head += 1
        if(head > SIZE - 1) { head = 0 }
    }

    def prev(): Memento = {
        head -= 1
        if(head < 0 ) { head = SIZE - 1 }
        if(mementoList(head) == null) { head = 0 }
        mementoList(head)
    }

}
