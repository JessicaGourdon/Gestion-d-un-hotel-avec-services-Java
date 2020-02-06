package restaurant;

import client.Client;

import java.util.*;

public class Restaurant {

	// Tableau avec les clients à la table du restaurant
	public ArrayList<Client> tableauClient = new ArrayList<Client>();
	//Nombre de clients à la table
	private int nbclient;
	// Tableau des booleans signalant la commande de menus mojitos
	public ArrayList<Boolean> tableauMojito = new ArrayList<Boolean>();
	// Tableau des booleans signalant la commande de menus daiquiri
	public ArrayList<Boolean> tableauDaiquiri = new ArrayList<Boolean>();
	// Tableau des booleans signalant la commande de plats
	public ArrayList<Boolean> tableauPlat = new ArrayList<Boolean>();
	// Tableau des booleans signalant la commande d'accompagnement
	public ArrayList<Boolean> tableauAccompagnement = new ArrayList<Boolean>();
	// Tableau des booleans signalant la commande de dessert
	public ArrayList<Boolean> tableauDessert = new ArrayList<Boolean>();
	// Tableau des booleans signalant la commande de boisson
	public ArrayList<Boolean> tableauBoisson = new ArrayList<Boolean>();
	// Le prix total
	public double prixTotal;
	// Le nombre de ticket que le client va utiliser
	public int ticketTotalMis;
	// Argent restant à payer
	public double argentRestantAPayer;

	// Crée un restaurant avec un tableau de clients
	public Restaurant (ArrayList<Client> tableauClient) {
		this.tableauClient=tableauClient;
		this.nbclient=this.tableauClient.size();
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

	public void commande() {
		Scanner sc = new Scanner(System.in);
		String choixFormule;
		boolean plat;
		boolean accompagnement;
		boolean dessert;
		boolean boisson;
		//Les tableaux tableauMojito,tableauDaiquiri, tableauPlat, tableauAccompagnement, tableauDessert et tableauBoisson contiendront des booleen indiquant la volonté de chaque client
		//Chaque client est un indice c'est-a-dire pour le cient n°1, sa commande est connue par les booleens des indices 0 de chaque tableau
		for (int i = 0; i < nbclient; i++) {
			System.out.println ("Bonjour " + this.tableauClient.get(i).getPrénom());
			System.out.println("Choisissez entre: 'CARTE' 'MOJITO' 'DAIQUIRI'");
			choixFormule = sc.nextLine();
			if (choixFormule.equals("CARTE")) {
				tableauMojito.add(false);
				tableauDaiquiri.add(false);
				System.out.println("Souhaitez-vous commander un plat?");
				plat = ConvertitOuiEnBool(sc.nextLine());
				tableauPlat.add(plat);
				System.out.println("Souhaitez-vous commander un accompagnement?");
				accompagnement = ConvertitOuiEnBool(sc.nextLine());
				tableauAccompagnement.add(accompagnement);
				System.out.println("Souhaitez-vous commander un dessert?");
				dessert = ConvertitOuiEnBool(sc.nextLine());
				tableauDessert.add(dessert);
				System.out.println("Souhaitez-vous commander une boisson?");
				boisson = ConvertitOuiEnBool(sc.nextLine());
				tableauBoisson.add(boisson);
			}
			else if (choixFormule.equals("MOJITO")) {
				tableauMojito.add(true);
				tableauDaiquiri.add(false);
				tableauPlat.add(false);
				tableauAccompagnement.add(false);
				tableauBoisson.add(false);
				System.out.println("Souhaitez-vous commander un dessert?");
				dessert = ConvertitOuiEnBool(sc.nextLine());
				tableauDessert.add(dessert);
			}
			else if (choixFormule.equals("DAIQUIRI")) {
				tableauMojito.add(false);
				tableauDaiquiri.add(true);
				tableauPlat.add(false);
				tableauAccompagnement.add(false);
				tableauBoisson.add(false);
				System.out.println("Souhaitez-vous commander un dessert?");
				dessert = ConvertitOuiEnBool(sc.nextLine());
				tableauDessert.add(dessert);
			}
			else {
				System.err.println("Veuillez selectionner une formule valide");
			}
		}
	}

	public void CalculTotalPrix() {
		//On parcourt chaque tableau pour verifier la présence des éléments commandés et on calcule le prix total en fonction de cela
		this.prixTotal = 0.0;
		for (boolean el: this.tableauMojito) {
			if(el == true){
				this.prixTotal += 6;
			}
		}
		for (boolean el: this.tableauDaiquiri) {
			if(el == true){
				this.prixTotal += 8;
			}
		}
		for (boolean el: this.tableauPlat) {
			if(el == true){
				this.prixTotal += 5;
			}
		}
		for (boolean el: this.tableauAccompagnement) {
			if(el == true){
				this.prixTotal += 2;
			}
		}
		for (boolean el: this.tableauDessert) {
			if(el == true){
				this.prixTotal += 3.5;
			}
		}
		for (boolean el: this.tableauBoisson) {
			if(el == true){
				this.prixTotal += 2;
			}
		}
		System.out.println("Prix Total: " + this.prixTotal + " euros");
	}

	public void Reglement() {
		this.ticketTotalMis = 0;
		this.argentRestantAPayer = this.prixTotal;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		for (Client client : tableauClient){
			//On demande à chaque client s'il désire se servir de tickets restaurants
			int choixNbTickets;
			System.out.println("Le client " + client.getPrénom() + " souhaite-t-il utiliser un ticket restaurant.Restaurant?");
			boolean choix = ConvertitOuiEnBool(sc1.nextLine());
			if (choix == true){
				//Si sa réponse est positive alors on lui demande combien de tickets il souhaite utiliser
				System.out.println("Combien de tickets souhaitez-vous utiliser?");
				choixNbTickets = sc2.nextInt();
				if ((client.getTicket() >= choixNbTickets) && (5*choixNbTickets <= this.argentRestantAPayer)){
					this.ticketTotalMis += choixNbTickets;
					this.argentRestantAPayer -= 5*choixNbTickets;
					client.setTicket(client.getTicket() - choixNbTickets);
				}
				else if (client.getTicket() < choixNbTickets) {
					//Si le client souhaite mettre plus de tickets qu'il en a en sa possession alors son choix ne sera pas pris en compte
					System.out.println("Vous n'avez pas assez de tickets disponibles pour cela. Votre choix n'est donc pas pris en compte");
				}
				else {
					//Si le client met un  nombre de ticket trop important c'est-a-dire correspondant à un montant supérieur au prix total alors son choix n'est pas pris en compte
					System.out.println("Vous mettez trop de tickets restaurants pour le prix total. Votre choix n'est donc pas pris en compte");
				}
			choix = false;
			}
		}
		System.out.println("Vous avez utilisé un total de "+this.ticketTotalMis+" tickets Restaurants");
		System.out.println("Il vous reste donc à payer un montant de "+this.argentRestantAPayer+" euros");
		this.tableauMojito.clear();
		this.tableauDaiquiri.clear();
		this.tableauPlat.clear();
		this.tableauAccompagnement.clear();
		this.tableauBoisson.clear();
		this.tableauDessert.clear();

	}

	public void DeroulementRestaurant(){
		this.commande();
		this.CalculTotalPrix();
		this.Reglement();
	}



}


