package com.metaphore.bmfmetaedit.mainscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.crashinvaders.common.eventmanager.EventManager;
import com.crashinvaders.screenmanager.BaseScreen;
import com.crashinvaders.screenmanager.Bundle;
import com.metaphore.bmfmetaedit.App;
import com.metaphore.bmfmetaedit.mainscreen.selection.GlyphSelectionManager;
import com.metaphore.bmfmetaedit.mainscreen.view.RootTable;

public class MainScreen extends BaseScreen implements MainScreenContext {

    private final MainResources resources;
    private final Stage stage;
    private final GlyphSelectionManager selectionManager;
    private final KeyBinds keyBinds;

    public MainScreen() {
        resources = new MainResources(App.inst().getAssets());
        stage = new Stage(new ScreenViewport());
        keyBinds = new KeyBinds(this);
        selectionManager = new GlyphSelectionManager(App.inst().getEvents());

        // Root table
        {
            RootTable rootTable = new RootTable(this);
            rootTable.setFillParent(true);
            stage.addActor(rootTable);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show(Bundle bundle) {
        App.inst().getInput().addProcessor(stage, 0);
        App.inst().getInput().addProcessor(keyBinds, 5);
    }

    @Override
    public void hide(Bundle bundle) {
        App.inst().getInput().removeProcessor(stage);
        App.inst().getInput().removeProcessor(keyBinds);
    }

    @Override
    public void resume() {
        Gdx.gl20.glClearColor(0,0,0,1);
    }

    @Override
    public void dispose() {
        stage.dispose();
        resources.dispose();
        selectionManager.dispose();
    }

    //region MainScreenContext implementation
    @Override
    public GlyphSelectionManager getSelectionManager() {
        return selectionManager;
    }

    @Override
    public MainResources getResources() {
        return resources;
    }

    @Override
    public EventManager getEvents() {
        return App.inst().getEvents();
    }

    @Override
    public Stage getStage() {
        return stage;
    }
//endregion
}
