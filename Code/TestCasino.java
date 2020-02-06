import casino.JeuCasino;
import client.Client;

import java.util.*;
import java.lang.*;

public class TestCasino{

	public static void main(String[]args){

		Client julien = new Client("julien", "choukroun", "19/10/1999", "julien.choukroun@orange.fr", "+33610943819");

		JeuCasino jeuA = new JeuCasino(julien);

		ArrayList<Double> tableauDesMises = new ArrayList<Double>();
		tableauDesMises.add(5.0);
		tableauDesMises.add(10.0);

		ArrayList<String> objetDesMises = new ArrayList<String>();
		objetDesMises.add("IMPAIR");
		objetDesMises.add("NOIR");

		jeuA.deroulementJeuComplet(true, 20, tableauDesMises, objetDesMises);
		System.out.println(jeuA.toString());
		jeuA.deroulementJeuComplet(false, 20, tableauDesMises, objetDesMises);
		System.out.println(jeuA.toString());
	}

}