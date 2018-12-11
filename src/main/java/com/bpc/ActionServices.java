package com.bpc;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Cette classe déclenche ou une plusieurs actions suites aux instructions
 * recues de EventsManager
 *
 * @see EventsManager
 */
public class ActionServices implements Serializable{
	private static final long serialVersionUID = 1L;
    private GraphicalUserInterface g1;
    private Frame frame;
    private static HashMap<Integer, ArrayList<Boolean>> map = new HashMap<>();
    	
    	static {
    	
    			ArrayList<Boolean> array1 = new ArrayList<>(Arrays.asList(false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,true,true));
    			
    			ArrayList<Boolean> array2 = new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true));
    			
    			ArrayList<Boolean> array3 = new ArrayList<>(Arrays.asList(false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,true,true));
    			
    			ArrayList<Boolean> array4 = new ArrayList<>(Arrays.asList(true,true,false,false,true,true,true,true,false,false,false,false,true,true,true,false,true,false,false,false,true,true,false,true,true));
    			
    			ArrayList<Boolean> array5 = new ArrayList<>(Arrays.asList(true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true));
    			
    			ArrayList<Boolean> array6 = new ArrayList<>(Arrays.asList(true,true,false,false,true,true,true,true,false,false,false,false,true,true,true,false,true,false,false,false,true,true,true,true,true));
    		
    			map.put(1,array1);
    			map.put(2,array2);
    			map.put(3,array3);
    			map.put(4,array4);
    			map.put(5,array5);
    			map.put(6,array6);
    }

