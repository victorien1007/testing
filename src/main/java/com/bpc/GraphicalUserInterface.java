package com.bpc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

/**
 * Cette classe construit l'interface en utilisant le package SWING. La frame de
 * base est GraphicalUserInterface contenant un JMenuBar "menubar", 4 JMenu, 14
 * éléments de menu, 7 panels, 2 Combo Lists pour les mois de naissance et
 * d'embauche, 4 boutons et 12 zones de texte.
 */
public class GraphicalUserInterface implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EventsManager ev = new EventsManager();

    //A part le JMenuBar les autres variables sont en "friendly"
	JFrame jframe=new JFrame();
	
    JMenu menuAjouter;
    JMenu menuSupprimer; 
    JMenu menuModifier;
    JMenu menuAfficher;

	JLabel labelNom;
	JLabel labelPrenom;
	JLabel labelGrade;
	JLabel labelAdresse;
	JLabel labelResponsable;
	JLabel labelInsee;
	JLabel labelInseePere;
	JLabel labelDateNaiss;
	JLabel labelDateEmb;
	JLabel labelHobby;

	JPanel panelCard0;
	JPanel panelCard1;
	JPanel panelShowMemeAnnee;
	JPanel panelShowAll;
	JPanel panel;
	JPanel panelNord;
	JPanel panelSud;
	JPanel panelCentre;
    String[] mois = {"jan", "fev", "mar", "avr", "mai", "juin", "jul", "aou", "sep", "oct", "nov", "dec"};

    JComboBox moisNaiss;
    JComboBox moisEmb;

	JMenuItem employe1;
	JMenuItem enfant1;
	JMenuItem employe2;
	JMenuItem enfant2;
	JMenuItem employe3;
	JMenuItem enfant3;
	JMenuItem employe4;
	JMenuItem enfant4;
	JMenuItem tous;
	JMenuItem insee;
	JMenuItem memeAnnee;
	JMenuItem viaInseePere;
	JMenuItem viaInseePropre;
	JMenuItem quitter;

	JTextField nom;
	JTextField prenom;
	JTextField grade;
	JTextField adresse;
	JTextField responsable;
	JTextField inseeTexte;
	JTextField inseePereTexte;
	JTextField hobby;
	JTextField jNaiss;
	JTextField aNaiss;
	JTextField jEmb;
	JTextField aEmb;

    JButton rechercher; 
    JButton effacer; 
    JButton valider;
    JButton annuler;

    CardLayout cl = new CardLayout();
    private static final String  EF = "Enfant";
    private static final String  EPLY = "Employé";
    /**
     * Cette méthode initialise les composants
     */
    public void lancer() {

        jframe.setTitle("Gestion du Personnel de BPC");

        JMenuBar menuBar = new JMenuBar();
        jframe.setJMenuBar(menuBar);

        menuAjouter = new JMenu("Ajouter");
        menuSupprimer = new JMenu("Supprimer");
        menuModifier = new JMenu("Modifier");
        menuAfficher = new JMenu("Afficher");

        menuAjouter.setMnemonic(KeyEvent.VK_A);
        menuSupprimer.setMnemonic(KeyEvent.VK_S);
        menuModifier.setMnemonic(KeyEvent.VK_M);
        menuAfficher.setMnemonic(KeyEvent.VK_F);

        menuBar.add(menuAjouter);
        menuBar.add(menuSupprimer);
        menuBar.add(menuModifier);
        menuBar.add(menuAfficher);

        //Raccourcis clavier
        //===========================================
        employe1 = new JMenuItem(EPLY);
        employe1.setMnemonic(KeyEvent.VK_E);
        enfant1 = new JMenuItem(EF);
        enfant1.setMnemonic(KeyEvent.VK_N);
        employe2 = new JMenuItem(EPLY);
        employe2.setMnemonic(KeyEvent.VK_E);
        enfant2 = new JMenuItem(EF);
        enfant2.setMnemonic(KeyEvent.VK_N);
        employe3 = new JMenuItem(EPLY);
        employe3.setMnemonic(KeyEvent.VK_E);
        enfant3 = new JMenuItem(EF);
        enfant3.setMnemonic(KeyEvent.VK_N);
        employe4 = new JMenu(EPLY);
        /*ca passe uniquement parce que JMenuItem
									 sous-classe de JMenu car employe4 déclaré comme JMenu*/
        employe4.setMnemonic(KeyEvent.VK_E);
        enfant4 = new JMenu(EF);
        enfant4.setMnemonic(KeyEvent.VK_N);
        tous = new JMenuItem("Tous");
        tous.setMnemonic(KeyEvent.VK_T);
        insee = new JMenuItem("Recherche par # INSEE");
        insee.setMnemonic(KeyEvent.VK_I);
        memeAnnee = new JMenuItem("Engagés la meme année");
        memeAnnee.setMnemonic(KeyEvent.VK_A);
        viaInseePere = new JMenuItem("Recherche par # INSEE père");
        viaInseePere.setMnemonic(KeyEvent.VK_P);
        viaInseePropre = new JMenuItem("Recherche par # INSEE propre");
        viaInseePere.setMnemonic(KeyEvent.VK_R);
        quitter = new JMenuItem("Quitter");
        quitter.setMnemonic(KeyEvent.VK_Q);
        //===========================================	

        menuAjouter.add(employe1);
        menuAjouter.add(enfant1);
        menuAjouter.addSeparator();
        menuAjouter.add(quitter);
        menuSupprimer.add(employe2);
        menuSupprimer.add(enfant2);
        menuModifier.add(employe3);
        menuModifier.add(enfant3);
        menuAfficher.add(employe4);
        menuAfficher.add(enfant4);
        employe4.add(tous);
        employe4.add(insee);
        employe4.add(memeAnnee);
        enfant4.add(viaInseePere);
        enfant4.add(viaInseePropre);

        // panel pour l'affichage de tous les employés
        panelShowAll = new JPanel();
        //panel pour l'affichage des employes engagés une meme année
        panelShowMemeAnnee = new JPanel();

        panel = new JPanel();
        panelNord = new JPanel();
        panelCentre = new JPanel();
        panelSud = new JPanel();
        panelCard0 = new JPanel();
        panelCard1 = new JPanel();

        //Initialisation des zones de texte et des combos
        nom = new JTextField(10);
        prenom = new JTextField(10);
        grade = new JTextField(10);
        adresse = new JTextField(10);
        responsable = new JTextField(10);
        inseeTexte = new JTextField("0", 13);
        inseePereTexte = new JTextField("0", 13);
        hobby = new JTextField(10);
        jNaiss = new JTextField("jour", 5);
        moisNaiss = new JComboBox(mois);
        aNaiss = new JTextField("année", 6);
        jEmb = new JTextField("jour", 5);
        moisEmb = new JComboBox(mois);
        aEmb = new JTextField("année", 6);

        // Initialisation des labels
        labelNom = new JLabel("Nom    ");
        labelPrenom = new JLabel("Prénom");
        labelGrade = new JLabel("Grade");
        labelAdresse = new JLabel("Adresse");
        labelResponsable = new JLabel("Responsable");
        labelInsee = new JLabel("# INSEE");
        labelInseePere = new JLabel("#INSEE PERE");
        labelDateNaiss = new JLabel("Date de Naissance");
        labelDateEmb = new JLabel("Date Embauche");
        labelHobby = new JLabel("Hobby");

        // Initialisation des boutons
        rechercher = new JButton("Rechercher");
        effacer = new JButton("Réinitialiser");
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");

        /*CardLayout utilisé pour afficher à tour de role
	le panel "gris" (annuler), et le panel formulaire
	ATTENTION!  Un panelCard0 est obligatoire pour le bon
	fonctionnement de ce type de layout.  En fait, il n'est
	pas visible
         */
        jframe.getContentPane().add(panelCard0);

        BorderLayout layoutbase = new BorderLayout(5, 5);
        panelCard0.setLayout(cl);
        panel.setLayout(layoutbase);
        panelShowAll.setLayout(layoutbase);
        panelShowMemeAnnee.setLayout(layoutbase);

        panelCard1.setBackground(Color.gray);

        panelCard0.add("1", panelCard1);
        panelCard0.add("2", panel);
        panelCard0.add("3", panelShowAll);
        panelCard0.add("4", panelShowMemeAnnee);

        panel.add(panelNord, BorderLayout.NORTH);
        panel.add(panelSud, BorderLayout.SOUTH);
        panel.add(panelCentre, BorderLayout.CENTER);

        // Traitement panel Nord //
        panelNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelNord.add(labelInsee);
        panelNord.add(inseeTexte);
        panelNord.add(labelInseePere);
        panelNord.add(inseePereTexte);

        // Traitement Panel Centre | Utilisation d'un GridBagLayout//
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        panelCentre.setLayout(gbl);

        // Attributs globaux de gbc
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.right = 10;
        gbc.insets.left = 10;
        gbc.weightx = 0;
        gbc.weighty = 0;

// Fin Attr. globaux
        panelCentre.add(labelNom);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbl.setConstraints(labelNom, gbc);

        panelCentre.add(nom);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbl.setConstraints(nom, gbc);

        panelCentre.add(labelPrenom);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbl.setConstraints(labelPrenom, gbc);

        panelCentre.add(prenom);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbl.setConstraints(prenom, gbc);

        panelCentre.add(labelAdresse);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbl.setConstraints(labelAdresse, gbc);

        panelCentre.add(adresse);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbl.setConstraints(adresse, gbc);

        panelCentre.add(labelGrade);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbl.setConstraints(labelGrade, gbc);

        panelCentre.add(grade);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbl.setConstraints(grade, gbc);

        panelCentre.add(labelResponsable);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbl.setConstraints(labelResponsable, gbc);

        panelCentre.add(responsable);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbl.setConstraints(responsable, gbc);

        panelCentre.add(labelDateNaiss);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbl.setConstraints(labelDateNaiss, gbc);

        panelCentre.add(jNaiss);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbl.setConstraints(jNaiss, gbc);

        panelCentre.add(moisNaiss);
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbl.setConstraints(moisNaiss, gbc);

        panelCentre.add(aNaiss);
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbl.setConstraints(aNaiss, gbc);

        panelCentre.add(labelHobby);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbl.setConstraints(labelHobby, gbc);

        panelCentre.add(hobby);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbl.setConstraints(hobby, gbc);

        panelCentre.add(labelDateEmb);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbl.setConstraints(labelDateEmb, gbc);

        panelCentre.add(jEmb);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbl.setConstraints(jEmb, gbc);

        panelCentre.add(moisEmb);
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbl.setConstraints(moisEmb, gbc);

        panelCentre.add(aEmb);
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbl.setConstraints(aEmb, gbc);

        //Traitement Panel Sud //
        panelSud.setLayout(new FlowLayout());
        panelSud.add(rechercher);
        panelSud.add(effacer);
        panelSud.add(valider);
        panelSud.add(annuler);
        ev.enregistrerListener(this);

        //gestion du clic dans chaque zone de texte des dates
        //==================================================
        jNaiss.addMouseListener(new Souris1());
        jEmb.addMouseListener(new Souris3());
        aNaiss.addMouseListener(new Souris2());
        aEmb.addMouseListener(new Souris4());
        inseeTexte.addMouseListener(new Souris5());
        inseePereTexte.addMouseListener(new Souris6());
        //==================================================

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        Toolkit theKit = jframe.getToolkit();       // toolkit de la fenetre
        Dimension wndSize = theKit.getScreenSize();  // dimension de l'ecran
        jframe.setBounds(wndSize.width / 4, wndSize.height / 4, wndSize.width / 2, wndSize.height / 2);
        jframe.pack();	//optimisation de l'occupation de l'espace disponible par les composants
    }

    // A chaque zone de texte son objet "évennement"
    //=================================================
    /**
     * Quand l'utilisateur actionne la souris (clic) sur le champ du jour de
     * naissance le contenu de la zone de texte s'efface
     */
    class Souris1 extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            jNaiss.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            jNaiss.setText("");
        }
    }

    /**
     */
    class Souris2 extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            aNaiss.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            aNaiss.setText("");
        }
    }

    /**
     */
    class Souris3 extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            jEmb.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            jEmb.setText("");
        }
    }

    /**
     */
    class Souris4 extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent f) {
            aEmb.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            aEmb.setText("");
        }
    }

    /**
     */
    class Souris5 extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent f) {
            inseeTexte.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            inseeTexte.setText("");
        }
    }

    /**
     */
    class Souris6 extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent f) {
            inseePereTexte.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            inseePereTexte.setText("");
        }
    }

    //================================================
}
