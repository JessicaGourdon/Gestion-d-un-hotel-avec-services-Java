package spa;

import client.Client;

import java.util.*;

public class Spa {

	// Prix du massage relaxant
	public double prixRelaxant;
	// Prix du massage aux pierres chaudes
	public double prixPierre;
	// Prix du massage à la bougie
	public double prixBougie;
	// Tableau avec les clients au spa
	public ArrayList<Client> tableauClient = new ArrayList<Client>();
	// Nombre de clients au spa
	private int nbclient;
	// Tableau des commandes du massage relaxant (contenant le nombre demandé)
	public ArrayList<Integer> tableauRelaxant = new ArrayList<Integer>();
	// Tableau des commandes du massage relaxant (contenant le nombre demandé)
	public ArrayList<Integer> tableauPierre = new ArrayList<Integer>();
	// Tableau des booleans signalant la commande du massage à la bougie
	public ArrayList<Integer> tableauBougie = new ArrayList<Integer>();
	// Prix total
	public double prixTotal;

	// Crée un spa avec un tableau de clients desirant s'y rendre
	public Spa(ArrayList<Client> tableauClient) {
		this.tableauClient = tableauClient;
		this.nbclient = this.tableauClient.size();
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

	// Le client à le choix entre 3 types de massage : relaxant, aux pierres chaudes et à la bougie
	public void choixMassage() {
		boolean choix;
		int nbMassage;
		//Pour chaque client, on demande s'il desire certains types de massages
		//En fonction de ses réponses, on remplit les tableaux tableauRelaxant, tableauPierre et tableauBougie de booleen correspondant à ses choix
		//En cas de réponse positive pour les massages, on définit combien de massages de ce type le client souhaite
		for (int i = 0 ; i < nbclient ; i++) {
			System.out.println("Bonjour " + this.tableauClient.get(i).getPrénom());
			Scanner sc1 = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Souhaitez-vous un massage relaxant ?");
			choix=ConvertitOuiEnBool(sc1.nextLine());
			if (choix == true) {
				System.out.println("Combien souhaitez-vous de massage relaxant ?");
				nbMassage = sc2.nextInt();
				tableauRelaxant.add(nbMassage);
			}
			else{
				tableauRelaxant.add(0);
			}
			System.out.println("Souhaitez-vous un massage aux pierres chaudes ?");
			choix = ConvertitOuiEnBool(sc1.nextLine());
			if (choix == true) {
				System.out.println("Combien souhaitez-vous de massage aux pierres chaudes ?");
				nbMassage = sc2.nextInt();
				tableauPierre.add(nbMassage);
			}
			else{
				tableauPierre.add(0);
			}
			System.out.println("Souhaitez-vous un massage à la bougie ?");
			choix = ConvertitOuiEnBool(sc1.nextLine());
			if (choix == true){
				System.out.println("Combien souhaitez-vous de massage à la bougie ?");
				nbMassage = sc2.nextInt();
				tableauBougie.add(nbMassage);
			}
			else{
				tableauBougie.add(0);
			}
		}
	}

	public void CalculPrix() {
		this.prixTotal = 0.0;
		//On parcourt les différents tableaux pour calculer le prix total des soins
		for (int el: this.tableauRelaxant) {
				this.prixRelaxant += el*50;
		}
		for (int el: this.tableauPierre) {
				this.prixPierre += el*70;
		}
		for (int el: this.tableauBougie) {
				this.prixBougie += el*80;
		}
		this.prixTotal = this.prixRelaxant + this.prixPierre + this.prixBougie;
	}

	// Affichage sur l'écran
	public String toString() {
		String fin;
		fin = "spa.Spa « Yuma Massage »\n";
		fin += "Massages relaxants :" + this.prixRelaxant + " €\n";
		fin += "Massages aux pierres chaudes :" + this.prixPierre + " €\n";
		fin += "Massages à la bougie :" + this.prixBougie + " €\n";
		fin += "Total des massages à payer :" + this.prixTotal + " €";
		return fin;
	}

	public void deroulementMassage() {
		this.choixMassage();
		this.CalculPrix();
		System.out.println(this.toString());
		this.tableauRelaxant.clear();
		this.tableauPierre.clear();
		this.tableauBougie.clear();
	}

}