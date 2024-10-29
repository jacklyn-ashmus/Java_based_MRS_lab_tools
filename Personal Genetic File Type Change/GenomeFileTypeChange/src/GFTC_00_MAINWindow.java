import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Font;

public class GFTC_00_MAINWindow extends JFrame {

	private JPanel contentPane;
	private JTextField inFilePathField;
	private JTextField outFilePathField;

	static boolean inputIs_ftDNA = false;
	static boolean inputIs_ancestryDNA = false;
	static boolean inputIs_23AndMe = false;
	static boolean inputIs_diagnomics = false;
	static String inFilePath = "";
	static String outFilePath = "";
	static boolean outputIs_ftDNA = false;
	static boolean outputIs_ancestryDNA = false;
	static boolean outputIs_23AndMe = false;
	static boolean outputIs_diagnomics = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GFTC_00_MAINWindow frame = new GFTC_00_MAINWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GFTC_00_MAINWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 842);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImportFileType = new JLabel("Input File Type:");
		lblImportFileType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblImportFileType.setBounds(46, 153, 236, 37);
		contentPane.add(lblImportFileType);
		
		ButtonGroup inButtonGroup = new ButtonGroup();
		ButtonGroup outButtonGroup = new ButtonGroup();
		 
		JRadioButton ftDNAInputButton = new JRadioButton("ftDNA");
		ftDNAInputButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ftDNAInputButton.setBounds(79, 309, 90, 29);
		contentPane.add(ftDNAInputButton);
		
		JRadioButton ancestryDNAInputButton = new JRadioButton("AncestryDNA");
		ancestryDNAInputButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ancestryDNAInputButton.setBounds(79, 260, 155, 29);
		contentPane.add(ancestryDNAInputButton);
		
		JRadioButton twenAndMeInputButton = new JRadioButton("23AndMe");
		twenAndMeInputButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		twenAndMeInputButton.setBounds(79, 216, 155, 29);
		contentPane.add(twenAndMeInputButton);
		
	//	JRadioButton diagnomicsInputButton = new JRadioButton("Diagnomics");
	//	diagnomicsInputButton.setBounds(133, 83, 155, 29);
	//	contentPane.add(diagnomicsInputButton);
		
		JRadioButton ftDNAOutputButton = new JRadioButton("ftDNA");
		ftDNAOutputButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ftDNAOutputButton.setBounds(630, 309, 109, 29);
		contentPane.add(ftDNAOutputButton);
		
		JRadioButton ancestryDNAOutputButton = new JRadioButton("AncestryDNA");
		ancestryDNAOutputButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ancestryDNAOutputButton.setBounds(630, 262, 155, 29);
		contentPane.add(ancestryDNAOutputButton);
		
		JRadioButton twenAndMeOutputButton = new JRadioButton("23AndMe");
		twenAndMeOutputButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		twenAndMeOutputButton.setBounds(630, 216, 155, 29);
		contentPane.add(twenAndMeOutputButton);
		
	//	JRadioButton diagnomicsOutputButton = new JRadioButton("Diagnomics");
	//	diagnomicsOutputButton.setBounds(510, 83, 155, 29);
	//	contentPane.add(diagnomicsOutputButton);
		
		JLabel lblOutputFileType = new JLabel("Output File Type:");
		lblOutputFileType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOutputFileType.setBounds(611, 149, 265, 44);
		contentPane.add(lblOutputFileType);
		
		inFilePathField = new JTextField();
		inFilePathField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inFilePathField.setBounds(44, 388, 519, 37);
		contentPane.add(inFilePathField);
		inFilePathField.setColumns(10);
		
		outFilePathField = new JTextField();
		outFilePathField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		outFilePathField.setColumns(10);
		outFilePathField.setBounds(611, 388, 579, 37);
		contentPane.add(outFilePathField);
		
		JLabel lblInputFilePath = new JLabel("Input File Path:");
		lblInputFilePath.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInputFilePath.setBounds(45, 350, 215, 26);
		contentPane.add(lblInputFilePath);
		
		JLabel lblOutputFilePath = new JLabel("Output File Path:");
		lblOutputFilePath.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOutputFilePath.setBounds(611, 350, 281, 29);
		contentPane.add(lblOutputFilePath);
		
		JLabel lblNoteFilePaths = new JLabel("- File paths must be entered with whole path and forward slashes");
		lblNoteFilePaths.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNoteFilePaths.setBounds(46, 477, 732, 26);
		contentPane.add(lblNoteFilePaths);
		
		JLabel lblExChomefolderfolderancestryfilecsv = new JLabel("Ex.: C:/Home/folder/folder/ancestryFile.csv");
		lblExChomefolderfolderancestryfilecsv.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblExChomefolderfolderancestryfilecsv.setBounds(153, 506, 586, 20);
		contentPane.add(lblExChomefolderfolderancestryfilecsv);
		
		JButton btnRunProgram = new JButton("Run Program");
		btnRunProgram.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnRunProgram.setBounds(457, 656, 328, 98);
		contentPane.add(btnRunProgram);
		
		
	//	inButtonGroup.add(diagnomicsInputButton);
		inButtonGroup.add(twenAndMeInputButton);
		inButtonGroup.add(ancestryDNAInputButton);
		inButtonGroup.add(ftDNAInputButton);
		
	//	outButtonGroup.add(diagnomicsOutputButton);
		outButtonGroup.add(twenAndMeOutputButton);
		outButtonGroup.add(ancestryDNAOutputButton);
		outButtonGroup.add(ftDNAOutputButton);
		
		JLabel lblAlsoFtdnaAnd = new JLabel("- ftDNA and MyHeritage formats are identical, so just select \"ftDNA\" to  convert to or from MyHeritage files");
		lblAlsoFtdnaAnd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAlsoFtdnaAnd.setBounds(46, 537, 1102, 37);
		contentPane.add(lblAlsoFtdnaAnd);
		
		JLabel lblGenomeFileType = new JLabel("Genome File Type Converter");
		lblGenomeFileType.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblGenomeFileType.setBounds(406, 16, 443, 70);
		contentPane.add(lblGenomeFileType);
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNotes.setBounds(29, 441, 78, 35);
		contentPane.add(lblNotes);
		
		JLabel lblIfYou = new JLabel("- If you leave the output file path blank, an output file name will automatically be assigned.");
		lblIfYou.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIfYou.setBounds(56, 573, 1053, 29);
		contentPane.add(lblIfYou);
		
		JLabel lblTheOutputFile = new JLabel("The output file will be created in the same directory as the input file.");
		lblTheOutputFile.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTheOutputFile.setBounds(90, 611, 950, 29);
		contentPane.add(lblTheOutputFile);
		
		
		btnRunProgram.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
			//	inputIs_diagnomics = diagnomicsInputButton.isSelected();
				inputIs_23AndMe = twenAndMeInputButton.isSelected();
				inputIs_ancestryDNA = ancestryDNAInputButton.isSelected();
				inputIs_ftDNA = ftDNAInputButton.isSelected();
				
			//	outputIs_diagnomics = diagnomicsOutputButton.isSelected();
				outputIs_23AndMe = twenAndMeOutputButton.isSelected();
				outputIs_ancestryDNA = ancestryDNAOutputButton.isSelected();
				outputIs_ftDNA = ftDNAOutputButton.isSelected();
				
				inFilePath = inFilePathField.getText();
				outFilePath = outFilePathField.getText();
				System.out.println("outFilePath = " +  outFilePath);
				if ((outFilePath == null ) || outFilePath.isEmpty() || (outFilePath.length() == 0)){	outPathMaker();	}
				System.out.println("inFilePath = " +  inFilePath);
				System.out.println("outFilePath = " +  outFilePath);
				try {
					boolean noIn = false;
					boolean noOut = false;
					boolean noInPath = false;
					if (!inputIs_23AndMe && !inputIs_ancestryDNA && !inputIs_ftDNA){	noIn = true;	}
					if (!outputIs_23AndMe && !outputIs_ancestryDNA && !outputIs_ftDNA){
						JOptionPane.showMessageDialog(null, "Please select an output type.");
					}
					if (inFilePath.length() == 0){
						noInPath = true;
					}
					if (noIn || noOut || noInPath){
						if (noIn){	JOptionPane.showMessageDialog(null, "Please select an input type.");	}
						if (noOut){	JOptionPane.showMessageDialog(null, "Please select an output type.");	}
						if (noInPath){	JOptionPane.showMessageDialog(null, "Please enter an input file path");	}
						
					}	else	{
						GFTC_04_FileOut.processFile();
						JOptionPane.showMessageDialog(null, "Done!");
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					if (inFilePath.length() == 0){
						JOptionPane.showMessageDialog(null, "please enter an input file path");
					}
					
					JOptionPane.showMessageDialog(null, "Couldn't process file.");

				}
				
				
			} 
				} );
		
	}
	
	
	
	static void outPathMaker (){
		
		String outTemp = inFilePath;
		
		if ( outTemp.contains(".txt")){
			outTemp = outTemp.replace(".txt", "");
			
			if (outputIs_23AndMe){
				outTemp = outTemp + "_23AndMe.txt";
			}
			if (outputIs_ancestryDNA){
				outTemp = outTemp + "_AncestryDNA.csv";
			}
			if (outputIs_ftDNA){
				outTemp = outTemp + "_ftDNA.txt";
			}
			
		}
		if ( outTemp.contains(".csv")){
			outTemp = outTemp.replace(".csv", "");
			
			if (outputIs_23AndMe){
				outTemp = outTemp + "_23AndMe.txt";
			}
			if (outputIs_ancestryDNA){
				outTemp = outTemp + "_AncestryDNA.csv";
			}
			if (outputIs_ftDNA){
				outTemp = outTemp + "_ftDNA.txt";
			}
			
		}
		outFilePath = outTemp;
	}
	
	
}
