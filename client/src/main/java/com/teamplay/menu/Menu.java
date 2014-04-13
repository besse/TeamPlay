package com.teamplay.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    List<MenuOption> options = new ArrayList<MenuOption>();

    private MenuOption acvtiveOption = null;

    public void setActiveMenuOption(MenuOption activeOption) {
        this.acvtiveOption = activeOption;
    }

    public void addMenuOption(MenuOption option) {
        if (options.isEmpty()) {
            setActiveMenuOption(option);
        }
        options.add(option);
    }

    public MenuOption getActiveOption() {
        return acvtiveOption;
    }

    public void moveUp() {
        acvtiveOption = optionAbove(acvtiveOption);
    }

    private MenuOption optionAbove(MenuOption option) {
        int index = Math.max(0, options.indexOf(option) - 1);
        return options.get(index);
    }

    public void moveDown() {
        acvtiveOption = optionBelow(acvtiveOption);
    }

    private MenuOption optionBelow(MenuOption option) {
        int index = Math.min(options.size()-1, options.indexOf(option) + 1);
        return options.get(index);
    }
}
