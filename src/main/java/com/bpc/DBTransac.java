package com.bpc;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Logger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
/**
 * Cette classe s'occupe de tout ce qui a rapport é la communication avec la
 * base de données via JDBC
 *
 * @see GespersonnelEvennements
 * @see ActionServices
 * @see GraphicalUserInterface
 * @see InputDialog
 * @see ResultsModel
 */
public class DBTransac {

    String nom;
    String prenom; 
    String adresse; 
    String grade;
    String responsable; 
    String hobby;
    String moisNaiss; 
    String moisEmb;
    int insee;
    int inseePere; 
    int jNaiss;
    int jEmb;
    int aNaiss; 
    int aEmb;
    ActionServices tr = new ActionServices();
    GraphicalUserInterface g1;
    InputDialog resDialog;
    Connection con;
    Statement stmnt;
    PreparedStatement pstmnt;
    
    PropertiesConfiguration config = null;  
	Logger logger = Logger.getLogger("testing");
    private static final String  SQLSTATE = "SQLState : ";
    private static final String  MESSAGE = "Message : ";
    private static final String  SUCCES = "BPC - SUCCES";
    private static final String  ECHEC = "BPC - ECHEC";
    private static final String  DATE = "to_char(date_naissance,'dd'),";
    private static final String  YEAR =  "to_char(date_naissance,'YYYY'),";
    private static final String  MONTH = "to_number(to_char(date_naissance,'mm'))";
    private static final String  OR = "Operation Reussie!";
    private static final String  ONR = "Operation NON Reussie!";
    private static final String  II = "Insee Inexistant";
    private static final String  IPI = "Insee Pere Inexistant";
    /**
     * La construction d'un objet GespersonnelJdbc s'accompagne du chargement du
     * driver et de l'ouverture de la connexion
     */
    public DBTransac() {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/bpc", "adm", config.getString("pwd"));
        } catch (SQLException sqle) {
            logger.severe(sqle.getMessage());
        }
        try {
        	config = new PropertiesConfiguration("/database.properties");  
        } 
        catch (ConfigurationException ex) 
        {
            logger.info("printStackTrace: " + ex);
        }

    }

//**** DEBUT  MODULE  EMPLOYE ******************
//*****Vérification de l'existence du #insee*****
    /**
     * Teste l'existence de ce #INSEE (pére) dans la base
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @return test Une variable de type booléenne
     */
    public boolean rechInseePere(GraphicalUserInterface g2) {
        g1 = g2;
        boolean test = false;
        int inseePere1 = 0;
  
        try {
            inseePere1 = Integer.valueOf(g1.inseePereTexte.getText());
            stmnt = con.createStatement();
            String query = "select insee from employe where insee=?;";
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
            	ps = con.prepareStatement(query);
            	ps.setInt(1, inseePere1);  	
            } finally {
 
            	if (ps != null) {
            		ps.close();
            	}
            }
            try {
            	rs = ps.executeQuery();
            } finally { 
	        	if (rs != null) {
	        		rs.close();
	        	}
	        }
            
            if (rs.next()) {
                return true;
            }
        } catch (SQLException sqle) {
            logger.severe(sqle.getMessage());
        }
        return test;
    }

    /**
     * Teste l'existence de ce #INSEE (employé) dans la base
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @return test Une variable de type booléenne
     */
    public boolean rechInseeEmp(GraphicalUserInterface g2) {
        g1 = g2;
        boolean test = false;
        int insee1 = 0;
       
        try {
            insee = Integer.valueOf(g1.inseeTexte.getText());
            stmnt = con.createStatement();
            String query = "select insee from employe where insee=?;";
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
            	ps = con.prepareStatement(query);
            	ps.setInt(1, insee1);  	
            } finally {
 
            	if (ps != null) {
            		ps.close();
            	}
            }
            try {
            	rs = ps.executeQuery();
            } finally { 
	        	if (rs != null) {
	        		rs.close();
	        	}
	        }
            if (rs.next()) {
                test = true;
            }
        } catch (SQLException sqle) {
            logger.severe(sqle.getMessage());
        }
        return test;
    }

    /**
     * Teste l'existence de ce #INSEE (enfant) dans la base
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @return test Une variable de type booléenne
     */
    public boolean rechInseeEnf(GraphicalUserInterface g2) {
        g1 = g2;
        boolean test = false;
        try {
            int insee1 = Integer.parseInt(g1.inseeTexte.getText());
            stmnt = con.createStatement();
            ResultSet rs = null;
            try {
            	rs = stmnt.executeQuery("select insee from enfant where insee=" + insee1);
            }finally {
            	if(rs != null) {
            		rs.close();
            	}
            }

            if (rs.next()) {
                test = true;
            }
        } catch (SQLException sqle) {
        	logger.severe(sqle.getMessage());
        }
        return test;
    }

