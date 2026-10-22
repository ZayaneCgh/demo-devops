package fr.univeiffel.boulangerie;

import static fr.univeiffel.boulangerie.Panier.PRIX_BAGUETTE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests automatiques : ils tournent à chaque push, dans le pipeline.
 * Si un seul échoue, la mise en production est bloquée.
 */
class PanierTest {

    @Test
    @DisplayName("1 baguette coûte 1,20 €")
    void uneBaguette() {
        assertEquals(120, Panier.total(1, PRIX_BAGUETTE));
    }

    @Test
    @DisplayName("3 baguettes coûtent 3,60 €")
    void troisBaguettes() {
        assertEquals(360, Panier.total(3, PRIX_BAGUETTE));
    }

    @Test
    @DisplayName("Un panier vide coûte 0 €")
    void panierVide() {
        assertEquals(0, Panier.total(0, PRIX_BAGUETTE));
    }

    @Test
    @DisplayName("Une quantité négative est refusée")
    void quantiteNegative() {
        assertThrows(IllegalArgumentException.class, () -> Panier.total(-1, PRIX_BAGUETTE));
    }

    @Test
    @DisplayName("Affichage en euros à la française")
    void formatEuros() {
        assertEquals("3,60 €", Panier.formatEuros(360));
        assertEquals("12,05 €", Panier.formatEuros(1205));
    }
}
