import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

public class SNPFFrame1 {

	private JFrame frame;
	private JTextField inputUserFile;
	private JTextField inputRefFile;
	private JTextField outputDir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SNPFFrame1 window = new SNPFFrame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SNPFFrame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 958, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Start Program");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(305, 397, 293, 58);
		frame.getContentPane().add(btnNewButton);
		
	
		
		
		JLabel lblSnpMatcher = new JLabel("SNP Matcher");
		lblSnpMatcher.setFont(new Font("Arial", Font.PLAIN, 36));
		lblSnpMatcher.setBounds(332, 16, 230, 43);
		frame.getContentPane().add(lblSnpMatcher);
		
		JTextArea txtrThisProgramMatches = new JTextArea();
		txtrThisProgramMatches.setForeground(SystemColor.desktop);
		txtrThisProgramMatches.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrThisProgramMatches.setBackground(Color.BLACK);
		txtrThisProgramMatches.setWrapStyleWord(true);
		txtrThisProgramMatches.setLineWrap(true);
		txtrThisProgramMatches.setEnabled(false);
		txtrThisProgramMatches.setText("This program searches through a user file for Reference Cluster IDs listed in another reference file. Please ensure that both input files are in simple text format with rsIDs in single column lists in each, and that you have Java 8 already installed on your computer before you run this. Also, please enter directory paths with the appropriate Windows formatting (i.e. \"C:\\User\\JN\\Folder\\\", \"C:/User/JN/Folder/\", \"C:\\User\\JN\\Folder\\file.txt\"). - Jacklyn Newsome");
		txtrThisProgramMatches.setBounds(15, 64, 906, 142);
		frame.getContentPane().add(txtrThisProgramMatches);
		
		inputUserFile = new JTextField();
		inputUserFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inputUserFile.setBounds(284, 222, 568, 32);
		frame.getContentPane().add(inputUserFile);
		inputUserFile.setColumns(10);
		
		JLabel lblUserFilewhole = new JLabel("User File (whole directory):");
		lblUserFilewhole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserFilewhole.setBounds(25, 216, 268, 39);
		frame.getContentPane().add(lblUserFilewhole);
		
		JLabel lblReferenceFile = new JLabel("Reference File (whole dir.):");
		lblReferenceFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReferenceFile.setBounds(15, 271, 258, 50);
		frame.getContentPane().add(lblReferenceFile);
		
		inputRefFile = new JTextField();
		inputRefFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inputRefFile.setColumns(10);
		inputRefFile.setBounds(283, 277, 569, 32);
		frame.getContentPane().add(inputRefFile);
		
		JLabel lblOutputDirectory = new JLabel("Output Directory:");
		lblOutputDirectory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOutputDirectory.setBounds(15, 351, 243, 27);
		frame.getContentPane().add(lblOutputDirectory);
		
		outputDir = new JTextField();
		outputDir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		outputDir.setColumns(10);
		outputDir.setBounds(284, 349, 568, 32);
		frame.getContentPane().add(outputDir);

	btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					String unfixedUserFileName = inputUserFile.getText();
					String fixedUserFileName = 	unfixedUserFileName.replace("/", "\\");
					String unfixedRefFileName = inputRefFile.getText();
					String fixedRefFileName = unfixedRefFileName.replace("/", "\\");
					String unfixedOutDirName = outputDir.getText();
					String fixedOutDirName = unfixedOutDirName.replace("/", "\\");
					
					String outname = SNPFind2.runProgram( fixedUserFileName, fixedRefFileName, fixedOutDirName);
					String message = "File \"" + outname + "\" has been created.";
					JOptionPane.showMessageDialog(null, message);
					
				} catch(Exception e){
						
						JOptionPane.showMessageDialog(null, "Please enter proper directory and file text");
					}
					
				
				
			}
			
			
			
	});
		
		
		
	
		
		
		
		
	}
}
