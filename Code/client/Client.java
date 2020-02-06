package client;

import java.text.*;
import java.util.*;

public class Client {

	// Le prénom du client
	private String prénom;
	// Le nom du client
	private String nom;
	// Date de naissance du client
	private String naissance;
	// Email du client
	private String email;
	// Numéro de téléphone du client
	private String téléphone;
	//Montant de la cagnotte pour le casino
    private double CagnotteCasino;
    //Nombre de tickets restaurants
    private int ticketRestaurant;

	// Création d'un nouveau client
	public Client(String prénom, String nom, String naissance, String email, String téléphone) {
		this.prénom = prénom;
		this.nom = nom;
		setNaissance(naissance);
		setEmail(email);
		setTéléphone(téléphone);
        // On fixe la cagnotte du casino par défault à 20 euros
		this.CagnotteCasino = 20;
	}

	// Retourne le prénom du client
	public String getPrénom() {
		return this.prénom;
	}

	// Fixe un nouveau prénom au client
	public void setPrénom(String newPrénom) {
		this.prénom = newPrénom;
	}

	// Retourne le nom du client
	public String getNom() {
		return this.nom;
	}

	// Fixe un nouveau nom au client
	public void setNom(String newNom) {
		this.nom = newNom;
	}

	// Retourne la date de naissance du client
	public String getNaissance() {
		return this.naissance;
	}

	// On vérifie que la saisie de la date de naissance est correcte de la forme : dd/MM/yyyy
	// SimpleDateFormat est une classe permettant de formater et d’analyser les dates
	public void setNaissance(String naissance) {
		SimpleDateFormat dateNaissance = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
        	// On récupère la date de naissance
            date = dateNaissance.parse(naissance);
            // Formate la date de naissance en date
            String formatDate = dateNaissance.format(date);
            // Vérifie que la date de naissance donné par l'utilisateur et la date de naissance formatée soient égales
            if(formatDate.compareTo(naissance) ==  0) {
            	this.naissance = naissance;
            }
            else
            	System.err.println("Date de naissance invalide : re-vérifiez svp.");
        }
        catch (Exception e) {
                System.out.println("Exception");
        }
    }

    // Retourne l'email du client
    public String getEmail() {
    	return this.email;
    }

    // On vérifie que la saisie de l'email est correct
    // L'adresse mail contient un '@' et un '.' (l'ordre d'appartition est important)
    public void setEmail(String email) {
    	if (email.indexOf("@") > 0) {
    		// Sépare la chaine en deux
    		String [] tmp = email.split("@");
    		// La chaine à droite du '@' doit contenir un '.' (par exemple : '.fr' ou '.com')
            //Cela nous permet donc de vérifier à la fois la présence du @ mais également du . et cela dans le bon ordre
    		if (tmp[1].contains(".")) {
    			this.email = email;
    		}
    		else
    			System.err.println("Adresse invalide : re-vérifiez svp.");
    	}
    	else
    		System.err.println("Adresse invalide : re-vérifiez svp.");
    }

    // Retourne le numéro de téléphone du client
    public String getTéléphone() {
    	return this.téléphone;
    }

    // Teste si la chaine est un entier c'est-à-dire si il n'y a pas la présence d'un autre caractère tel qu'un point parmi les chiffres
	public boolean estUnEntier(String téléphone) {
		boolean estUnChiffre = true;
		// On crée un tableau avec tous les caractères de la chaine
		char[] tableauChiffres = téléphone.toCharArray();
		// On teste si chaque caractère est un entier
		for(char chiffre : tableauChiffres){
			if(!Character.isDigit(chiffre) && estUnChiffre){
				// Le caractère n'est pas un entier
				estUnChiffre = false;
			}
		}
		return estUnChiffre;
	}

    // On vérifie que la saisie du numéro de téléphone est correct
    // La chaine est constituée par au maximum 12 caractères.
    // Si il y a le code du pays, on écrit : '+codenumero' (par exemple : +33612345678 et si non, on écrit : 0612345678)
    public void setTéléphone(String téléphone) {
    	try{
    		// On regarde le premier caractère de la chaine
    		String plus = téléphone.substring(0,1);
    		if (plus.equals("+")) {
    			// On garde uniquement les chiffres
    			String justeLesChiffres = téléphone.substring(1);
    			// Le numéro doit avoir 12 caractères (un '+' et 11 chiffres)
    			if ((téléphone.length() != 12) || ((estUnEntier(justeLesChiffres)) == false)) {
    				System.err.println("Numéro de téléphone invalide : re-vérifiez svp.");
    			}
    			else {
    				this.téléphone = téléphone;
    			}
    		}
    		// Le numéro doit avoir 10 caractères (10 chiffres)
    		else if ((téléphone.length() != 10) || ((estUnEntier(téléphone)) == false)) {
    			System.err.println("Numéro de téléphone invalide : re-vérifiez svp.");
    		}
    		else {
    			this.téléphone = téléphone;
    		}
    	}
    	catch (Exception e) {
                System.out.println("Exception");
        }
    }

    // Retourne la cagnotte du client au casino
    public double getCagnotte() {
        return this.CagnotteCasino;
    }

    // Fixe une nouvelle cagnotte au client pour le casino
    public void setCagnotte(double newCagnotte) {
        this.CagnotteCasino = newCagnotte;
    }

    // Retourne le nombre de ticket restaurant restant au client
    public int getTicket() {
        return this.ticketRestaurant;
    }

    // Modifie le nombre de ticket restaurants restant au client
    public void setTicket(int newTicket) {
        this.ticketRestaurant = newTicket;
    }

    // Affichage sur l'écran
    public String toString() {
    	String res = "";
    	Date date = new Date();
    	SimpleDateFormat dateActuelle = new SimpleDateFormat("MMM d, y H:m:s a");
    	String formatDate = dateActuelle.format(date);
    	res = "************************ HOTEL BLUE BAY CARACAO ************************ \n";
    	res += "Date de réservation : " + formatDate + "\n";
    	res += "Nom et prénom du client : " + this.nom + " " + this.prénom + "\n";
    	res += "Date de naissance : " + this.naissance + "\n";
    	res += "email : " + this.email + "\n";
    	res += "Téléphone : " + this.téléphone;
    	return res;
    }

}