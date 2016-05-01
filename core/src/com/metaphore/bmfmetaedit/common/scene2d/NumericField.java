package com.metaphore.bmfmetaedit.common.scene2d;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;

public class NumericField extends TextField {
    private final NumberTextFilter filter;

    public NumericField(TextFieldStyle style) {
        super("", style);
        filter = new NumberTextFilter();
        setTextFieldFilter(filter);

        // Select all text on focus
        addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (actor == NumericField.this) {
                    selectAll();
                }
            }
        });

        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    getStage().setKeyboardFocus(null);
                    return true;
                }
                return super.keyDown(event, keycode);
            }
        });
    }

    public NumericField allowFloat() {
        filter.allowFloat();
        return this;
    }

    public void setInt(int value) {
        super.setText(value+"");
    }

    public int getInt() {
        try {
            return Integer.parseInt(super.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    @Deprecated
    public void setText(String str) {
        super.setText(str);
    }
    @Override
    @Deprecated
    public String getText() {
        return super.getText();
    }
}
