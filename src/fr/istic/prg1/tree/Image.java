package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;

/**
 * @author Zlanca-Nto ELisée MIHAN<zlanca-nto.mihan@etudiant.univ-rennes.fr>
 * @author Ines Gaetan NOUBI-SI KUISSEU
 *         <ines-gaetan.noubi@etudiant.univ-rennes.fr>
 * @version 5.0
 * @since 2023-09-23
 * 
 *        Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * this devient identique à image2.
	 *
	 * @param image2 image à copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		assert !image2.isEmpty() : "image2 ne doit pas être vide";
		assert !image2.isEmpty() : "this ne doit pas être identique à image2";
		Iterator<Node> it1 = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it1.clear();
		this.affectAux(it1, it2);
	}

	/**
	 * Fait la récursivité
	 * 
	 * @param it1
	 * @param it2
	 */
	private void affectAux(Iterator<Node> it1, Iterator<Node> it2) {
		if (!it2.isEmpty()) {
			it1.addValue(it2.getValue());
			it1.goLeft();
			it2.goLeft();
			affectAux(it1, it2);
			it1.goUp();
			it2.goUp();
			it1.goRight();
			it2.goRight();
			affectAux(it1, it2);
			it1.goUp();
			it2.goUp();
		}
	}

	/**
	 * this devient rotation de image2 à 180 degrés.
	 *
	 * @param image2 image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {

	}

	/**
	 * this devient inverse vidéo de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		assert !this.isEmpty() : "this ne doit pas être vide";
		Iterator<Node> it = this.iterator();
		this.videoInverseAux(it);
	}

	private void videoInverseAux(Iterator<Node> it) {
		if (it.getValue().state == 0) {
			it.setValue(Node.valueOf(1));
		} else if (it.getValue().state == 1) {
			it.setValue(Node.valueOf(0));
		} else {
			it.goLeft();
			this.videoInverseAux(it);

			it.goUp();
			it.goRight();
			this.videoInverseAux(it);

			it.goUp();
		}
	}

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient quart supérieur gauche de image2.
	 *
	 * @param image2 image à agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this devient
	 * éteint.
	 * 
	 * @param image2 image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient l'in de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		assert !image1.isEmpty() && !image2.isEmpty() : "image1 et image2 ne doivent pas toutes être nulles";

		Iterator<Node> it = this.iterator();
		it.clear();
		if (image1.isEmpty() || image2.isEmpty()) {
			return;
		}

		Iterator<Node> it1 = image1.iterator();
		Iterator<Node> it2 = image2.iterator();
		this.intersectionAux(it, it1, it2);
	}

	private void intersectionAux(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
		Node node1 = it1.getValue();
		Node node2 = it2.getValue();
		if (node1.state == 2 && node2.state == 2) {
			it.addValue(Node.valueOf(2));
			it.goLeft();
			it1.goLeft();
			it2.goLeft();
			intersectionAux(it, it1, it2);
		} else if (node1.state == 0 || node2.state == 0) {
			if (this.isEmpty()) {
				it.addValue(Node.valueOf(0));
				intersectionAux(it, it1, it2);
			} else {
				it.addValue(Node.valueOf(0));
				this.parcoursInfixeIntersection(it, it1, it2);
			}
		} else if (node1.state == 1 && node2.state == 2) {
			this.affectAux(it, it2);
			this.parcoursInfixeIntersection(it, it1, it2);
		} else if (node1.state == 2 && node2.state == 1) {
			this.affectAux(it, it1);
			this.parcoursInfixeIntersection(it, it1, it2);
		} else if (node1.state == 1 && node2.state == 1) {
			if (this.isEmpty()) {
				it.addValue(Node.valueOf(1));
				intersectionAux(it, it1, it2);
			} else {
				it.addValue(Node.valueOf(1));
				this.parcoursInfixeIntersection(it, it1, it2);
			}
		} // Ce block traite le dernier cas possible
	}

	private void parcoursInfixeIntersection(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
		it.goUp();
		it1.goUp();
		it2.goUp();

		it.goRight();
		it1.goRight();
		it2.goRight();
		this.intersectionAux(it, it1, it2);

		it.goUp();
		it1.goUp();
		it2.goUp();
	}

	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumés dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		Iterator<Node> it = this.iterator();
		return testDiagonalAux(it);
	}

	private boolean testDiagonalAux(Iterator<Node> it) {
		if (it.getValue().state != 2) {
			return it.getValue().state == 1;
		}
		boolean allumeG = false;
		it.goLeft();
		if (it.getValue().state == 2) {
			it.goLeft();
			allumeG = testDiagonalAux(it);
			it.goUp();
		} else {
			allumeG = it.getValue().state == 1;
		}
		it.goUp();
		if (allumeG) {
			boolean allumeD = false;
			it.goRight();
			if (it.getValue().state == 2) {
				it.goRight();
				allumeD = testDiagonalAux(it);
				it.goUp();
			} else {
				allumeD = it.getValue().state == 1;
			}
			it.goUp();
			return allumeD;
		}
		return false;
	}

	/**
	 * @param x abscisse du point
	 * @param y ordonnée du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumé dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		assert !this.isEmpty() : "This ne doit pas être vide";

		int yMax = 256;
		int xMax = 256;
		int count = 0;
		Iterator<Node> it = this.iterator();

		while (it.getValue().state == 2) {
			if (count % 2 == 0) {
				yMax = yMax / 2;
				if (y < yMax) {
					it.goLeft();
				} else {
					it.goRight();
				}
			} else {
				xMax = xMax / 2;
				if (x < xMax) {
					it.goLeft();
				} else {
					it.goRight();
				}
			}
			count++;
		}
		return (it.getValue().state == 1);
	}

	/**
	 * @param x1 abscisse du premier point
	 * @param y1 ordonnée du premier point
	 * @param x2 abscisse du deuxième point
	 * @param y2 ordonnée du deuxième point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par la
	 *         même feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		assert !this.isEmpty() : "This ne doit pas être vide";

		int yMax = 256;
		int xMax = 256;
		int count = 0;
		Iterator<Node> it = this.iterator();

		while (it.getValue().state == 2) {
			if (count % 2 == 0) {
				yMax = yMax / 2;
				if (y1 < yMax && y2 < yMax) {
					it.goLeft();
				} else if (y1 >= yMax && y2 >= yMax) {
					it.goRight();
				} else {
					return false;
				}
			} else {
				xMax = xMax / 2;
				if (x1 < xMax && x2 < xMax) {
					it.goLeft();
				} else if (x1 >= xMax && x2 >= xMax) {
					it.goRight();
				} else {
					return false;
				}
			}
			count++;
		}
		return true;
	}

	/**
	 * @param image2 autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés false
	 *         sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction a ecrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}
}