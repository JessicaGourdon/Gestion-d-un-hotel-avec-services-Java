package chambre;
import client.*;

import java.lang.*;
import java.util.*;
import java.text.*;

public class Chambre {

	// Tableau avec les clientscde la chambre
	public ArrayList<Client> tableauClient = new ArrayList<Client>();
	// Type de chambres possibles
	protected enum Type {SIMPLE, DOUBLE, TRIPLE, DELUXE};
	private Type type;
	// Prix de la chambre
	private double prix;
	private double prixTaxe;
	// Date d'arrivée
	private String arrivée;
	// Date de départ
	private String départ;
	//Nombre de jours dans la chambre
	private int nbjour;
	// Tableau repertoriant les numéros de chambres
	public static ArrayList<Integer> tabNumChambre = new ArrayList<Integer>();
	// Tableau drepertoriant les dates d'arrivée
	public static ArrayList<String> tabDateArrivée = new ArrayList<String>();
	// Tableau repertoriant les dates de départ
	public static ArrayList<String> tabDateDépart = new ArrayList<String>();
	// Numéro de la chambre
	private int numéroChambre = 0;


	// Crée une nouvelle chambre
	public Chambre(ArrayList<Client> tableauClient, String arrivée, String départ) {
		this.tableauClient = tableauClient;
		setArrivée(arrivée);
		setDépart(départ);
		this.nbjour = (int)this.calculNbJours(arrivée, départ);
		// On attribut à chaque client du tableau des tickets restaurants selon le nombre de jours qu'ils restent (2 par jour)
		for(Client client: tableauClient){
			client.setTicket(this.nbjour*2);
		}
		setType();
		setPrix();
		setNuméroChambre(this.type, arrivée, départ);
	}

	// Retourne le tableau des clients
	public ArrayList<Client> getTableauClient() {
		return this.tableauClient;
	}

	// Associe le client à un type de chambre selon le nombre de personnes
	public void setType() {
		if (this.tableauClient.size() == 1) {
			this.type = Type.SIMPLE;
		}
		if (this.tableauClient.size() == 2) {
			//Si il y a deux occupants dans une chambre alors les clients ont le choix entre la chambre double et la chambre deluxe
			Scanner sc = new Scanner(System.in);
			System.out.println("Vous avez le choix entre 'double' ou 'deluxe' : ");
     		String chambre = sc.nextLine();
     		//On convertit la chaine de caractères pour se servir de l'énumération
     		if (chambre.equals("double")) {
     			this.type = Type.DOUBLE;
     		}
     		else if (chambre.equals("deluxe")) {
     			this.type = Type.DELUXE;
     		}
     		else
     			System.err.println("Veuillez choisir entre 'double' ou 'deluxe'.");
		}
		if (this.tableauClient.size() == 3) {
			this.type = Type.TRIPLE;
		}
	}

	// Retourne le type de chambre
	public Type getType() {
		return this.type;
	}

	// Associe le type de chambre avec un prix
	// Multiplie le prix selon le nombre de jours réservés
	public void setPrix() {
		double jours = calculNbJours(this.arrivée, this.départ);
		if (getType() == Type.SIMPLE) {
			prix = 124.0 * jours;
		}
		if (getType() == Type.DOUBLE) {
			prix = 137.60 * jours;
		}
		if (getType() == Type.TRIPLE) {
			prix = 163.40 * jours;
		}
		if (getType() == Type.DELUXE) {
			prix = 189.20 * jours;
		}
		prixTaxe = prix + 10*prix/100;
	}

	// Retourne le prix taxé
	public double getPrix() {
		return this.prixTaxe;
	}

