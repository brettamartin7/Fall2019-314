public class Project1 {
	public static void main(String[] args) 
	{
		// Instantiate Primes Class
		PrimeOperations p = new PrimeOperations();
		Config.APPLICATION_NAME = "My Prime Generator";
		MainWindow mw = new MainWindow(Config.APPLICATION_NAME, p);
	}
}
