package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class MyGdxGame extends Game {
    private var _batch: SpriteBatch = _
    private var _font: BitmapFont = _
    private var _shape: ShapeRenderer = _

    override def create(): Unit = {
        batch = new SpriteBatch()
        font = new BitmapFont()
        shape = new ShapeRenderer()
        this.setScreen(new MainScreen(this)) // Setting main screen
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
