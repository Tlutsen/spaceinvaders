package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class SpaceInvaders {
    public static final char MARQUE_VAISSEAU = 'V';
    public static final char MARQUE_VIDE = '.';
    public static final char MARQUE_FIN_LIGNE = '\n';
    int longueur;
    int hauteur;
    Vaisseau vaisseau;

    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    public void positionnerUnNouveauVaisseau(int x, int y) {
        if (!estDansEspaceJeu(x, y)) {
            throw new HorsEspaceJeuException("Vous êtes en dehors de l'espace jeu");
        }

        vaisseau = new Vaisseau(x, y);
    }

    private boolean estDansEspaceJeu(int x, int y) {
        return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
    }

    public String recupererEspaceJeuDansChaineASCII() {
        StringBuilder espaceDeJeu = new StringBuilder();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < longueur; x++) {
                espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
            }
            espaceDeJeu.append(MARQUE_FIN_LIGNE);
        }
        return espaceDeJeu.toString();
    }

    public void deplacerVaisseauVersLaDroite() {
        if (vaisseau.abscisse()< (longueur-1)) vaisseau.seDeplacerVersLaDroite();
    }

    private char recupererMarqueDeLaPosition(int y, int x) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
            marque = MARQUE_VAISSEAU;
        } else {
            marque = MARQUE_VIDE;
        }
        return marque;
    }

    private boolean aUnVaisseauQuiOccupeLaPosition(int y, int x) {
        return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
    }

    private boolean aUnVaisseau() {
        return vaisseau!=null;
    }

    public void deplacerVaisseauVersLaGauche() {
        if (vaisseau.abscisse() > (0)) vaisseau.seDeplacerVersLaGauche();
    }
}