package com.hashi.menu;

import com.hashi.style.Panel;

import java.awt.*;

public class Chapitre extends Panel {
    public Chapitre(int chapitre) {
        super(new BorderLayout(), "bg-history-load-game.png");
        switch (chapitre){
            case 1:
                PageManager.getInstance().setTitle("Chapitre 1");
                break;
            case 2:
                PageManager.getInstance().setTitle("Chapitre 2");
                break;
            case 3:
                PageManager.getInstance().setTitle("Chapitre 3");
                break;
        }
    }


}
