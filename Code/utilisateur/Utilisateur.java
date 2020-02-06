package utilisateur;

import casino.JeuCasino;
import chambre.Chambre;
import client.Client;
import restaurant.Restaurant;
import spa.Spa;

import java.util.*;

public class Utilisateur {

	// Tableau des noms des clients
	ArrayList<String> tabNom = new ArrayList<String>();
	// Tableau des prénoms des clients
	ArrayList<String> tabPrénom = new ArrayList<String>();
	// Tableau des dates de naissances des clients
	ArrayList<String> tabDateNaissance = new ArrayList<String>();
	// Tableau des emails des clients
	ArrayList<String> tabEmail = new ArrayList<String>();
	// Tableau des numéros de téléphone des clients
	ArrayList<String> tabTéléphone = new ArrayList<String>();
	// Nombre de personnes dans une chambre
	int nbPersonnes = 0;
	// Tableau des clients pour les chambres
	ArrayList<Client> tabClient = new ArrayList<Client>();
	// Tableau de clients
	Client [] client = new Client[20];
	// Tableau des dates d'arrivée
	ArrayList<String> tabDateArrivée = new ArrayList<String>();
	// Tableau des dates de départ
	ArrayList<String> tabDateDépart = new ArrayList<String>();
	// Tableau des chambres
	Chambre [] chambre = new Chambre[20];
	// Tableau des restaurants
	Restaurant[] table = new Restaurant[20];
	// Tableau des spa
	Spa [] spa = new Spa[20];
	// Tableau des casinos
	JeuCasino[] jeu = new JeuCasino[20];
	// Tableau des mises d'argent des clients
	ArrayList<Double> tabMonnaieMise = new ArrayList<Double>();
	// Tableau des mises des clients (par exemple : PAIR, ROUGE, etc)
	ArrayList<String> tabMise = new ArrayList<String>();
	// Boolean qui indique si le client veut utiliser la cagnotte offerte par l'hotel ou non
	private boolean utiliseCagnotte;
	// Monnaie inséréé par le client
	double monnaieInseree;
	// Permet de réaliser une autre réservation à la fin de l'execution du programme
	private boolean recommencer = true;
	private int k=0;

	Scanner sc = new Scanner(System.in);

	// Crée un utilisateur
	public Utilisateur() {
		System.out.println("BIENVENUE A L'HOTEL BLUE BAY CARACAO");
		System.out.println("Veuillez remplir cette fiche de réservation");
		//On crée un nouveau client (ou plusieurs) auquel on va attribuer une chambre par la suite
		//On va ensuite orienter ce ou ces clients vers le restaurant puis vers le spa et enfin vers le casino
		while (recommencer == true){
			client();
			chambre();
			chambre[k] = new Chambre(tabClient, tabDateArrivée.get(k), tabDateDépart.get(k));
			System.out.println(chambre[k].toString());
			System.out.println("RESTAURANT");
			table[k] = new Restaurant(tabClient);
			table[k].DeroulementRestaurant();
			System.out.println("SPA");
			spa[k] = new Spa(tabClient);
			spa[k].deroulementMassage();
			System.out.println("CASINO");
			for (int i = 1; i <= nbPersonnes; i++) {
				System.out.println("CLIENT n° " + i);
				casino();
				jeu[k] = new JeuCasino(tabClient.get(i-1));
				jeu[k].deroulementJeuComplet(utiliseCagnotte, monnaieInseree, tabMonnaieMise, tabMise);
				tabMonnaieMise.clear();
				tabMise.clear();
			}
			recom();
		}
	}

	public void client() {
		System.out.println("Combien êtes-vous de personne pour une chambre (maximum 3 personnes) ?");
		nbPersonnes = sc.nextInt();
		// A chaque client créé, on demande ses informations personnelles pour remplir les données dans notre constructeur de la classe client.Client
		for (int i = 1; i <= nbPersonnes; i++) {
			System.out.println("CLIENT n° " + i);
			System.out.println("Entrez le nom du client " + i);
			String nom = sc.next();
			tabNom.add(nom);
			System.out.println("Entrez le prénom du client " + i);
			String prénom = sc.next();
			tabPrénom.add(prénom);
			System.out.println("Entrez la date de naissance sous la forme dd/MM/yyyy du client " + i);
			String dateNaissance = sc.next();
			tabDateNaissance.add(dateNaissance);
			System.out.println("Entrez l'email du client " + i);
			String email = sc.next();
			tabEmail.add(email);
			System.out.println("Entrez le numéro de téléphone du client " + i);
			String téléphone = sc.next();
			tabTéléphone.add(téléphone);
		}
		for (int j = 0; j < nbPersonnes; j++) {
			client[j] = new Client(tabPrénom.get(j), tabNom.get(j), tabDateNaissance.get(j), tabEmail.get(j), tabTéléphone.get(j));
			tabClient.add(client[j]);
		}
	}

	public void chambre() {
		//Lors de la création de la nouvelle chambre, on demande les dates d'arrivées et de départ pour pouvoir lancer la création de la chambre en effectuant toutes les verification grâce aux méthodes de notre classe chambre.Chambre
		System.out.println("CHAMBRE");
		System.out.println("Entrez une date d'arrivée sous la forme dd/MM/yyyy");
		String dateArrivée = sc.next();
		tabDateArrivée.add(dateArrivée);
		System.out.println("Entrez une date de départ sous la forme dd/MM/yyyy");
		String dateDépart = sc.next();
		tabDateDépart.add(dateDépart);
	}

	// Convertit "oui" en true et "non" en false
	// On utilise cette méthode pour les scanners
	public boolean ConvertitOuiEnBool(String ouinon) {
		if (ouinon.equals("oui")) {
			return true;
		}
		else if (ouinon.equals("non")) {
			return false;
		}
		else {
			System.err.println("Veuillez donner une réponse par oui ou par non");
			return false;
		}
	}

	public void casino() {
		//On demande au client toutes les informations concernant ses mises pour pouvoir le faire jouer au casino
		System.out.println("Souhaitez-vous utiliser la cagnotte ?");
		Boolean utiliseCagnotte = ConvertitOuiEnBool(sc.next());
		System.out.println("Combien de monnaie voulez-vous insérer ?");
		this.monnaieInseree = sc.nextDouble();
		System.out.println("Combien de mise voulez-vous faire ? (Maximum 6)");
		int nbMise = sc.nextInt();
		for (int j = 1; j <= nbMise; j++) {
			System.out.println("MISE n° " + j);
			System.out.println("Combien voulez-vous miser ?");
			double mise = sc.nextDouble();
			tabMonnaieMise.add(mise);
			System.out.println("Où voulez-vous miser ? (PAIR, IMPAIR, ROUGE, NOIR, MANQUE ou PASSE)");
			String emplacementMise = sc.next();
			tabMise.add(emplacementMise);
		}
	}

	public void recom() {
		//Si l'on veut relancer le programme pour effectuer une autre reservation, alors cette méthode va effacer les attributs qu'on a pu fixé precedemment
		System.out.println("Voulez vous faire une autre réservation ?");
		String autreResa = sc.next();
		if (autreResa.equals("oui")) {
			recommencer = true;
			k++;
			tabNom.clear();
			tabPrénom.clear();
			tabDateNaissance.clear();
			tabEmail.clear();
			tabTéléphone.clear();
			tabClient.clear();
			for(int m = 0; m < client.length; m++){
				client[m] = null;
			}
		}
		if (autreResa.equals("non")) {
			recommencer = false;
			System.out.println("Au revoir.");
		}
	}

}