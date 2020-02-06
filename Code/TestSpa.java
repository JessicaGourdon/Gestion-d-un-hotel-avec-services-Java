import client.Client;
import spa.Spa;

import java.util.*;

public class TestSpa {

	public static void main(String[]args){
		ArrayList<Client> tabclient1 = new ArrayList<Client>();
		Client julien = new Client("julien", "choukroun", "19/10/1999", "julien.choukroun@orange.fr", "+33610943819");
		tabclient1.add(julien);
		Client jessica = new Client("jessica", "gourdon", "07/01/2000", "jessica.goudon@orange.fr", "0622345678");
		tabclient1.add(jessica);
		Spa spa = new Spa(tabclient1);
		spa.deroulementMassage();
	}

}