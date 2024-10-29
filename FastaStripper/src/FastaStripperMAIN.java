import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class FastaStripperMAIN {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	//	String inputName = "C:/Users/Jackie/Downloads/_happy/Ruminococcus_bicirculans.Rb803.cdna.all.fa";
	//	String outputName = "C:/Users/Jackie/Downloads/_happy/uminococcus_bicirculans_FIXED.txt";
		
	//	ArrayList<String> list = readFile(inputName);
		
	//	outputStringList(list, outputName);
	//	System.out.println("DONE!! # of genes = " + list.size());
		
		String speciesFileName = args[0];
		String cliqueFileName = args[1];
		String outputName = args[2];
		
		// import clique file

		
		// import fasta files
		
		
	//	ArrayList<String> fastaFiles = new ArrayList<String>();
	
		ArrayList<Species> sppList = importSpeciesFileNames(speciesFileName);

		ArrayList<Clique> cliqueList = importCliqueFile(cliqueFileName);
		
		
		ArrayList<CliqueMember> finalCliqueMatchList = matchCliqueGenes (sppList, cliqueList);
		
		outputCliqueInfo(outputName, finalCliqueMatchList);
		
		
		
		// V 2 
	//	int nStringsPerFile = 100;
	/*	String in1 = "";
		String in2 = "";
		String in3 = "";
		String in4 = "";
		String in5 = "";
		String in6 = ""; 
		String in7 = "";
		String in8 = "";
		String in9 = "";
		String in10 = "";
		String out1 = "";
		*/
		
	/*	String in1 = "C:/Users/Jackie/Downloads/_sad/Alistipes_inops_FIXED.txt";
		String in2 = "C:/Users/Jackie/Downloads/_sad/Clostridium_sporogenes_FIXED.txt";
		String in3 = "C:/Users/Jackie/Downloads/_sad/Erysipelotrichaceae_bacterium_FIXED.txt";
		String in4 = "C:/Users/Jackie/Downloads/_sad/Flavonifractor_plautii_FIXED.txt";
		String in5 = "C:/Users/Jackie/Downloads/_sad/Hafnia_alvei_FIXED.txt";
		String in6 = "C:/Users/Jackie/Downloads/_sad/Klebsiella_pneumoniae_FIXED.txt"; 
		String in7 = "C:/Users/Jackie/Downloads/_sad/Mucinivorans_hirudinis_FIXED.txt";
		String in8 = "C:/Users/Jackie/Downloads/_sad/Parabacteroides_distasonis_FIXED.txt";
		String in9 = "C:/Users/Jackie/Downloads/_sad/Porphyromonadaceae_bacterium_FIXED.txt";
		String out1 = "C:/Users/Jackie/Downloads/_sad/SmallSad_" + nStringsPerFile + "_GenesPerSpecies.txt";
		
		ArrayList<String> inList = new ArrayList<String>();
		inList.add(in1);
		inList.add(in2);
		inList.add(in3);
		inList.add(in4);
		inList.add(in5);
		inList.add(in6);
		inList.add(in7);
		inList.add(in8);
		inList.add(in9);
		
		
		
		
		
	
		ArrayList<String> outList = collectFiles(inList, nStringsPerFile);
		outputStringList(outList, out1);
		
		
	     in1 = "C:/Users/Jackie/Downloads/_happy/Anaerotruncus_colihominis_FIXED.txt";
		 in2 = "C:/Users/Jackie/Downloads/_happy/Dialister_invisus_FIXED.txt";
		 in3 = "C:/Users/Jackie/Downloads/_happy/Escherichia_coli_FIXED.txt";
		 in4 = "C:/Users/Jackie/Downloads/_happy/Faecalibacterium_prausnitzii_FIXED.txt";
		 in5 = "C:/Users/Jackie/Downloads/_happy/FaeHaemophilus_parainfluenzae_FIXED.txt";
		 in6 = "C:/Users/Jackie/Downloads/_happy/Pasteurellaceae_bacterium_FIXED.txt"; 
		 in7 = "C:/Users/Jackie/Downloads/_happy/Peptostreptococcaceae_bacterium_FIXED.txt";
		 in8 = "C:/Users/Jackie/Downloads/_happy/Ruminococcus_albus_FIXED.txt";
		 in9 = "C:/Users/Jackie/Downloads/_happy/ruminococcus_bicirculans_FIXED.txt";
		 out1 = "C:/Users/Jackie/Downloads/_happy/SmallHappy_" + nStringsPerFile + "_GenesPerSpecies.txt";
		
		 inList = new ArrayList<String>();
		 inList.add(in1);
		 inList.add(in2);
		 inList.add(in3);
		 inList.add(in4);
		 inList.add(in5);
		 inList.add(in6);
		 inList.add(in7);
		 inList.add(in8);
		 inList.add(in9);
		 outList = collectFiles(inList, nStringsPerFile);
			outputStringList(outList, out1);
			*/
			
			System.out.println("Done!");
		
	}

	
	
	
	
	
	
	static ArrayList<Species> importSpeciesFileNames (String inFileName) throws IOException{
		ArrayList<Species> sppList = new ArrayList<Species>();
		File f = new File(inFileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		ArrayList<String> gene = new ArrayList<String>();
		int lineCount = 0;
		while (line != null) {
			if (line.length() > 0) {
				Species s1 = new Species();
				String[] split = line.split(",");
				s1.fileName = split[0].trim();
				if (split[1].trim().equals("sad")) {
					s1.isHappy = false;
				}	else	{
					s1.isHappy = true;
				}
				s1.spName = split[2].trim();
				sppList.add(s1);
			}
			lineCount++;
			line = br.readLine();
		}
		br.close();
		return sppList;
	}
	
	static ArrayList<Species> importGenes (ArrayList<Species> spp) throws IOException{
		ArrayList<Species> sppFixed = new ArrayList<Species>();
		for (int i = 0; i < spp.size(); i++) {
			ArrayList<Header_Gene> hgList = readFileKeepHeader(spp.get(i).fileName);
			Species sp = spp.get(i);
			sp.genes = parseGenes(hgList, sp);
			sppFixed.add(sp);
		}
		return sppFixed;
	}
	
	
	static ArrayList<Gene> parseGenes (ArrayList<Header_Gene> hgList, Species sp){
		ArrayList<Gene> geneList = new ArrayList();
		for (int i = 0; i < hgList.size(); i++) {
			Gene g = new Gene();
			g.seq = hgList.get(i).gene;
			
			
			String[] split = hgList.get(i).header.replaceAll(">","").split(" ");
			
			
			
			g.name = split[0].trim();  // >KRU35927 cdna
			g.cdnaThing = split[1].trim();// cdna
			g.superContigThing = split[2].replaceAll("supercontig:","").trim(); // supercontig:ASM144469v1
			g.superContigThing2 = split[3].trim(); // VT92_contig036
			String[] split2 = split[4].split(":");
			g.start = Integer.parseInt(split2[0].trim()); // 370:1395:1
			g.start = Integer.parseInt(split2[1].trim()); // 370:1395:1 = Integer.parseInt(split2[1].trim()); // 370:1395:1
			g.thirdnumberidontevenfuckingknow = Integer.parseInt(split2[3].trim()); // 370:1395:1
			g.geneNameThing2 = split[4].replace("gene:", "").trim(); // gene:VT92_21050
			g.biotypeThing = split[5].replace("gene_biotype:", "").trim(); // gene_biotype:protein_coding
			g.transcriptBiotype = split[6].replace("transcript_biotype:", "").trim(); // transcript_biotype:protein_coding
			String description = split[6].replace("description:", "").trim(); //   description:3D/G5 domain-containing protein
			g.species = sp.spName;
			g.isHappy = sp.isHappy;
			
			geneList.add(g);
			
			
		}
		
		
		return geneList;
	}
	
	
	
	static ArrayList<String> collectFiles (ArrayList<String> inList, int nStringsPerFile) throws IOException{
		
		ArrayList<String> geneList = new ArrayList<String>();
		
		
		for (int i = 0; i < inList.size(); i++) {
			ArrayList<String> newStrings =  collectFilesInput(inList.get(i), nStringsPerFile) ;
			geneList.addAll(newStrings);
			
			
		}
		
		return geneList;
	}
	
	static ArrayList<String> collectFilesInput (String inFileName, int nStringsPerFile) throws IOException{
		
		ArrayList<String> geneStrings = new ArrayList<String>();
		File f = new File(inFileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		ArrayList<String> gene = new ArrayList<String>();
		int lineCount = 0;
		while (line != null) {
			
			if (lineCount < nStringsPerFile) {
				geneStrings.add(line.trim());
			}	else	{
				break;
			}
			lineCount++;
			line = br.readLine();
		}
		
		br.close();

		return geneStrings;
	}
	

	
	
	static ArrayList<Clique> importCliqueFile (String inFileName) throws IOException{
		
		ArrayList<Clique> cliques = new ArrayList<Clique>();
		File f = new File(inFileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		ArrayList<String> gene = new ArrayList<String>();
		int lineCount = 0;
		while (line != null) {
			
			if (line.length() > 0 ) {
				Clique c = new Clique();
				String[] split = line.split(",");
				for (int i = 0; i < split.length; i++) {
					c.genes.add(split[i]);
				}
				cliques.add(c);
			}	
			line = br.readLine();
		}
		
		br.close();

		return cliques;
	}
	
	
	
	
	
	
	 static void outputStringList (ArrayList<String> result, String outputName) throws IOException {
		
		 FileWriter fw = new FileWriter(outputName);
		 BufferedWriter bw = new BufferedWriter(fw);
		 for (int i = 0; i < result.size(); i++) {
			// System.out.println(result.get(i));
			 if (i < result.size() - 1) {
				 bw.write(result.get(i) + "\n");
			 }	else	{
				 bw.write(result.get(i));
			 }
		 }
		 bw.close();
	 }
	 
	
	static ArrayList<String> readFile (String fileName) throws IOException {
		ArrayList<String> finalStrings = new ArrayList<String>();
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		ArrayList<String> gene = new ArrayList<String>();
		while (line != null) {
			
			if (line.startsWith(">")) {
				
				if (gene.size() > 0) {
					String geneConcat = concatList(gene);
					finalStrings.add(geneConcat);
					gene = new ArrayList<String>();
				}
			}	else	{
				if (line.length() > 0) {
					gene.add(line.trim());
				}
			}
			
			line = br.readLine();
		}
		
		br.close();
		return finalStrings;
	}

	
	static ArrayList<Header_Gene> readFileKeepHeader (String fileName) throws IOException {
		ArrayList<Header_Gene> finalStrings = new ArrayList<Header_Gene>();
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		ArrayList<String> gene = new ArrayList<String>();
		Header_Gene hg = new Header_Gene();
		String geneheaderNext = "";
		while (line != null) {
			
			if (line.startsWith(">")) {
				
				if (gene.size() > 0) {
					hg.gene = concatList(gene);
					hg.header = geneheaderNext;
					
					finalStrings.add(hg);
					gene = new ArrayList<String>();
				}
				geneheaderNext = line;
			}	else	{
				if (line.length() > 0) {
					gene.add(line.trim());
				}
			}
			
			line = br.readLine();
		}
		
		br.close();
		return finalStrings;
	}

	
	
	
	
	static String concatList (ArrayList<String> list) {
		
		String s = "";
		
		for (int i = 0; i < list.size(); i++) {
			
			s = s + list.get(i);
		}
		
		return s;
	}

	
	static class CliqueMember {
		Gene gene = new Gene();
		int cliqueNumber = -1; // cliqueNumber = cliqueList entry + 1
	}
	
	static class Clique {
		
		ArrayList<String> genes = new ArrayList<String>();
		
	}
	
	
	static ArrayList<CliqueMember> matchCliqueGenes (ArrayList<Species> spp, ArrayList<Clique> cliques){
		ArrayList<CliqueMember> cliqMems = new ArrayList<CliqueMember>();
		 HashMap<String, Gene> map = new HashMap<String, Gene>();
		
		for (int i = 0 ; i < spp.size(); i++) {
			Species sp = spp.get(i);
			for (int j = 0; j < sp.genes.size(); i++) {
				map.put(sp.genes.get(i).seq, sp.genes.get(i));
			}
		}
		
		 for (int i = 0; i < cliques.size(); i++) {
			 CliqueMember cm = new CliqueMember();
			 cm.cliqueNumber = i + 1;
			 Clique c = cliques.get(i);
			 for (int j = 0; j < c.genes.size(); j++) {
				 if (map.containsKey(c.genes.get(j))) {
					cm.gene = map.get(c.genes.get(j));
					
				 }	else	{
					cm.gene.name = "NOT FOUND IN REAL DATA SET";
				 }
			 } 
			 cliqMems.add(cm);
			 
		 }
		 
		 return cliqMems;
	}
	
	 
	
	static void outputCliqueInfo (String outFileName, ArrayList<CliqueMember> cliques) throws IOException {
		
		File f = new File(outFileName);
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < cliques.size(); i++) {
			
			
			
			
		}
		
		
		
		
		
		bw.close();
		
	}
	
	
	
	
/*	static Gene headerParse (String head) {
		Gene g = new Gene();
		
		head = head.replace(">", "");
		String[] s = head.split(" ");
		
		g.name = s[0];
		g.cdnaThing = s[1];
		
		String t = s[2].replaceAll("supercontig", "").trim();
		String[] t1 = t.split(":");
		g.superContigThing = t1[0];
		g.superContigThing2 = t1[1];
		String temp =  s[3];
		t1 = temp.split(":");
		g.start = Integer.parseInt(t1[0].trim());
		g.end =  Integer.parseInt(t1[1].trim());
		g.thirdnumberidontevenfuckingknow = Integer.parseInt(t1[2].trim());
		g.geneNameThing2 = s[4].replace("gene:", "");
		g.biotypeThing = s[5].replace("gene_biotype:", "");
		g.transcriptBiotype = s[6].replace("transcript_biotype:", "");
		g.description = s[7].replace("description:", "");
	}
	
	
	*/
	

	static class Header_Gene {
		String gene = "";
		String header = "";
	}
	
	static class Gene {
		String seq = "";
		String name = "";  // >KRU35927 cdna
		String cdnaThing = "";// cdna
		String superContigThing = ""; // supercontig:ASM144469v1
		String superContigThing2 = ""; // VT92_contig036
		int start = -1; // 370:1395:1
		int end = -1;
		int thirdnumberidontevenfuckingknow = -1; // 
		String geneNameThing2 = ""; // gene:VT92_21050
		String biotypeThing = ""; // gene_biotype:protein_coding
		String transcriptBiotype = ""; // transcript_biotype:protein_coding
		String description = ""; //   description:3D/G5 domain-containing protein
		String species = "";
		int cliqueNumber = -1;
		boolean isHappy = false;
	}
	
	static class Species {
		
		boolean isHappy = false;
		ArrayList<Gene> genes = new ArrayList<Gene>();
		String spName = "";
		String fileName = "";
	}
	
	
	static class Arguments {
		
		String input = "";
		String output = "";
		String category = "";
		String species = ""; 
		boolean isSeqStringOnly = false;
	}
	
}