	// On vérifie que la saisie de la date d'arrivée est correcte de la forme : dd/MM/yyyy
	// SimpleDateFormat est une classe permettant de formater et d’analyser les dates
	public void setArrivée(String arrivée) {
		SimpleDateFormat dateArrivée = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
        	// On récupère la date d'arrivée
            date = dateArrivée.parse(arrivée);
            // Formate la date d'arrivée en date
            String formatDate = dateArrivée.format(date);
            // Vérifie que la date d'arrivée donné par l'utilisateur et la date d'arrivée formatée soient égales
            if(formatDate.compareTo(arrivée) ==  0) {
            	this.arrivée = arrivée;
            	this.tabDateArrivée.add(arrivée);
            }
            else
            	System.err.println("Date d'arrivée invalide : re-vérifiez svp.");
        }
        catch (Exception e) {
            System.out.println("Exception");
        }
	}

	// Retourne la date d'arrivée
	public String getArrivée() {
		return this.arrivée;
	}

	// On vérifie que la saisie de la date de départ est correcte de la forme : dd/MM/yyyy
	// SimpleDateFormat est une classe permettant de formater et d’analyser les dates
	public void setDépart(String départ) {
		SimpleDateFormat dateDépart = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
        	// On récupère la date de départ
            date = dateDépart.parse(départ);
            // Formate la date de départ en date
            String formatDate = dateDépart.format(date);
            // Vérifie que la date de départ donné par l'utilisateur et la date de départ formatée soient égales
            if(formatDate.compareTo(départ) ==  0) {
                this.départ = départ;
            	this.tabDateDépart.add(départ);
                //System.out.println("Date de départ valide.");
            }
            else
                System.err.println("Date de départ invalide : re-vérifiez svp.");
        }
        catch (Exception e) {
                System.out.println("Exception");
        }
	}

	// Retourne la date de départ
	public String getDépart() {
		return this.départ;
	}

	// Teste si la date de départ est bien après la date d'arrivée grâce à la méthode after()
	// SimpleDateFormat est une classe permettant de formater et d’analyser les dates
	public boolean départApresArrivée(String arrivée, String départ) {
		SimpleDateFormat dateArrivée = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateDépart = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();
        Date date2 = new Date();
        boolean départApresArrivée = true;
        try{
        	// On récupère les dates
        	date1 = dateArrivée.parse(arrivée);
        	date2 = dateDépart.parse(départ);
        	if (date2.after(date1) == false) {
        		départApresArrivée = false;
        		System.err.println("Date de départ et d'arrivée invalide : re-vérifiez svp.");
        	}
    	}
        catch(Exception e) {
        	System.out.println("Exception");
        }
        return départApresArrivée;
	}

	// Calcule le nombre de jours entre l'arrivée des clients et leur départ
	// SimpleDateFormat est une classe permettant de formater et d’analyser les dates
	public long calculNbJours(String arrivée, String départ) {
		SimpleDateFormat dateArrivée = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateDépart = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();
        Date date2 = new Date();
        long jours = 0;
        try {
        	// On récupère les dates
        	date1 = dateArrivée.parse(arrivée);
        	date2 = dateDépart.parse(départ);
        	if (départApresArrivée(arrivée, départ) == false) {
        		System.err.println("Date de départ et d'arrivée invalide : re-vérifiez svp.");
        	}
        	else {
        		// La méthode getTime() retourne le nombre de millisecondes depuis le 1er janvier 1970, 00:00:00 GMT
        		long milli = date2.getTime() - date1.getTime();
        		// On convertit les millisecondes en jours
        		jours = milli/(1000*60*60*24);
        	}
        }
        catch(Exception e) {
        	System.out.println("Exception");
        }
        return jours;
	}

	// Permet de savoir si la chambre est déjà prise en la comparant qu'a une seule chambre
	public boolean disponibilitéUneChambre(Type typeATester, String dateArrivéeATester, String dateDépartATester, int numéroTableau, String dateArrivéeTableau, String dateDépartTableau) {
		//Les chambres 1 à 5 sont les chambres simples
		if ((numéroTableau >= 1 && numéroTableau <= 5) && (typeATester == Type.SIMPLE) && (compareDate(dateArrivéeATester, dateDépartATester, dateArrivéeTableau, dateDépartTableau) == true)) {
			return true;
		}
		//Les chambres 6 à 10 sont les chambres doubles
		else if ((numéroTableau >= 6 && numéroTableau <= 10) && (typeATester == Type.DOUBLE) && (compareDate(dateArrivéeATester, dateDépartATester, dateArrivéeTableau, dateDépartTableau) == true)) {
			return true;
		}
		//Les chambres 11 à 15 sont les chambres deluxes
		else if ((numéroTableau >= 10 && numéroTableau <= 16) && (typeATester == Type.DELUXE) && (compareDate(dateArrivéeATester, dateDépartATester, dateArrivéeTableau, dateDépartTableau) == true)) {
			return true;
		}
		//Les chambres 16 à 20 sont les chambres triples
		else if ((numéroTableau >= 16 && numéroTableau <= 20) && (typeATester == Type.TRIPLE) && (compareDate(dateArrivéeATester, dateDépartATester, dateArrivéeTableau, dateDépartTableau) == true)) {
			return true;
		}
		else {
			return false;
		}
	}

	// Compare deux dates
	public boolean compareDate(String arrivéeATester, String départATester, String arrivéeTableau, String départTableau) {
		boolean pasLaMemeDate = true;
		SimpleDateFormat dateArrivée = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateDépart = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        Date date4 = new Date();
		try{
        	// On récupère les dates
        	// Les 2 dates fixes (deja reservées)
        	date1 = dateArrivée.parse(arrivéeTableau);
        	date2 = dateDépart.parse(départTableau);
        	Long date1Milli = date1.getTime();
        	Long date2Milli = date2.getTime();
        	// Les 2 dates qu'on va tester 
        	date3 = dateArrivée.parse(arrivéeATester);
        	date4 = dateDépart.parse(départATester);
        	Long date3Milli = date3.getTime();
        	Long date4Milli = date4.getTime();
        	for (long i = date1Milli; i <= date2Milli; i += 86400000) {
        		if (date3Milli.equals(i) || date4Milli.equals(i)) {
        			pasLaMemeDate = false;
        			//Si pour une même chambre, les dates à tester et les dates deja reservées sont les mêmes alors on ne peut pas reserver cette chambre
        			//Si les dates su'on veut tester sont situées dans l'intervalle des dates deja reservées on ne peut pas reserver la chambre non plus
        		}
			}
			if ((date3Milli <= date1Milli) && (date4Milli >= date2Milli)){
                pasLaMemeDate = false;
                //Si on veut reserver la chambre dans un intervalle du temps dans lequel la chambre est deja reservée alors on ne peut pas reserver la chambre

            }
		}
		catch (Exception e) {
        	pasLaMemeDate = true;
        	System.out.println("Exception");
        }
        return pasLaMemeDate;
	}

	// Retourne le numéro de la chambre
	public int getNuméroChambre(Type typeATester, String dateArrivéeATester, String dateDépartATester) {
		try{
			//On effectue les verifications avec les méthodes compareDate et disponibilitéUneChambre
			//Pour cela, on cherche dans le tableau repertoriant les numeros de chambres qui ont deja ete reservés les numéros correspondant au type de la chambre qu'on veut reserver
			//On releve l'indice correspondant à la reservation de la chambre pour verifier avec les tableaux repertoriant les dates de depart et d'arrivée la disponibilité de ces chambres durant les dates souhaitées

			if (typeATester == Type.SIMPLE) {
				for (int i = 1; i <= 5 ;i++) {
					if (tabNumChambre.contains(i)) {
						int entiertemp=tabNumChambre.indexOf(i);
						if (disponibilitéUneChambre(typeATester, dateArrivéeATester,dateDépartATester, i, tabDateArrivée.get(entiertemp), tabDateDépart.get(entiertemp)) == true) {
							numéroChambre = i;
							return numéroChambre;
						}
					}
					else {
						numéroChambre = i;
						return numéroChambre;
					}
				}
			}
			else if (typeATester == Type.DOUBLE) {
				for (int i = 6; i <= 10 ;i++) {
					if (tabNumChambre.contains(i)) {
						int entiertemp=tabNumChambre.indexOf(i);
						if (disponibilitéUneChambre(typeATester, dateArrivéeATester,dateDépartATester, i, tabDateArrivée.get(i), tabDateDépart.get(i)) == true) {
							numéroChambre = i;
							return numéroChambre;
						}
					}
					else {
						numéroChambre = i;
						return numéroChambre;
					}
				}
			}
			else if (typeATester == Type.DELUXE) {
				for (int i = 11; i <= 15 ;i++) {
					if (tabNumChambre.contains(i)) {
						int entiertemp=tabNumChambre.indexOf(i);
						if (disponibilitéUneChambre(typeATester, dateArrivéeATester,dateDépartATester, i, tabDateArrivée.get(i), tabDateDépart.get(i)) == true) {
							numéroChambre = i;
							return numéroChambre;
						}
					}
					else {
						numéroChambre = i;
						return numéroChambre;
					}
				}
			}
			else if (typeATester == Type.TRIPLE) {
				for (int i = 16; i <= 20 ;i++) {
					if (tabNumChambre.contains(i)) {
						int entiertemp=tabNumChambre.indexOf(i);
						if (disponibilitéUneChambre(typeATester, dateArrivéeATester,dateDépartATester, i, tabDateArrivée.get(i), tabDateDépart.get(i)) == true) {
							numéroChambre = i;
							return numéroChambre;
						}
					}
					else {
						numéroChambre = i;
						return numéroChambre;
					}
				}
			}
			if (numéroChambre == 0) {
				System.err.println("Il n'y a plus de chambre de libre.");
			}
		}
		catch(Exception e) {
			System.out.println("Exception");
		}
		return numéroChambre;
	}

	// Ajoute le numéro de la chambre au tableau des numéros de chambres
	public void setNuméroChambre(Type typeATester, String dateArrivéeATester, String dateDépartATester) {
		int nbChambreTemp = getNuméroChambre(typeATester, dateArrivéeATester, dateDépartATester);
		this.numéroChambre = nbChambreTemp;
		this.tabNumChambre.add(nbChambreTemp);
	}

	// Retourne le numéro de la chambre
	public int TestRetourNuméroChambre() {
		return this.numéroChambre;
	}

	// Affichage sur l'écran
	public String toString() {
		String res = "";
    	Date date = new Date();
    	SimpleDateFormat dateActuelle = new SimpleDateFormat("MMM d, y H:m:s a");
    	String formatDate = dateActuelle.format(date);
    	res = "************************ HOTEL BLUE BAY CARACAO ************************ \n";
    	res += "Date de réservation : " + formatDate + "\n";
    	for (int i = 0; i < tableauClient.size(); i++) {
    		res += "Nom et prénom du client (" + (i+1) + ") : " + this.tableauClient.get(i).getNom() + " " + this.tableauClient.get(i).getPrénom() + "\n";
    	}
    	for (int m = 0; m < tableauClient.size(); m++) {
    		res += "Date de naissance (" + (m+1) + ") : " + this.tableauClient.get(m).getNaissance() + "\n";
    	}
    	for (int j = 0; j < tableauClient.size(); j++) {
    		res += "email (" + (j+1) + ") : " + this.tableauClient.get(j).getEmail() + "\n";
    	}
    	for (int k = 0; k < tableauClient.size(); k++) {
    		res += "Téléphone (" + (k+1) + ") : " + this.tableauClient.get(k).getTéléphone() + "\n";
    	}
    	res += "Date d'arrivée : " + this.arrivée + "\n";
    	res += "Date de départ : " + this.départ + "\n";
    	res += "Vous restez donc : " + calculNbJours(this.arrivée, this.départ) + " nuits" + "\n";
    	res += "Prix de la chambre : " + getPrix() + " euros" + "\n";
    	res += "Numéro de la chambre : " + TestRetourNuméroChambre() + "\n";
    	return res;
	}

}