//*****fin de vérification insee*****
    /**
     * Cette méthode permet l'affichage de tous les employés de la base quand
     * l'utilisateur choisit Afficher - Tous dans le menu principal
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @see ResultsModel
     */
    public void afficherEmployes(GraphicalUserInterface g2) {
        g1 = g2;
        String requete = "select * from employe";
        JTextArea status = new JTextArea(1, 10);
        JLabel titreShowAll = new JLabel("D O S S I E R  --  E M P L O Y E S  --  BPC");

        try {

            stmnt = con.createStatement();

            ResultsModel rmodel = new ResultsModel(); // Creation du modéle
            rmodel.setResultSet(stmnt.executeQuery(requete));
            JTable table = new JTable(rmodel);            // Creation de la table é partir du modéle

            g1.panelShowAll.add(titreShowAll, BorderLayout.NORTH);

            JScrollPane sPane = new JScrollPane(table);        // Creation du scrollpane pour la table
            g1.panelShowAll.add(sPane, BorderLayout.CENTER);

            status.setLineWrap(true);
            status.setWrapStyleWord(true);
            g1.panelShowAll.add(status, BorderLayout.SOUTH);

            status.setText("BPC compte  " + rmodel.getRowCount() + " employés.");
        } catch (SQLException sqle) {
            logger.severe(SQLSTATE + sqle.getSQLState());
            logger.severe(config.getString("Message") + sqle.getMessage());
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(g1.panelCentre, "Probléme Grave \n Relancer le programme", "Erreur Interne", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Cette méthode permet l'affichage de tous les employés de la base engagés
     * la meme année
     *
     * @param g2 Objet de type GraphicalUserInterface
     * @param resDialog
     * @see ResultsModel
     */
    public void afficherEmployesAnneeIdem(GraphicalUserInterface g2, InputDialog resDialog) {
        g1 = g2;

        String requete = "select * from employe where to_char(date_embauche,'YYYY')=" + resDialog.getValidatedText();
        JTextArea status = new JTextArea(1, 10);
        JLabel titreMemeAnnee = new JLabel("E M P L O Y E (S)  engagé(s) cette meme année --  BPC");

        try {
            g1.cl.show(g1.panelCard0, "4");

            stmnt = con.createStatement();

            ResultsModel rmodel = new ResultsModel(); // Creation du modéle
            rmodel.setResultSet(stmnt.executeQuery(requete));
            JTable tableMemeAnnee = new JTable(rmodel);            // Creation de la table é partir du modéle

            g1.panelShowMemeAnnee.add(titreMemeAnnee, BorderLayout.NORTH);

            JScrollPane memeAnneePane = new JScrollPane(tableMemeAnnee);        // Creation du scrollpane pour la table
            g1.panelShowMemeAnnee.add(memeAnneePane, BorderLayout.CENTER);

            status.setLineWrap(true);
            status.setWrapStyleWord(true);
            g1.panelShowMemeAnnee.add(status, BorderLayout.SOUTH);

            status.setText("Il y " + rmodel.getRowCount() + " employé(s) engagé(s) cette année-lé.");
        } catch (SQLException sqle) {
            logger.severe(config.getString("SQLState") + sqle.getSQLState());
            logger.severe(MESSAGE + sqle.getMessage());
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(g1.panelCentre, "Probléme Grave \n Relancer le programme", "Erreur Interne", JOptionPane.ERROR_MESSAGE);
        }

    }

    void function(GraphicalUserInterface g2) {
    	 g1 = g2;
         insee = Integer.valueOf(g1.inseeTexte.getText());
         nom = g1.nom.getText();
         prenom = g1.prenom.getText();
         adresse = g1.adresse.getText();
         grade = g1.grade.getText();
         responsable = g1.responsable.getText();
         moisNaiss = g1.moisNaiss.getSelectedItem().toString();
         moisEmb = g1.moisEmb.getSelectedItem().toString();
         jNaiss = Integer.valueOf(g1.jNaiss.getText());
         jEmb = Integer.valueOf(g1.jEmb.getText());
         aNaiss = Integer.valueOf(g1.aNaiss.getText());
         aEmb = Integer.parseInt(g1.aEmb.getText());
    }
    /**
     * Cette méthode se charge de l'insertion d'un nouvel employé
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void ajouterEmploye(GraphicalUserInterface g2) {
       function(g2);
        String ajoutEmp = "insert into employe values(?,?,?,?,?,?,?,?)";

        try {
            pstmnt = con.prepareStatement(ajoutEmp);

            pstmnt.setInt(1, insee);
            pstmnt.setString(2, nom);
            pstmnt.setString(3, prenom);
            pstmnt.setString(4, adresse);
            pstmnt.setString(5, grade);
            pstmnt.setString(6, responsable);
            pstmnt.setString(7, jNaiss + "-" + moisNaiss + "-" + aNaiss);
            pstmnt.setString(8, jEmb + "-" + moisEmb + "-" + aEmb);
            if (insee != 0) {
                int n = pstmnt.executeUpdate();
                //Affichage d'un message si enregistrement effectué avec succés
                if (n != 0) {
                    JOptionPane.showMessageDialog(g1.panelCentre, OR, SUCCES, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(g1.panelCentre, ONR, ECHEC, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, "Tapez un # insee\n non nul", ECHEC, JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(g1.panelCentre, "Opération NON Réussie!", ECHEC, JOptionPane.ERROR_MESSAGE);
            logger.severe(SQLSTATE + sqle.getSQLState());
            logger.severe(MESSAGE + sqle.getMessage());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(g1.panelCentre, "Probléme de format \n de nombre", ECHEC, JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Cette méthode se charge de la suppression d'un employé
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void supprimerEmploye(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            if (rechInseeEmp(g1)) //***  vérification de l'existence du #insee
            {
                insee = Integer.valueOf(g1.inseeTexte.getText());

                String effaceEmp = "delete from employe where insee=?";

                pstmnt = con.prepareStatement(effaceEmp);

                pstmnt.setInt(1, insee);

                int n = pstmnt.executeUpdate();

                if (n != 0) {
                    JOptionPane.showMessageDialog(g1.panelCentre, OR, SUCCES, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(g1.panelCentre, ONR, ECHEC, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, II, ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe(SQLSTATE + sqle.getSQLState());
            logger.severe(MESSAGE + sqle.getMessage());
        }
    }

    ResultSet execute(String requete) throws SQLException {
    	ResultSet rs = null;
    	 try {
         	rs = stmnt.executeQuery(requete);
         } finally {
         	if (rs != null) {
         		rs.close();
         	}
         }
    	 return rs;
    }
    /**
     * Suite é la demande de modification d'un employé,cette méthode affiche les
     * informations concernant l'enregistrement é modifier
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherAvantModifEmploye(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            if (rechInseeEmp(g1)) //si #insee Emp existe
            {
                insee = Integer.valueOf(g1.inseeTexte.getText());
                String requete = "select nom,prenom,adresse,grade,responsable,"
                        + "to_char(date_naissance,'dd'),to_char(date_embauche,'dd'),"
                        + "to_char(date_naissance,'YYYY'),to_char(date_embauche,'YYYY'),"
                        + "to_number(to_char(date_naissance,'mm')),to_number(to_char(date_embauche,'mm'))"
                        + "from employe where insee=" + insee;
                stmnt = con.createStatement();
                ResultSet rs = execute(requete);
                
                while (rs.next()) {
                    g1.nom.setText(rs.getString(1));
                    g1.prenom.setText(rs.getString(2));
                    g1.adresse.setText(rs.getString(3));
                    g1.grade.setText(rs.getString(4));
                    g1.responsable.setText(rs.getString(5));
                    g1.jNaiss.setText(rs.getString(6));
                    g1.jEmb.setText(rs.getString(7));
                    g1.aNaiss.setText(rs.getString(8));
                    g1.aEmb.setText(rs.getString(9));
                    g1.moisNaiss.setSelectedIndex(rs.getInt(10) - 1);
                    g1.moisEmb.setSelectedIndex(rs.getInt(11) - 1);
                }
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, II, ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe(sqle.getMessage());
        }
        g1.valider.setEnabled(true);
    }

    /**
     * Cette méthode se charge de la modification de l'entregistrement
     * correspondant é un employé
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void modifierEmploye(GraphicalUserInterface g2) {
        function(g2);

        String modifEmploye = "update employe set nom=?,prenom=?,adresse=?,"
                + "grade=?,responsable=?,date_naissance=?,date_embauche=?"
                + "where insee=?";
        try {
            pstmnt = con.prepareStatement(modifEmploye);

            pstmnt.setString(1, nom);
            pstmnt.setString(2, prenom);
            pstmnt.setString(3, adresse);
            pstmnt.setString(4, grade);
            pstmnt.setString(5, responsable);
            pstmnt.setString(6, jNaiss + "-" + moisNaiss + "-" + aNaiss);
            pstmnt.setString(7, jEmb + "-" + moisEmb + "-" + aEmb);
            pstmnt.setInt(8, insee);

            int n = pstmnt.executeUpdate();
            if (n != 0) {
                JOptionPane.showMessageDialog(g1.panelCentre, OR, SUCCES, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, ONR, ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe(SQLSTATE + sqle.getSQLState());
            logger.severe(MESSAGE + sqle.getMessage());
        }
    }

    /**
     * Cette méthode récupére le #INSEE tapé dans la fenetre de dialogue et la
     * place dans la zone de texte correspondante
     *
     * @param g2 un objet de type GraphicalUserInterface
     * @param resDialog un objet de type InputDialog
     * @see InputDialog
     */
    public void afficherEmployeInsee(GraphicalUserInterface g2, InputDialog resDialog) {
        g1 = g2;
        g1.inseeTexte.setText(resDialog.getValidatedText());

    }

//********FIN  MODULE   EMPLOYE***********
//******** DEBUT MODULE ENFANT *********** 
    /**
     * Cette méthode permet d'insérer les informations concernant un enfant dans
     * la base
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void ajouterEnfant(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            insee = Integer.valueOf(g1.inseeTexte.getText());
            inseePere = Integer.parseInt(g1.inseePereTexte.getText());
            nom = g1.nom.getText();
            prenom = g1.prenom.getText();
            moisNaiss = g1.moisNaiss.getSelectedItem().toString();
            jNaiss = Integer.valueOf(g1.jNaiss.getText());
            aNaiss = Integer.valueOf(g1.aNaiss.getText());
            hobby = g1.hobby.getText();

            String ajoutEnf = "insert into enfant values(?,?,?,?,?,?)";

            pstmnt = con.prepareStatement(ajoutEnf);

            pstmnt.setInt(1, insee);
            pstmnt.setInt(2, inseePere);
            pstmnt.setString(3, nom);
            pstmnt.setString(4, prenom);
            pstmnt.setString(5, jNaiss + "-" + moisNaiss + "-" + aNaiss); //artifice pour etre "compliant" au format "date" d'Oracle
            pstmnt.setString(6, hobby);

            //Vérification de l'existence du # insee du pére dans la base avant toute opération
            if (rechInseePere(g1)) //si #insee Pere existe
            {
                int n = pstmnt.executeUpdate();
                if (n != 0) {
                    JOptionPane.showMessageDialog(g1.panelCentre, OR, SUCCES, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(g1.panelCentre, "Opération NON Réussie \n Vérifiez #insee", ECHEC, JOptionPane.ERROR_MESSAGE);
                }
            } else // si #insee Pere n'existe pas
            {
                JOptionPane.showMessageDialog(g1.panelCentre, IPI, ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe(SQLSTATE + sqle.getSQLState());
            logger.severe(MESSAGE + sqle.getMessage());
        }
    }

    /**
     * Cette méthode permet de supprimer un enregistrement relatif é un enfant
     * dans la base
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void supprimerEnfant(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            insee = Integer.valueOf(g1.inseeTexte.getText());

            String effaceEnf = "delete from enfant where insee=?";

            pstmnt = con.prepareStatement(effaceEnf);

            pstmnt.setInt(1, insee);
            int n = pstmnt.executeUpdate();

            if (rechInseePere(g1)) //si #insee Pere existe
            {
                if (n != 0) {
                    JOptionPane.showMessageDialog(g1.panelCentre, "Opération Réussie!", SUCCES, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(g1.panelCentre, "Opération NON Réussie \n Vérifiez #insee", ECHEC, JOptionPane.ERROR_MESSAGE);
                }
            } else // si #insee Pere n'existe pas
            {
                JOptionPane.showMessageDialog(g1.panelCentre, "Insee Père Inexistant", ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe(SQLSTATE + sqle.getSQLState());
            logger.severe(MESSAGE + sqle.getMessage());
        }
    }
    
    void getInfo(int i,ResultSet rs) {
    	 try {
			while (rs.next()) {
				if (i==1) {
					g1.inseeTexte.setText(String.valueOf(rs.getInt(1)));
				}
			     g1.nom.setText(rs.getString(1+i));
			     g1.prenom.setText(rs.getString(2+i));
			     g1.hobby.setText(rs.getString(3+i));
			     g1.jNaiss.setText(rs.getString(4+i));
			     g1.aNaiss.setText(rs.getString(5+i));
			     g1.moisNaiss.setSelectedIndex(rs.getInt(6+i) - 1);
			 }
		} catch (SQLException e) {
			
			logger.severe(e.toString());
		}
    }

    /**
     * Suite é la demande de modification d'un enfant,cette méthode vérifie
     * d'abord que le #INSEE du pére est dans la base puis affiche les
     * informations concernant l'enregistrement é modifier
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void afficherAvantModifEnfant(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            if (rechInseePere(g1)) //si #insee Père existe
            {
                g1.inseePereTexte.setEnabled(false);
                insee = Integer.valueOf(g1.inseeTexte.getText());
                String requete = "select nom,prenom,hobby,"
                        + DATE
                        + YEAR
                        + MONTH
                        + "from enfant where insee=" + insee;
                stmnt = con.createStatement();
                ResultSet rs = execute(requete);
               getInfo(0,rs);
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, "Vérifiez les #insee \n Parent et/ou Enfant  svp!", ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe(sqle.getMessage());
        }
        g1.valider.setEnabled(true);
    }

    /**
     * Cette méthode permet de rechercher si le #INSEE de l'enfant existe
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    void rechercherEnfantInseePropre(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            if (rechInseeEnf(g1)) {
                insee = Integer.valueOf(g1.inseeTexte.getText());
                String requete = "select inseepere,nom,prenom,hobby,"
                        + DATE
                        + YEAR
                        + MONTH
                        + "from enfant where insee=" + insee;

                stmnt = con.createStatement();
                ResultSet rs =execute(requete);
                
                while (rs.next()) {
                    g1.inseePereTexte.setText(String.valueOf(rs.getInt(1)));
                    g1.nom.setText(rs.getString(2));
                    g1.prenom.setText(rs.getString(3));
                    g1.hobby.setText(rs.getString(4));
                    g1.jNaiss.setText(rs.getString(5));
                    g1.aNaiss.setText(rs.getString(6));
                    g1.moisNaiss.setSelectedIndex(rs.getInt(7) - 1);
                }
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, II, ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe(sqle.getMessage());
        }
        g1.valider.setEnabled(false);

    }

    /**
     * Cette méthode permet de rechercher si le #INSEE du pére de l'enfant
     * existe
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void rechercherEnfantInseePere(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            if (rechInseePere(g1)) //si #insee Pére existe
            {
                inseePere = Integer.valueOf(g1.inseePereTexte.getText());
                String requete = "select insee,nom,prenom,hobby,"
                        + DATE
                        + YEAR
                        + MONTH
                        + "from enfant where inseepere=" + inseePere;

                stmnt = con.createStatement();
                ResultSet rs = execute(requete);
                getInfo(1,rs);
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, "Vérifiez les #insee \n père et/ou enfant  svp!", ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {

            logger.severe(sqle.getMessage());
        }
        g1.valider.setEnabled(false);

    }

    /**
     * Cette méthode permet de modifier les informations au sujet d'un enfant
     * contenues dans la base
     *
     * @param g2 Objet de type GraphicalUserInterface
     */
    public void modifierEnfant(GraphicalUserInterface g2) {
        g1 = g2;
        try {
            insee = Integer.valueOf(g1.inseeTexte.getText());
            inseePere = Integer.valueOf(g1.inseePereTexte.getText());
            nom = g1.nom.getText();
            prenom = g1.prenom.getText();
            moisNaiss = g1.moisNaiss.getSelectedItem().toString();
            jNaiss = Integer.valueOf(g1.jNaiss.getText());
            aNaiss = Integer.valueOf(g1.aNaiss.getText());
            hobby = g1.hobby.getText();

            String modifEnfant = "update enfant set nom=?,prenom=?,date_naissance=?,hobby=?"
                    + "where insee=?";

            pstmnt = con.prepareStatement(modifEnfant);

            pstmnt.setString(1, nom);
            pstmnt.setString(2, prenom);
            pstmnt.setString(3, adresse);
            pstmnt.setString(4, jNaiss + "-" + moisNaiss + "-" + aNaiss);
            pstmnt.setInt(5, insee);

            if (rechInseePere(g1)) //si #insee Pere existe
            {
                int n = pstmnt.executeUpdate();
                if (n != 0) {
                    JOptionPane.showMessageDialog(g1.panelCentre, "Opération Réussie!", SUCCES, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(g1.panelCentre, "Opération NON Réussie", ECHEC, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(g1.panelCentre, "Insee Père Inexistant", ECHEC, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException sqle) {
            logger.severe("SQLState: " + sqle.getSQLState());
            logger.severe(MESSAGE + sqle.getMessage());
        }
    }

    /**
     * Cette méthode ferme la connexion
     */
    void fermerConnexion() {

        if (con != null) {
            try {
                con.close();
                if (stmnt != null) {
                    stmnt.close();
                }
                if (pstmnt != null) {
                    pstmnt.close();
                }
                con = null;
            } catch (SQLException ex) {
                logger.info("SQLState: " + ex.getSQLState());
                logger.info(MESSAGE + ex.getMessage());
            }
        }
    }

}
