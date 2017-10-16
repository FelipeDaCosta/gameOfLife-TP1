package rules

import com.mygdx.game.GameEngine

/**
  * Created by felipecosta on 10/16/17.
  */
class MyRules extends GameEngine{
    override var nome: String = "Minha Regra"

    override def shouldRevive(i: Int, j: Int): Boolean = {
        (!cells(i)(j).isAlive) &&
            (numberOfNeighborhoodAliveCells(i, j) == 1)
    }

    override def shouldKeepAlive(i: Int, j: Int): Boolean = {
        !cells(i)(j).isAlive
    }
}
