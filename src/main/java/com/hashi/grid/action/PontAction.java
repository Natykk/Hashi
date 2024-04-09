package com.hashi.grid.action;

import java.io.Serializable;

import com.hashi.grid.Grille;
import com.hashi.grid.Ile;
import com.hashi.grid.Pont;

public class PontAction implements Serializable {
    public int ile1X;
    public int ile1Y;
    public int ile2X;
    public int ile2Y;
    public boolean estDouble;

    public PontAction(int ile1X, int ile1Y, int ile2X, int ile2Y, boolean estDouble) {
        this.ile1X = ile1X;
        this.ile1Y = ile1Y;
        this.ile2X = ile2X;
        this.ile2Y = ile2Y;
        this.estDouble = estDouble;
    }

    public PontAction(Ile ile1, Ile ile2, boolean estDouble) {
        this.ile1X = ile1.getX();
        this.ile1Y = ile1.getY();
        this.ile2X = ile2.getX();
        this.ile2Y = ile2.getY();
        this.estDouble = estDouble;
    }

    public PontAction(Ile ile1, Ile ile2) {
        this(ile1, ile2, false);
    }

    public Pont getPont(Grille grille) {
        Ile ile1 = grille.getIleAt(ile1X, ile1Y);
        Ile ile2 = grille.getIleAt(ile2X, ile2Y);

        return grille.getPont(ile1, ile2);
    }

    public Pont newPont(Grille grille) {
        Ile ile1 = grille.getIleAt(ile1X, ile1Y);
        Ile ile2 = grille.getIleAt(ile2X, ile2Y);
        Pont pont = new Pont(ile1, ile2, grille);

        pont.setEstDouble(estDouble);

        return pont;
    }
}
