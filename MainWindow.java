import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class MainWindow extends JFrame implements ActionListener
{
	private class MainPanel extends JPanel {
		
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Dimension size = this.getSize();
			
			g.setColor(Color.BLACK);
			
			//Draws the grey lines across the screen
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.lightGray);
			Line2D line = new Line2D.Float(0,100,size.width,100);
			Line2D line2 = new Line2D.Float(0,200,size.width, 200);
			Line2D line3 = new Line2D.Float(0, 320, size.width, 320);
			g2.draw(line);
			g2.draw(line2);
			g2.draw(line3);
		}
	
		
	}
	private static final long serialVersionUID = -3880026026104218593L;
	private PrimeOperations m_Primes;
	
	//TextFields
	private JTextField primeTextField = new JTextField("primesInput.txt");
	private JTextField crossTextField = new JTextField("crossesInput.txt");
	
	//Labels
	private JLabel lblStatus = new JLabel("");
	
	private JLabel lblPrimesGenerated;
	private JLabel lblCrossesGenerated;
	private JLabel lblLengthLargestPrime;
	private JLabel lblLengthLargestCrosses;
	
	private JLabel primeTextFieldLabel = new JLabel("Note: Loading in primes uses a relative path, make sure to set up your data path in Config. Saving primes will save it to your data_path in a file named primesOutput.txt");
	private JLabel crossTextFieldLabel = new JLabel("Note: Loading in crosses uses a relative path, make sure to set up your data path in Config. Saving crosses will save it to your data_path in a file named crossOutput.txt");
	
	//Buttons
	private JButton primeLoad;
	private JButton primeSave;
	private JButton primeBrowse;
	private JButton crossLoad;
	private JButton crossSave;
	private JButton crossBrowse;
	private JButton generatePrimesButton;
	private JButton generateCrossesButton;
	
	//MainPanel
	MainPanel mainPanel;
	JFrame frame;
	
	MainWindow(String name, PrimeOperations p)
	{	
		m_Primes = new PrimeOperations();
		generateMainWindow(name);
	}

	
	//Used to create the main window
	protected void generateMainWindow(String name) {
		
		//Main Frame
		frame = new JFrame(name);
		frame.setBackground(Color.decode("#500000"));
		frame.setSize(1000, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Main Panel
		mainPanel = new MainPanel();
		frame.add(mainPanel);
		placeComponents(mainPanel);
		frame.setVisible(true);
	}
	
	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		
		//Prime Label
		JLabel primesFileLabel = new JLabel("Primes File");
		primesFileLabel.setBounds(10,55,200,40);
		primesFileLabel.setForeground(Color.white);
		primesFileLabel.setFont(new Font("Arial",
								Font.PLAIN, 30));
		panel.add(primesFileLabel);
		
		//Primes Generated Label
		lblPrimesGenerated = new JLabel("Primes generated: 0");
		lblPrimesGenerated.setBounds(385,60,500,40);
		lblPrimesGenerated.setForeground(Color.white);
		lblPrimesGenerated.setFont(new Font(lblPrimesGenerated.getFont().getName(),
				lblPrimesGenerated.getFont().getStyle(), 20));
		panel.add(lblPrimesGenerated);
		
		//Prime TextField
		primeTextField.setBounds(10,20,850,26);
		panel.add(primeTextField);
		
		//Prime TextFieldLabel
		primeTextFieldLabel.setBounds(10, -5, 1000, 30);
		primeTextFieldLabel.setForeground(Color.white);
		primeTextFieldLabel.setFont(new Font(primeTextFieldLabel.getFont().getName(),
				primeTextFieldLabel.getFont().getStyle(), 12));
		panel.add(primeTextFieldLabel);
		
		//Prime Load Button
		primeLoad = new JButton("Load");
		primeLoad.setBounds(760, 60, 100, 30);
		primeLoad.setForeground(Color.black);
		primeLoad.setName("Prime Load");
		primeLoad.addActionListener(this);
		panel.add(primeLoad);
		
		//Prime Save Button
		primeSave = new JButton("Save");
		primeSave.setBounds(870, 60, 100, 30);
		primeSave.setForeground(Color.black);
		primeSave.setName("Prime Save");
		primeSave.addActionListener(this);
		panel.add(primeSave);
		
		//Prime Browse Button
		primeBrowse = new JButton("Browse");
		primeBrowse.setBounds(870, 18, 100, 30);
		primeBrowse.setForeground(Color.black);
		primeBrowse.setName("Prime Browse");
		primeBrowse.addActionListener(this);
		panel.add(primeBrowse);
		
		//Cross Label
		JLabel crossFileLabel = new JLabel("Hexagon Cross File");
		crossFileLabel.setBounds(10,155,400,40);
		crossFileLabel.setForeground(Color.white);
		crossFileLabel.setFont(new Font(crossFileLabel.getFont().getName(),
				crossFileLabel.getFont().getStyle(), 30));
		panel.add(crossFileLabel);
		
		//Crosses Generated Label
		lblCrossesGenerated = new JLabel("Crosses Generated: 0");
		lblCrossesGenerated.setBounds(380,160,400,40);
		lblCrossesGenerated.setForeground(Color.white);
		lblCrossesGenerated.setFont(new Font(lblCrossesGenerated.getFont().getName(),
				lblCrossesGenerated.getFont().getStyle(), 20));
		panel.add(lblCrossesGenerated);
		
		//Cross TextField
		crossTextField.setBounds(10,120,850,26);
		panel.add(crossTextField);
		
		//Cross TextField Label
		crossTextFieldLabel.setBounds(10, 96, 1000, 30);
		crossTextFieldLabel.setForeground(Color.white);
		crossTextFieldLabel.setFont(new Font(crossTextFieldLabel.getFont().getName(),
				crossTextFieldLabel.getFont().getStyle(), 12));
		panel.add(crossTextFieldLabel);
		
		//Cross Load Button
		crossLoad = new JButton("Load");
		crossLoad.setBounds(760, 160, 100, 30);
		crossLoad.setForeground(Color.black);
		crossLoad.setName("Cross Load");
		crossLoad.addActionListener(this);
		panel.add(crossLoad);
		
		//Cross Save Button
		crossSave = new JButton("Save");
		crossSave.setBounds(870, 160, 100, 30);
		crossSave.setForeground(Color.black);
		crossSave.setName("Cross Save");
		crossSave.addActionListener(this);
		panel.add(crossSave);
		
		//Cross Browse Button
		crossBrowse = new JButton("Browse");
		crossBrowse.setBounds(870, 118, 100, 30);
		crossBrowse.setForeground(Color.black);
		crossBrowse.setName("Cross Browse");
		crossBrowse.addActionListener(this);
		panel.add(crossBrowse);
		
		//Generate Primes Button
		generatePrimesButton = new JButton("Generate Primes");
		generatePrimesButton.setBounds(10, 240, 200, 70);
		generatePrimesButton.setForeground(Color.black);
		generatePrimesButton.setName("Generate Primes");
		generatePrimesButton.addActionListener(this);
		panel.add(generatePrimesButton);
		
		//Largest Primes Length Label
		lblLengthLargestPrime = new JLabel("The largest prime has 0 digits");
		lblLengthLargestPrime.setBounds(375, 250, 500, 30);
		lblLengthLargestPrime.setForeground(Color.white);
		lblLengthLargestPrime.setFont(new Font(lblLengthLargestPrime.getFont().getName(),
				lblLengthLargestPrime.getFont().getStyle(), 16));
		panel.add(lblLengthLargestPrime);
		
		//Largest Crosses Length Label
		lblLengthLargestCrosses = new JLabel("The largest hexagon cross has 0 digits and 0 digits");
		lblLengthLargestCrosses.setBounds(300, 270, 500, 30);
		lblLengthLargestCrosses.setForeground(Color.white);
		lblLengthLargestCrosses.setFont(new Font(lblLengthLargestCrosses.getFont().getName(),
				lblLengthLargestCrosses.getFont().getStyle(), 16));
		panel.add(lblLengthLargestCrosses);
		
		//Generate Crosses Button
		generateCrossesButton = new JButton("Generate Crosses");
		generateCrossesButton.setBounds(770, 240, 200, 70);
		generateCrossesButton.setForeground(Color.black);
		generateCrossesButton.setName("Generate Crosses");
		generateCrossesButton.addActionListener(this);
		panel.add(generateCrossesButton);
		
		//Status Label
		lblStatus.setBounds(10, 320, 1000, 30);
		lblStatus.setForeground(Color.white);
		lblStatus.setText("Status: Waiting");
		lblStatus.setFont(new Font(lblStatus.getFont().getName(),
				lblStatus.getFont().getStyle(), 16));
		panel.add(lblStatus);
	}
	
	//actionListener for buttons on main screen
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == primeSave) {				//Prime Save Button
			String outputPath = Config.DATA_PATH + "primeOutput.txt";
			boolean success = FileAccess.savePrimes(m_Primes,  outputPath);
			if (success) {	//Happy Path
				lblStatus.setText("File Saved to " +  outputPath);
			} else {		//Sad Path
				lblStatus.setText("Error saving primes");
			}
			
			frame.revalidate();
			frame.repaint();
			
		} else if (e.getSource() == primeLoad) {		//Prime Load Button
			Config.PRIME_PATH = Config.DATA_PATH + primeTextField.getText();
			boolean success = FileAccess.loadPrimes(m_Primes, Config.PRIME_PATH);
			m_Primes.printPrimes();
			
			if (success) {	//Happy Path
				lblStatus.setText("Primes have been generated");
				updateStats();
			} else {		//Sad Path
				lblStatus.setText("Error loading primes");
			}
			
			frame.revalidate();
			frame.repaint();
			
		} else if (e.getSource() == primeBrowse) {		//Prime Browse Button
			String fileName = getFile();
			primeTextField.setText(fileName);
			
		} else if (e.getSource() == crossSave) {		//Cross Save Button
			String outputPath = Config.DATA_PATH + "crossOutput.txt";
			boolean success = FileAccess.saveCrosses(m_Primes, outputPath);
			
			if (success) {	//Happy Path
				lblStatus.setText("File saved to " + outputPath);
			} else {		//Sad Path
				lblStatus.setText("Error saving crosses");
			}
			
			frame.revalidate();
			frame.repaint();
			
		} else if (e.getSource() == crossLoad) {		//Cross Load Button
			Config.CROSS_PATH = Config.DATA_PATH + crossTextField.getText();
			boolean success = FileAccess.loadCrosses(m_Primes, Config.CROSS_PATH);
			m_Primes.printHexes();
			
			if (success) {	//Happy Path
				lblStatus.setText("Crosses have been loaded.");
				updateStats();
			} else {		//Sad Path
				lblStatus.setText("Error loading crosses.");
			}
			
			frame.revalidate();
			frame.repaint();
			
		} else if (e.getSource() == crossBrowse) {		//Cross Browse Button
			String fileName = getFile();
			crossTextField.setText(fileName);
			
		} else if (e.getSource() == generatePrimesButton) {		//Primes Generate Button
			popupGeneratePrimes();
		} else if (e.getSource() == generateCrossesButton) {	//Cross Generate Button
			popupGenerateCrosses();
		}
	}
	
	//Fuction used to retrieve a file from the browser
	private String getFile() {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setDialogTitle("Choose a file to open: ");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int success = fileChooser.showOpenDialog(null);
		if (success == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String fileName = selectedFile.getName();
			return fileName;
		}
		return "";
	}
	
	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(Color.decode("500000"));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start.intValue(), count);
       		m_Primes.printPrimes();
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		updateStats();
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	protected void popupGenerateCrosses() {
		JDialog dCrosses = new JDialog(this, "Hexagon Cross Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dCrosses.getContentPane().setBackground(Color.decode("#500000"));
		dCrosses.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Crosses to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dCrosses.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGenerateCrosses = new JButton("Generate Crosses");
		btnGenerateCrosses.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	  	try
	  	{
	  		BigInteger start = new BigInteger(tfStart.getText());
	  		int count = Integer.parseInt(tfCount.getText());
	   		m_Primes.generateHexPrimes(start.intValue(), count);
	   		m_Primes.printHexes();
	   		lblStatus.setText("Status: Excited. Crosses have been generated.");
	   		updateStats();
	  		dCrosses.dispose();
	  	}
	  	catch(NumberFormatException ex)
	  	{
	  		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
	  		updateStats();
	  		dCrosses.dispose();
	  	}
	  }
	});
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGenerateCrosses, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	  	dCrosses.dispose();
      }
	});
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dCrosses.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());
	
		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
	
		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dCrosses.add(pnlStatus, gbcDialog);
		
		dCrosses.setSize(200,200);
		dCrosses.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dCrosses.setVisible(true);	
	}
	
	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		//Get prime and cross size
		int primesGenerated = m_Primes.getPrimeList().size();
		int crossesGenerated = m_Primes.getHexagonCrossList().size();
		lblPrimesGenerated.setText("Primes generated: " + primesGenerated);
		lblCrossesGenerated.setText("Crosses generated: " + crossesGenerated);
		
		//Get largest prime digit
		if (m_Primes.getPrimeList().size() != 0) {
			BigInteger largestPrime = m_Primes.getPrimeList().get(m_Primes.getPrimeList().size() - 1);
			int lengthLargestPrime = String.valueOf(largestPrime).length();
			lblLengthLargestPrime.setText("The largest prime has " + lengthLargestPrime + " digits");
		}
		
		//Get largest hexagon cross digit
		if (m_Primes.getHexagonCrossList().size() != 0) {
			Pair<BigInteger> largestCrossPair = m_Primes.getHexagonCrossList().get(m_Primes.getHexagonCrossList().size() - 1);
			BigInteger largestCross1 = largestCrossPair.getPairVal1();
			BigInteger largestCross2 = largestCrossPair.getPairVal2();
			int lengthLargestCross1 = String.valueOf(largestCross1).length();
			int lengthLargestCross2 = String.valueOf(largestCross2).length();
			lblLengthLargestCrosses.setText("The largest hexagon cross has " + lengthLargestCross1 + " digits and " + lengthLargestCross2 + " digits");
		}
		
		frame.revalidate();
		frame.repaint();
		
 	}

}
