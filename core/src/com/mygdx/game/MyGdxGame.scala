package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class MyGdxGame extends Game {
    private var _batch: SpriteBatch = _
    private var _font: BitmapFont = _
    private var _shape: ShapeRenderer = _

    val util = new Util()

    val gameEngine = new GameEngine {
        override def shouldRevive(i: Int, j: Int): Boolean = {
            (!cells(i)(j).isAlive) &&
                (numberOfNeighborhoodAliveCells(i, j) == 3)
        }

        override def shouldKeepAlive(i: Int, j: Int): Boolean ={
            cells(i)(j).isAlive &&
                (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
        }

        override var height: Int = util.BOARD_HEIGHT
        override var width: Int = util.BOARD_WIDTH

        override var cells: Array[Array[Cell]] = Array.ofDim[Cell](height, width)
        this.init()

    }

    val eventHandler = new EventHandler(gameEngine)

    override def create(): Unit = {
        batch = new SpriteBatch()
        font = new BitmapFont()
        shape = new ShapeRenderer()
        this.setScreen(new MainScreen(this, gameEngine, eventHandler)) // Setting main screen
    }

    override def render(): Unit = {
        super.render()
    }

    override def dispose(): Unit = {
        this.batch.dispose()
        this.shape.dispose()
        this.font.dispose()
    }

    // Getters & Setters

    def batch: SpriteBatch = _batch

    def batch_=(batch: SpriteBatch): Unit = {
        _batch = batch
    }

    def font: BitmapFont = _font

    def font_=(font: BitmapFont): Unit = {
        _font = font
    }

    def shape: ShapeRenderer = _shape

    def shape_=(shape: ShapeRenderer): Unit = {
        _shape = shape
    }

}
