package rules

import com.mygdx.game.GameEngine


/**
  * Created by felipecosta on 10/16/17.
  */
class HighLife extends GameEngine{
    override var nome: String = "HighLife"

    override def shouldRevive(i: Int, j: Int): Boolean = {
        (!cells(i)(j).isAlive) &&
            (numberOfNeighborhoodAliveCells(i, j) == 6 || numberOfNeighborhoodAliveCells(i, j) == 3)
    }

    override def shouldKeepAlive(i: Int, j: Int): Boolean ={
        cells(i)(j).isAlive &&
            (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
    }
}
