package com.bpc;

import java.awt.event.*;
import java.io.Serializable;


/**
 * La classe EventsManager fournit des champs et des méthodes nécessaires pour
 * tester le modéle de gestion d'événement du JDK 1.1 ou JDK 1.2
 *
 * Les évenements testés ici sont de type action.
 *
 * Une classe écouteur interne EventsManager est implémentée pour écouter et
 * traiter les évéments "ActionEvent".
 */
public class EventsManager implements ActionListener,Serializable {
	private static final long serialVersionUID = 1L;

	private static int codeEv;
	// Ce code est attribué é chaque événnement
	// lié au clic sur VALIDER ou RECHERCHER

	ActionServices tr = new ActionServices();
	// Création d'un objet Traitements pour accéder aux méthodes de cette classe

	private GraphicalUserInterface g1;
	
	public static int getcodeEv() {
		return codeEv;
	}
	public static void setcodeEv(int ev) {
		codeEv=ev; 
	}
	/**
	 * Cette méthode permet l'enregistrement des écouteurs via AddActionListener
	 * auquel on passe comme argument l'objet courant "this"
	 *
	 * @param g2 Un objet de type GraphicalUserInterface
	 */

	
	public void enregistrerListener(GraphicalUserInterface g2) {
		g1 = g2;

		g1.employe1.addActionListener(this);
		g1.employe2.addActionListener(this);
		g1.employe3.addActionListener(this);
		g1.employe4.addActionListener(this);
		g1.enfant1.addActionListener(this);
		g1.enfant2.addActionListener(this);
		g1.enfant3.addActionListener(this);
		g1.enfant4.addActionListener(this);
		g1.tous.addActionListener(this);
		g1.insee.addActionListener(this);
		g1.memeAnnee.addActionListener(this);
		g1.viaInseePere.addActionListener(this);
		g1.viaInseePropre.addActionListener(this);
		g1.quitter.addActionListener(this);
		g1.rechercher.addActionListener(this);
		g1.effacer.addActionListener(this);
		g1.valider.addActionListener(this);
		g1.annuler.addActionListener(this);

	}

	/**
	 * Cette méthode effectue le dispatching des actions en fonction du résultat de
	 * la méthode getSource() attaché à l'objet de type ActionEvent
	 *
	 * @param e
	 */

	
	void actionafficher(Object o) {
		if (o == g1.employe1) // insérer employé
		{
			tr.afficherEmployeAjouter(g1);
			setcodeEv(1);
		}

		else if (o == g1.enfant1) // insérer enfant
		{
			tr.afficherEnfantAjouter(g1);
			setcodeEv(2);
		}

		else if (o == g1.employe2) // supprimer employé
		{
			tr.afficherEmployeSupprimer(g1);
			setcodeEv(3);
		}

		else if (o == g1.enfant2) // supprimer enfant
		{
			tr.afficherEnfantSupprimer(g1);
			setcodeEv(4);
		}

		else if (o == g1.employe3) // modifier employé
		{
			tr.afficherEmployeModifier(g1);
			setcodeEv(5);
		}

		else if (o == g1.enfant3) // modifier enfant
		{
			tr.afficherEnfantModifier(g1);
			setcodeEv(6);
		}
	}
	void actionlancer(Object o) {
		if (o == g1.tous) // afficher tous les employés
		{
			tr.lancerAfficherEmployes(g1);
		}

		else if (o == g1.insee) // invite pour taper le #insee de l'employé
		{
			tr.lancerDialogInseeEmp(g1);
		}

		else if (o == g1.memeAnnee) {
			tr.lancerDialogEmpAnnee(g1);
		}

		else if (o == g1.viaInseePere) {
			tr.lancerDialogInseePere(g1);
		}

		else if (o == g1.viaInseePropre) {
			tr.lancerDialogInseeEnf(g1);
		}

		else if (o == g1.rechercher) {
			tr.lancerRechercher(g1);
		}

		else if (o == g1.valider) {
			tr.lancerValider(g1);
		}

		
	}
	
	void actionother(Object o){
		if (o == g1.effacer) {
			tr.resetChamps(g1);
		}

		// les évennements ci-dessous ne font pas appel
		// à l'"objet de la classe de traitements" tr
		// =============================================================
		else if (o == g1.annuler) {
			g1.cl.show(g1.panelCard0, "1");
		}

		else if (o == g1.quitter) {
			System.exit(0);
		}

		// =============================================================
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object o=e.getSource();
		
		actionafficher(o);
		
		actionlancer(o);
		
		actionother(o);
		
	}

}