//*************** DEBUT MODULE EMPLOYE *********************//
    		
    		
    public void function(int i, GraphicalUserInterface g2) {
    	
    	ArrayList<Boolean> array = map.get(i);
    	
    	 g1 = g2;
         g1.cl.show(g1.panelCard0, "2");
         g1.labelInseePere.setEnabled(array.get(0));
         g1.inseePereTexte.setEnabled(array.get(1));
         g1.labelAdresse.setEnabled(array.get(2));
         g1.adresse.setEnabled(array.get(3));
         g1.labelNom.setEnabled(array.get(4));
         g1.nom.setEnabled(array.get(5));
         g1.labelPrenom.setEnabled(array.get(6));
         g1.prenom.setEnabled(array.get(7));
         g1.labelGrade.setEnabled(array.get(8));
         g1.grade.setEnabled(array.get(9));
         g1.labelResponsable.setEnabled(array.get(10));
         g1.responsable.setEnabled(array.get(11));
         g1.jNaiss.setEnabled(array.get(12));
         g1.moisNaiss.setEnabled(array.get(13));
         g1.labelDateNaiss.setEnabled(array.get(14));
         g1.labelDateEmb.setEnabled(array.get(15));
         g1.aNaiss.setEnabled(array.get(16));
         g1.jEmb.setEnabled(array.get(17));
         g1.moisEmb.setEnabled(array.get(18));
         g1.aEmb.setEnabled(array.get(19));
         g1.labelHobby.setEnabled(array.get(20));
         g1.hobby.setEnabled(array.get(21));
         g1.rechercher.setEnabled(array.get(22));
         g1.effacer.setEnabled(array.get(23));
         g1.valider.setEnabled(array.get(24));

    }
    /**
     * Grace au CardLayout cette méthode affiche le panel correspondant à
     * l'ajout d'un employé
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherEmployeAjouter(GraphicalUserInterface g2) {
       function(1,g2);
    }

    /**
     * Grace au CardLayout cette méthode affiche le panel correspondant é la
     * suppression d'un employé
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherEmployeSupprimer(GraphicalUserInterface g2) {
    	function(2,g2);
    }

    /**
     * Cette méthode fait appel à une méthode de la classe GespersonnelJdb qui
     * affiche les informations sur un employé avant leur modification
     * éventuelle
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see DBTransac
     */
    public void lancerAfficherAvantModifEmploye(GraphicalUserInterface g2) {
        g1 = g2;
        DBTransac gp11 = new DBTransac();
        gp11.afficherAvantModifEmploye(g1);
        gp11.fermerConnexion();
    }

    /**
     * Grace au CardLayout cette méthode affiche le panel correspondant é la
     * modification des informations sur un employé
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherEmployeModifier(GraphicalUserInterface g2) {
    	function(3,g2);
    }

    /**
     * Cette méthode fait appel à une méthode de la classe DBTransac qui affiche
     * tous les employés engagés une meme année
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see DBTransac
     */
    public void lancerAfficherEmployes(GraphicalUserInterface g2) {
        g1 = g2;
        DBTransac gp10 = new DBTransac();
        g1.cl.show(g1.panelCard0, "3");
        gp10.afficherEmployes(g2);
        gp10.fermerConnexion();
    }
    //************* FIN MODULE EMPLOYE **************//

    //************* DEBUT MODULE ENFANT *************//
    /**
     * Grace au CardLayout cette méthode affiche le panel correspondant à
     * l'ajout d'un enregistrement-enfant
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherEnfantAjouter(GraphicalUserInterface g2) {
    	function(4,g2);
    }

    /**
     * Grace au CardLayout cette méthode affiche le panel correspondant é la
     * suppression d'un enregistrement-enfant
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherEnfantSupprimer(GraphicalUserInterface g2) {
    	function(5,g2);
    }

    /**
     * Cette méthode fait appel é une méthode de la classe DBTransac qui affiche
     * les informations sur un enfant avant leur modification éventuelle
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see DBTransac
     */
    public void lancerAfficherAvantModifEnfant(GraphicalUserInterface g2) {
        g1 = g2;
        DBTransac gp12 = new DBTransac();
        gp12.afficherAvantModifEnfant(g1);
        gp12.fermerConnexion();
    }

    /**
     * Grace au CardLayout cette méthode affiche le panel correspondant é la
     * modification des informations sur un enfant
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherEnfantModifier(GraphicalUserInterface g2) {
        function(6,g2);

    }

//===Fin Module Enfant===//
    /**
     * Cette méthode permet de réinitialiser tous les champs Elle est activée é
     * l'aide du bouton EFFACER
     */
    public void resetChamps(GraphicalUserInterface g2) {
        g1 = g2;
        g1.inseeTexte.setText("0");
        g1.inseePereTexte.setText("0");
        g1.nom.setText("");
        g1.prenom.setText("");
        g1.adresse.setText("");
        g1.grade.setText("");
        g1.responsable.setText("");
        g1.hobby.setText("");
        g1.jNaiss.setText("1");
        g1.aNaiss.setText("1900");
        g1.jEmb.setText("1");
        g1.aEmb.setText("1900");
        g1.moisNaiss.setSelectedItem(String.valueOf(g1.mois[0]));
        g1.moisEmb.setSelectedItem(String.valueOf(g1.mois[0]));
    }

    /**
     * Cette méthode crée une fenetre de dialogue, récupére le texte tapé,
     * affiche un panel en arriére-plan et y place qprés validation le texte
     * récupéré Est activée si l'utilisateur choisit d'afficher les informations
     * sur un employé é partir de son #INSEE
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see InputDialog
     */
    public void lancerDialogInseeEmp(GraphicalUserInterface g2) {
        g1 = g2;
        DBTransac gp = new DBTransac();

        afficherEmployeAjouter(g1);

        InputDialog inputDialog = new InputDialog(frame, "# INSEE", g1);
        inputDialog.pack();

        inputDialog.setVisible(true);

        g1.inseeTexte.setText(inputDialog.getValidatedText());
        gp.afficherAvantModifEmploye(g1);
        g1.valider.setEnabled(false);  // impossibilité de valider le résultat de la recherche!
    }

    /**
     * Cette méthode crée une fenetre de dialogue, récupére le texte tapé,
     * affiche un panel en arriére-plan et y place qprés validation le texte
     * récupéré Est activée si l'utilisateur choisit d'afficher les informations
     * sur un enfant é partir de son #INSEE
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see InputDialog
     */
    public void lancerDialogInseeEnf(GraphicalUserInterface g2) {
        g1 = g2;
        DBTransac gp = new DBTransac();

        afficherEnfantAjouter(g1);

        InputDialog inputDialog = new InputDialog(frame, "# INSEE de l'ENFANT", g1);
        inputDialog.pack();

        inputDialog.setVisible(true);

        g1.inseeTexte.setText(inputDialog.getValidatedText());
        gp.rechercherEnfantInseePropre(g1);

    }

    /**
     * Cette méthode crée une fenetre de dialogue, récupére le texte tapé,
     * affiche un panel en arriére-plan et y place qprés validation le texte
     * récupéré Est activée si l'utilisateur choisit d'afficher les informations
     * sur un enfant é partir du #INSEE du pére
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see InputDialog
     */
    public void lancerDialogInseePere(GraphicalUserInterface g2) {
        g1 = g2;
        DBTransac gp = new DBTransac();

        afficherEnfantAjouter(g1);

        InputDialog inputDialog = new InputDialog(frame, "# INSEE du PERE", g1);
        inputDialog.pack();

  
        inputDialog.setVisible(true);

        g1.inseePereTexte.setText(inputDialog.getValidatedText());
        gp.rechercherEnfantInseePere(g1);
        g1.valider.setEnabled(false);  // impossibilité de valider le résultat de la recherche!

    }

    /**
     * Cette méthode crée une fenetre de dialogue, récupére le texte tapé,
     * affiche un panel en arriére-plan et y place qprés validation le texte
     * récupéré Est activée si l'utilisateur choisit d'afficher tous les
     * employés engagés une meme année
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see InputDialog
     */
    public void lancerDialogEmpAnnee(GraphicalUserInterface g2) {
        g1 = g2;
        DBTransac gp = new DBTransac();

        InputDialog inputDialog = new InputDialog(frame, "", g1);
        inputDialog.pack();

        inputDialog.setVisible(true);
        gp.afficherEmployesAnneeIdem(g1, inputDialog);

        g1.valider.setEnabled(false);  // impossibilité de valider le résultat de la recherche!

    }

    /**
     * Cette méthode de sélection permet d'activer une méthode parmi deux
     * possible quand l'utilisateur clique sur RECHERCHER
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void lancerRechercher(GraphicalUserInterface g2) {
        g1 = g2;

        switch (EventsManager.getcodeEv()) {
            case 6:
                lancerAfficherAvantModifEnfant(g2);
                break;

            case 5:
                lancerAfficherAvantModifEmploye(g2);
                break;

            default:break;
        }
    }

    /**
     * Cette méthode de sélection permet d'activer une méthode parmi six
     * possible quand l'utilisateur clique sur VALIDER
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void lancerValider(GraphicalUserInterface g2) {
        g1 = g2;

        switch (EventsManager.getcodeEv()) {
            case 1:
                DBTransac gp1 = new DBTransac();
                gp1.ajouterEmploye(g2);
                gp1.fermerConnexion();
                resetChamps(g2);
                break;

            case 2:
                DBTransac gp2 = new DBTransac();
                gp2.ajouterEnfant(g2);
                gp2.fermerConnexion();
                resetChamps(g2);
                break;

            case 3:
                DBTransac gp3 = new DBTransac();
                gp3.supprimerEmploye(g2);
                gp3.fermerConnexion();
                resetChamps(g2);
                break;

            case 4:
                DBTransac gp4 = new DBTransac();
                gp4.supprimerEnfant(g2);
                gp4.fermerConnexion();
                resetChamps(g2);
                break;

            case 5:
                DBTransac gp5 = new DBTransac();
                gp5.modifierEmploye(g2);
                gp5.fermerConnexion();
                resetChamps(g2);
                break;

            case 6:
                DBTransac gp6 = new DBTransac();
                gp6.modifierEnfant(g2);
                gp6.fermerConnexion();
                resetChamps(g2);
                break;
                
            default:break;

        }
    }
}
