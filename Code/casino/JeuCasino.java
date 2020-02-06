package casino;
import client.*;

import java.util.*;
import java.lang.*;

public class JeuCasino {

	// Le client
	private Client client;
	// La cagnotte offerte par l'hotel au client
	private double cagnotteOfferte;
	// Argent inséré par le client
	private double argentInseree;
	// Tableau de toutes les mises du client
	private ArrayList<Double> argentMise = new ArrayList<Double>();
	// Les mises possibles
	protected enum Mise {PAIR, IMPAIR, ROUGE, NOIR, MANQUE, PASSE};
	// Tableau de toutes les mises
	private ArrayList<Mise> tableauMise = new ArrayList<Mise>();
	// Les gain du client
	private double gain;
	// Le numéro tirée aléatoirement par l'ordinateur
	private int numeroTiree;

	// Crée un jeu de casino
	public JeuCasino(Client client) {
		this.client = client;
		this.cagnotteOfferte = this.client.getCagnotte();
		this.gain = 0;
	}

	// Le client insere de la monnaie puis va pouvoir jouer avec cette monnaie
	// Il a la possibilité ou non d'utiliser la cagnotte donnée par l'hotel
	public void InsertionMonnaie(boolean utiliseLaCagnotte, double monnaieInseree) {
		if (utiliseLaCagnotte == false) {
			this.argentInseree = monnaieInseree;
		}
		else {
			if(this.client.getCagnotte() >= monnaieInseree){
				this.argentInseree = monnaieInseree;
				//Si le client utilise sa cagnotte, alors on se sert de la classe client.Client pour modifier le montant de sa cagnotte
				this.client.setCagnotte(this.client.getCagnotte() - monnaieInseree);
			}
			else {
				System.err.println("Votre Cagnotte ne dispose pas assez d'argent pour cela");
			}
		}
	}

	// Le client effectue ses mises
	public void FaitesVosJeux(ArrayList<Double> monnaieMise, ArrayList<String> mise) {
		this.argentMise = monnaieMise;
		for (Double chaqueMise : monnaieMise) {
			this.argentInseree = this.argentInseree-chaqueMise;
		}
		if (this.argentMise.size() == mise.size()) {
			//pour chaque élément misé, on convertit la chaine de caractère pour se servir de l'énumération
			for (int k = 0; k < mise.size(); k++){
				if (mise.get(k).equals("PAIR")) {
					this.tableauMise.add(Mise.PAIR);
				}
				else if (mise.get(k).equals("IMPAIR")) {
					this.tableauMise.add(Mise.IMPAIR);
				}
				else if (mise.get(k).equals("ROUGE")) {
					this.tableauMise.add(Mise.ROUGE);
				}
				else if (mise.get(k).equals("NOIR")) {
					this.tableauMise.add(Mise.NOIR);
				}
				else if (mise.get(k).equals("MANQUE")) {
					this.tableauMise.add(Mise.MANQUE);
				}
				else if (mise.get(k).equals("PASSE")) {
					this.tableauMise.add(Mise.PASSE);
				}
				else {
					System.err.println("Veuillez misez sur un type correct");
				}
			}
		}
		else {
			System.err.println("Veuillez miser sur un élément quand vous entrez de l'argent");
		}
	}

	// L'ordinateur tire un numéro entre 0 et 36
	public void tirageNumero() {
		Random random = new Random();
		this.numeroTiree = random.nextInt(37);
		System.out.println("Le numéro est: " + this.numeroTiree);
	}

	// On associe les numéros à un type de mise
	public void gestionGain() {
		ArrayList<Integer> tableauRouge = new ArrayList<Integer>();
		tableauRouge.add(1);
		tableauRouge.add(3);
		tableauRouge.add(5);
		tableauRouge.add(7);
		tableauRouge.add(9);
		tableauRouge.add(12);
		tableauRouge.add(14);
		tableauRouge.add(16);
		tableauRouge.add(18);
		tableauRouge.add(19);
		tableauRouge.add(21);
		tableauRouge.add(23);
		tableauRouge.add(25);
		tableauRouge.add(27);
		tableauRouge.add(30);
		tableauRouge.add(32);
		tableauRouge.add(34);
		tableauRouge.add(36);
		ArrayList<Integer> tableauNoir = new ArrayList<Integer>();
		tableauNoir.add(2);
		tableauNoir.add(4);
		tableauNoir.add(6);
		tableauNoir.add(8);
		tableauNoir.add(10);
		tableauNoir.add(11);
		tableauNoir.add(13);
		tableauNoir.add(15);
		tableauNoir.add(17);
		tableauNoir.add(20);
		tableauNoir.add(22);
		tableauNoir.add(24);
		tableauNoir.add(26);
		tableauNoir.add(28);
		tableauNoir.add(29);
		tableauNoir.add(31);
		tableauNoir.add(33);
		tableauNoir.add(35);
		try {
			// Si le numéro correspond à la mise du client, il gagne
			for (int i = 0; i < this.tableauMise.size(); i++){
				if ((this.numeroTiree%2 == 0) && (this.tableauMise.get(i) == Mise.PAIR)){
					this.gain = this.gain + 2*this.argentMise.get(i);
				}
				if ((this.numeroTiree%2 == 1) && (this.tableauMise.get(i) == Mise.IMPAIR)){
					this.gain = this.gain + 2*this.argentMise.get(i);
				}
				if ((tableauRouge.contains(this.numeroTiree)) && (this.tableauMise.get(i) == Mise.ROUGE)){
					this.gain = this.gain + 2*this.argentMise.get(i);
				}
				if ((tableauNoir.contains(this.numeroTiree)) && (this.tableauMise.get(i) == Mise.NOIR)){
					this.gain = this.gain + 2*this.argentMise.get(i);
				}
				if ((1 <= this.numeroTiree) && (this.numeroTiree <= 18) && (this.tableauMise.get(i) == Mise.MANQUE)){
					this.gain = this.gain + 2*this.argentMise.get(i);
				}
				if ((19 <= this.numeroTiree) && (this.numeroTiree <= 36) && (this.tableauMise.get(i) == Mise.PASSE)){
					this.gain = this.gain + 2*this.argentMise.get(i);
				}
			}
			System.out.println("Vous avez gagné "+this.gain+" euros");
			this.tableauMise.clear();
		}
		catch (Exception e){
			System.out.println("Vous n'avez pas misé");
		}
	}		

	// On modifie les attributs en fonction du gain du client
	public void VersementGain() {
		this.argentInseree = this.argentInseree + this.gain;
		this.gain = 0;
	}

	// Le jeu complet
	public void deroulementJeuComplet(boolean utiliseCagnotte, double monnaieInseree, ArrayList<Double> monnaieMise, ArrayList<String> mise) {
		this.InsertionMonnaie(utiliseCagnotte,monnaieInseree);
		this.FaitesVosJeux(monnaieMise,mise);
		this.tirageNumero();
		this.gestionGain();
		this.VersementGain();
		System.out.println(this.toString());
		this.argentInseree = 0;
	}

	// Affichage sur l'écran la situation du joueur
    public String toString() {
    	return "Votre Compte est à "+this.argentInseree+" euros";
    }		

}