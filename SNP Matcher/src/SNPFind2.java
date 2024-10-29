import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SNPFind2 {

	
	public static String runProgram ( String user, String ref, String out ) throws IOException{
		
		
		ArrayList<String> vcf = importLines(user);
		ArrayList<String> toFind = importLines(ref);
		
		ArrayList<String> foundlines = foundLines(vcf, toFind);
		System.out.println("foundlines size main = " + foundlines.size());
		String outName = SNPFind2.output(foundlines, ref, out);
		
		return outName;
	}
	
	public static ArrayList<String> importLines ( String inputName) throws IOException{
		ArrayList<String> lines = new  ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputName));
		String brLine = br.readLine();
		//int brLineNumber = 0;
		while (brLine != null ) {
			if (brLine.length() > 0) { // skip blank lines		
			//	int line_number_matcher = brLineNumber + 1 ;// used for boolean if statements to reduce parens confusion
				System.out.println("Read in = " + brLine);
				lines.add(brLine);	
				//brLineNumber++;
			}
			brLine = br.readLine();
		}
		br.close();
		return lines;
	}
	
	
	public static ArrayList<String> foundLines ( ArrayList<String> vcf, ArrayList<String> toBeFound ){
		ArrayList<String> foundLines = new ArrayList<String>();
		
		int vcfSize = vcf.size();
		int toBeFoundSize = toBeFound.size();
		
		for ( int i = 0; i < vcfSize ; ){
			for ( int j = 0 ; j < toBeFoundSize ; ){
				
				if ( vcf.get(i).equals(toBeFound.get(j) )){
					foundLines.add(vcf.get(i));	
					System.out.println("Found = " + vcf.get(i));
				}
				j++;
			}
			i++;
		}
		return foundLines;
	}
	
	
	
	
	public static String output (ArrayList<String> foundlines, String inputName, String out ) throws IOException{
		File f = new File(inputName);
		String tempName = f.getName().replace(".txt", "");
		String fileName = (out + tempName + "OUTPUT.txt");
		File outFile = new File(fileName);
		FileWriter fw = new FileWriter(outFile);
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		int foundSize = foundlines.size();
		for ( int i = 0 ; i < foundSize ; ){
			String outTemp = foundlines.get(i);
			System.out.println("Output = " + outTemp);
			bw.write( outTemp + "\n");
			
			i++;
		}
		bw.close();
		return fileName;
	}
	
	
	
	
	
}
