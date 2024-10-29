import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PullerMAIN {

	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		//    E:/_RNASeqRon100217-48522478/FNegSet2-Ron100217_S3_L001_R1_001.fq
		
		String fileName = "";
		int numFiles = 0;
		int numReadsPerFile = 0;
		String outputFolder = "";
		
		if (args.length < 4) {
			
			System.out.println("Please enter arguments as: inputFileName numberOfFilesToGenerate numberOfReadsPerGeneratedFile OutputFolder");
			System.exit(0);
		}	else	{
			fileName = args[0].trim();
			numFiles = Integer.parseInt(args[1].trim());
			numReadsPerFile = Integer.parseInt(args[2].trim());
			outputFolder = args[3].trim();
		}
		
		
		System.out.println();
		
		System.out.println("File name = " + fileName);
		System.out.println("number of files to generate = " + numFiles);
		System.out.println("number reads per generated file = " + numReadsPerFile);
		System.out.println("outputFolder = " + outputFolder);
		
		int currentFile = 0;
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		int lineNum = 0;
		int c = (currentFile);
		String fileName2 = outputFolder + file.getName().replace(".fastq", "").replace(".fq", "") + "_SAMPLE" + c + ".fq";
		System.out.println("fileName2 = " + fileName2);
		File file2 = new File(fileName2);
		FileWriter fw = new FileWriter(file2);
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println( "START    " + fileName );
		String ngsLine = br.readLine();
		while (ngsLine != null ) {
			bw.write(ngsLine + "\n");
			
			ngsLine = br.readLine();
			if ( lineNum == (numReadsPerFile * 4)){
				bw.close();
				currentFile++;
				fileName2 = outputFolder + file.getName().replace(".fastq", "").replace(".fq", "") + "_SAMPLE" + currentFile + ".fq";
				file2 = new File(fileName2);
				fw = new FileWriter(file2);
				bw = new BufferedWriter(fw);
				System.out.println( "END    " + fileName2 );
				if (currentFile > numFiles){
					bw.close();
					break;
				}
				lineNum = -1;
			}
			
			lineNum++;
		}
		
		System.out.println( "END    " + fileName2 );
		
		
		br.close();
	}

}
