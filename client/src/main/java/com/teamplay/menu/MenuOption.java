package com.teamplay.menu;

public class MenuOption {

    public static final MenuOption NONE = new MenuOption();
    public final String text;
    private final MenuOption parent;
    private final MenuAction action;

    private MenuOption(){
        text = null;
        parent = null;
        action = null;
    }

    public MenuOption(String text, MenuOption parent, MenuAction action){
        this.text=text;
        this.parent = parent;
        this.action = action;

    }
    public MenuOption getParent() {
        if (parent != MenuOption.NONE){
            return parent;
        } else {
            return this;
        }
    }

    public void invoke() {
        action.invoke();
    }
}
