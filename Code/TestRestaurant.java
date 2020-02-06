import chambre.Chambre;
import client.Client;
import restaurant.Restaurant;

import java.util.*;

public class TestRestaurant {

	public static void main(String[]args) {
		ArrayList<Client> tabclient1 = new ArrayList<Client>();
		Client julien = new Client("julien", "choukroun", "19/10/1999", "julien.choukroun@orange.fr", "+33610943819");
		tabclient1.add(julien);
		Client jessica = new Client("jessica", "gourdon", "07/01/2000", "jessica.goudon@orange.fr", "0622345678");
		tabclient1.add(jessica);
		Chambre chambre1 = new Chambre(tabclient1, "03/12/2019", "10/12/2019");
		Restaurant table1 = new Restaurant(tabclient1);
		table1.DeroulementRestaurant();
	}

}