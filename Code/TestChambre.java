import chambre.Chambre;
import client.Client;

import java.util.*;

public class TestChambre {
	
	public static void main (String [] args) {
		ArrayList<Client> tabclient1=new ArrayList<Client>();
		Client client1 = new Client("julien", "choukroun", "19/10/1999", "julien.choukroun@orange.fr", "+33610943819");
		tabclient1.add(client1);

		ArrayList<Client> tabclient2=new ArrayList<Client>();
		Client client2 = new Client("jessic", "gourdo", "07/01/1000", "jesica.goudon@ornge.r", "0622345678");
		tabclient2.add(client2);

		ArrayList<Client> tabclient3=new ArrayList<Client>();
		Client client3 = new Client("jessica", "gordon", "07/10/2000", "jesiica.gourdon@orange.fr", "0613345678");
		tabclient3.add(client3);

		ArrayList<Client> tabclient4=new ArrayList<Client>();
		Client client4 = new Client("jessia", "ourdon", "07/07/2000", "jssica.gourdon@orane.fr", "0614345678");
		tabclient4.add(client4);

		ArrayList<Client> tabclient5=new ArrayList<Client>();
		Client client5 = new Client("jessca", "gourdos", "08/01/2000", "essica.gourdon@ornge.fr", "0672345678");
		tabclient5.add(client5);

		ArrayList<Client> tabclient6=new ArrayList<Client>();
		Client client6 = new Client("jesca", "ourdos", "10/01/2000", "essic.gourdon@ornge.fr", "0677345678");
		tabclient6.add(client6);

		ArrayList<Client> tabclient7=new ArrayList<Client>();
		Client client7 = new Client("jess", "gou", "10/01/2000", "essic.gourdon@ornge.fr", "0677345678");
		tabclient7.add(client7);
		Client client8 = new Client("ju", "cou", "10/01/1999", "essic.godon@ornge.fr", "0677345678");
		tabclient7.add(client8);

		//les chambres
		Chambre chambre1 = new Chambre(tabclient1, "03/12/2019", "10/12/2019");
		Chambre chambre2 = new Chambre(tabclient2, "05/12/2019", "15/12/2019");
		Chambre chambre3 = new Chambre(tabclient3, "01/12/2019", "20/12/2019");
		Chambre chambre4 = new Chambre(tabclient4, "01/12/2019", "05/12/2019");
		Chambre chambre5 = new Chambre(tabclient5, "03/12/2019", "10/12/2019");
		Chambre chambre6 = new Chambre(tabclient6, "03/12/2019", "10/12/2019");
		Chambre chambre7= new Chambre(tabclient7,"03/12/2019", "10/12/2019");

		System.out.println(chambre1.toString());
		System.out.println(chambre2.toString());
		System.out.println(chambre3.toString());
		System.out.println(chambre4.toString());
		System.out.println(chambre5.toString());
		System.out.println(chambre6.toString());
		System.out.println(chambre7.toString());
	}

}