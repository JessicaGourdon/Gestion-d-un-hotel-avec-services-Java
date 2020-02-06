import client.Client;

public class TestClient {
	
	public static void main (String [] args) {
		Client a = new Client("julien", "choukroun", "19/10/1999", "julien.choukroun@orange.fr", "+33610943819");
		System.out.println(a.toString());
		Client b = new Client("jessica", "gourdon", "40/01/2000", "jessica.gourdonorange.fr", "0aaa123456");
		System.out.println(b.toString());

	}

}