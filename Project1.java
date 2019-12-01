public class Project1 {
	public static void main(String[] args) 
	{
		// Instantiate Primes Class
		PrimeOperations p = new PrimeOperations();
		Config.NAME = "My Prime Generator";
		MainWindow mw = new MainWindow(Config.NAME, p);
	}
}
