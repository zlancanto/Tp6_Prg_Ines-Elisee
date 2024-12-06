package fr.istic.prg1.tree;

import java.util.Deque;

import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.NodeType;

import java.util.ArrayDeque;

/**
 * @author Zlanca-Nto MIHAN<zlanca-nto.mihan@etudiant.univ-rennes.fr>
 * @author Ines Gaetan NOUBI-SI KUISSEU
 *         <ines-gaetan.noubi@etudiant.univ-rennes.fr>
 * @version 4.0
 * @since 2015-06-15
 * @param <T>
 *            type formel d'objet pour la classe
 *
 *            Les arbres binaires sont construits par chaÃ®nage par rÃ©fÃ©rences
 *            pour les fils et une pile de pÃ¨res.
 */
public class BinaryTree<T> {

    /**
     * Type reprÃ©sentant les noeuds.
     */
    private class Element {
        public T value;
        public Element left, right;

        public Element() {
            value = null;
            left = null;
            right = null;
        }

        public boolean isEmpty() {
            return left == null && right == null;
        }
    }

    private Element root;

    public BinaryTree() {
    }

    /**
     * @return Un nouvel iterateur sur l'arbre this. Le noeud courant de
     *         l'itérateur est positionné sur la racine de l'arbre.
     */
    public TreeIterator iterator() {
        return null;
    }

    /**
     * @return true si l'arbre this est vide, false sinon
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Classe reprÃ©sentant les itÃ©rateurs sur les arbres binaires.
     */
    public class TreeIterator implements Iterator<T> {
        private Element currentNode;
        private Deque<Element> stack;

        private TreeIterator() {
            stack = new ArrayDeque<>();
            currentNode = root;
        }

        /**
         * L'itÃ©rateur se positionnne sur le fils gauche du noeud courant.
         * 
         * @pre Le noeud courant nâ€™est pas un butoir.
         */
        @Override
        public void goLeft() {
            assert !this.isEmpty() : "le butoir n'a pas de fils";
            stack.push(currentNode);
            currentNode = currentNode.left;
        }

        /**
         * L'itÃ©rateur se positionnne sur le fils droit du noeud courant.
         * 
         * @pre Le noeud courant nâ€™est pas un butoir.
         */
        @Override
        public void goRight() {
            assert !this.isEmpty() : "le butoir n'a pas de fils";
            stack.push(currentNode);
            currentNode = currentNode.right;
        }

        /**
         * L'itÃ©rateur se positionnne sur le pÃ¨re du noeud courant.
         * 
         * @pre Le noeud courant nâ€™est pas la racine.
         */
        @Override
        public void goUp() {
            assert !stack.isEmpty() : " la racine n'a pas de pere";
            currentNode = stack.pop();
        }

        /**
         * L'itÃ©rateur se positionne sur la racine de l'arbre.
         */
        @Override
        public void goRoot() {
            stack.clear();
            currentNode = root;
        }

        /**
         * @return true si l'iterateur est sur un sous-arbre vide, false sinon
         */
        @Override
        public boolean isEmpty() {
            currentNode
            return false;
        }

        /**
         * @return Le genre du noeud courant.
         */
        @Override
        public NodeType nodeType() {
            return NodeType.SENTINEL;
        }

        /**
         * Supprimer le noeud courant de l'arbre.
         * 
         * @pre Le noeud courant n'est pas un noeud double.
         */
        @Override
        public void remove() {
            try {
                assert nodeType() != NodeType.DOUBLE : "retirer : retrait d'un noeud double non permis";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        /**
         * Vider le sousâ€“arbre rÃ©fÃ©rencÃ© par le noeud courant, qui devient
         * butoir.
         */
        @Override
        public void clear() {
        }

        /**
         * @return La valeur du noeud courant.
         */
        @Override
        public T getValue() {
            return null;
        }

        /**
         * CrÃ©er un nouveau noeud de valeur v Ã cet endroit.
         * 
         * @pre Le noeud courant est un butoir.
         * 
         * @param v
         *          Valeur Ã ajouter.
         */

        @Override
        public void addValue(T v) {
            try {
                assert isEmpty() : "Ajouter : on n'est pas sur un butoir";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        /**
         * Affecter la valeur v au noeud courant.
         * 
         * @param v
         *          La nouvelle valeur du noeud courant.
         */
        @Override
        public void setValue(T v) {
        }

        private void ancestor(int i, int j) {
            try {
                assert !stack.isEmpty() : "switchValue : argument trop grand";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }
            Element x = stack.pop();
            if (j < i) {
                ancestor(i, j + 1);
            } else {
                T v = x.value;
                x.value = currentNode.value;
                currentNode.value = v;
            }
            stack.push(x);
        }

        /**
         * Ã‰changer les valeurs associÃ©es au noeud courant et Ã son pÃ¨re dâ€™ordre i
         * (le
         * noeud courant reste inchangÃ©).
         * 
         * @pre i>= 0 et racine est pÃ¨re du noeud courant dâ€™ordre >= i.
         * 
         * @param i ordre du pÃ¨re
         */
        @Override
        public void switchValue(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("switchValue : argument negatif");
            }
            if (i > 0) {
                ancestor(i, 1);
            }
        }
    }
}