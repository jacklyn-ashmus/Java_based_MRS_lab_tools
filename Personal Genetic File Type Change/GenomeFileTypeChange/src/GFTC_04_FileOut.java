import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class GFTC_04_FileOut {

	static void processFile ( ) throws IOException{
		System.out.println(GFTC_00_MAINWindow.inFilePath);
		UserEntry[] user = GFTC_03_ImportUserFile.userImport( GFTC_00_MAINWindow.inputIs_23AndMe, GFTC_00_MAINWindow.inputIs_ancestryDNA, 
				GFTC_00_MAINWindow.inputIs_ftDNA , false, false, false, false, false, GFTC_00_MAINWindow.inputIs_diagnomics, GFTC_00_MAINWindow.inFilePath);
		
		System.out.println("# of user entries = " + user.length);
		
		File outFile = new File(GFTC_00_MAINWindow.outFilePath);
		FileWriter fw = new FileWriter(outFile);
		BufferedWriter bw = new BufferedWriter (fw);
		/*
		 * The file format for an AncestryDNA raw data file differs from the 23andMe file format		
			The results are given in two columns called Allele1 and Allele2, while 23andMe combines these 
			into one column called genotype, with the alleles in alphabetical order.	
				
				Note: Allele1 and Allele2 do NOT represent phased data -- they are in the order given by 
				Illumina's base-calling software.
			AncestryDNA uses 0 for no-calls, while 23andMe uses a dash (-).	
			Ancestry DNA uses the number 23 for X chromosome SNPs, 24 for Y chromosome SNPs, and 
			25 for XY SNPs. 23andMe includes XY SNPs with the X portion of the file.	
	
		 */
		
		
		if (GFTC_00_MAINWindow.outputIs_diagnomics){	}
		
		if (GFTC_00_MAINWindow.outputIs_23AndMe){
			System.out.println("outputIs_23AndMe");
			GFTC_04_FileOut.twenAndMeOut(user, bw);
			
		}
		
		if (GFTC_00_MAINWindow.outputIs_ancestryDNA){
			System.out.println("outputIs_ancestryDNA");
			GFTC_04_FileOut.ancestryOut(user, bw);
		
			
		}
	
		if (GFTC_00_MAINWindow.outputIs_ftDNA){
				
			System.out.println("outputIs_ftDNA");
				GFTC_04_FileOut.ftDNAOut(user, bw);
				
		}
		
		
		
		
		bw.close();
	}

	static void twenAndMeOut(UserEntry[] user, BufferedWriter bw) throws IOException{
		// 23andMe
		// rsid	chromosome	position	genotype
			// tab separated
		
		bw.write("# 23andMe reformatted File\n");
		if (GFTC_00_MAINWindow.inputIs_ancestryDNA){
			System.out.println("inputIs_ancestryDNA");
			bw.write("# original file type = ancestryDNA\n# Header stuff");
			bw.write("#rsid\tchromosome\tposition\tgenotype\n");
			GFTC_04_FileOut.ancestry_2_23AndMe(user, bw);
		}
		if (GFTC_00_MAINWindow.inputIs_ftDNA){
			System.out.println("inputIs_ftDNA");
			bw.write("# original file type = ftDNA\n# Header stuff");
			bw.write("#rsid\tchromosome\tposition\tgenotype\n");
			GFTC_04_FileOut.ancestry_2_23AndMe(user, bw);
		}
		
	}

	static void ancestryOut  (UserEntry[] user, BufferedWriter bw) throws IOException{
		bw.write("# Ancestry DNA reformatted File\n");
		// ancestyDNA
		//	rsid	chromosome	position	allele1	allele2
			// tab separated
		if (GFTC_00_MAINWindow.inputIs_23AndMe){
			System.out.println("inputIs_23AndMe");
			bw.write("# original file type = 23AndMe\n# Header stuff");
			bw.write("rsid\tchromosome\tposition\tallele1\tallele2\n");
			GFTC_04_FileOut.twenAndMe_2_ancestry(user, bw);
		}
		if (GFTC_00_MAINWindow.inputIs_ftDNA){
			System.out.println("inputIs_ftDNA");
			bw.write("# original file type = ftDNA\n# Header stuff");
			bw.write("rsid\tchromosome\tposition\tallele1\tallele2\n");
			GFTC_04_FileOut.twenAndMe_2_ancestry(user, bw);
		}
	}

	static void ftDNAOut  (UserEntry[] user, BufferedWriter bw) throws IOException{
		// familyfinder
		// rsid	chromosome	position	result (genotype)
			// csv
		if (GFTC_00_MAINWindow.inputIs_23AndMe){
			System.out.println("inputIs_23AndMe");
			bw.write("# original file type = 23AndMe\n# Header stuff");
			bw.write("RSID,CHROMOSOME,POSITION,RESULT\n");
			GFTC_04_FileOut.ancestry_2_ftDNA(user, bw);
		}
		if (GFTC_00_MAINWindow.inputIs_ancestryDNA){
			System.out.println("inputIs_ancestryDNA");
			bw.write("# original file type = ancestryDNA\n# Header stuff");
			bw.write("RSID,CHROMOSOME,POSITION,RESULT\n");
			GFTC_04_FileOut.ancestry_2_ftDNA(user, bw);
		}
		
	}

	static void twenAndMe_2_ancestry (UserEntry[] user, BufferedWriter bw) throws IOException{
		for (int i = 0; i < user.length; i++){
			String rsTemp = user[i].rsNum.trim();
			String chromTemp = user[i].chrom_String.trim();
			int position = user[i].position;
			user[i].genotype = user[i].genotype.trim();
			String a1 = "";
			String a2 = "";
			if (user[i].genotype.length() > 1){
				char[] alleles = user[i].genotype.trim().toCharArray();
				if ( alleles.length > 1){
					a1 = alleles[0] + "";
					a2 = alleles[1] + "";
				}
			}	else	{
				System.out.println(user[i].genotype);
				a1 = user[i].genotype;
				a2 = "-";
			}
			

			
			if ( a1.equals("-")){	a1 = 0 + "";	}
			if ( a2.equals("-")){	a2 = 0 + "";	}
			if (chromTemp.equals("Y")){	chromTemp = 24 + "";	}
			if (chromTemp.equals("Y")){	chromTemp = 25 + "";	}
			if (chromTemp.equals("X")){	chromTemp = 23 + "";	}
			if (chromTemp.equals("MT")){	chromTemp = 26 + "";	}
			
			
			/*
			 * AncestryDNA uses 0 for no-calls, while 23andMe uses a dash (-).	
				Ancestry DNA uses the number 23 for X chromosome SNPs, 24 for Y chromosome SNPs, and 
				25 for XY SNPs. 23andMe includes XY SNPs with the X portion of the file.	
			 */
			if (!rsTemp.startsWith("i")){
				bw.write(rsTemp + "\t" + chromTemp+ "\t" + position+ "\t" + a1+ "\t" + a2 + "\n");
				//System.out.println("out line = " + i + "\t\t" + rsTemp + "\t" + chromTemp+ "\t" + position+ "\t" + a1+ "\t" + a2 );
			}
		}
	}

	static void ancestry_2_23AndMe (UserEntry[] user, BufferedWriter bw) throws IOException{
		for (int i = 0; i < user.length; i++){
			String rsTemp = user[i].rsNum.trim();
			String chromTemp = user[i].chrom_String.trim();
			int position = user[i].position;
			user[i].genotype = user[i].genotype.trim();
			String a1 = "";
			String a2 = "";
			
			if (user[i].genotype.length() > 1){
				char[]  alleles = user[i].genotype.trim().toCharArray();
				if ( alleles.length > 1){
					a1 = alleles[0] + "";
					a2 = alleles[1] + "";
				}
			}	else	{
				System.out.println(user[i].genotype);
				a1 = user[i].genotype;
				a2 = "-";
			}
			
	
			
			if ( a1.equals("0")){	a1 = "-";	}
			if ( a2.equals("0")){	a2 = "-";	}
			
			if (chromTemp.equals("24")){	chromTemp = "Y";	}
			if (chromTemp.equals("25")){	chromTemp = "Y";	}
			if (chromTemp.equals("23")){	chromTemp = "X";	}
			if (chromTemp.equals("26")){	chromTemp = "MT";	}
			
			/*
			 * AncestryDNA uses 0 for no-calls, while 23andMe uses a dash (-).	
				Ancestry DNA uses the number 23 for X chromosome SNPs, 24 for Y chromosome SNPs, and 
				25 for XY SNPs. 23andMe includes XY SNPs with the X portion of the file.	
			 */
			bw.write(rsTemp + "\t" + chromTemp+ "\t" + position+ "\t" + a1 + a2 + "\n");
			System.out.println("out line = " + i + "\t\t" + rsTemp + "\t" + chromTemp+ "\t" + position+ "\t" + a1 + a2  );
		}
	}

	static void ancestry_2_ftDNA (UserEntry[] user, BufferedWriter bw) throws IOException{
		for (int i = 0; i < user.length; i++){
			String rsTemp = user[i].rsNum.trim();
			String chromTemp = user[i].chrom_String.trim();
			int position = user[i].position;
			user[i].genotype = user[i].genotype.trim();
			String a1 = "";
			String a2 = "";
			char[] alleles = new char[2];
			
			if (user[i].genotype.length() > 1){
				 alleles = user[i].genotype.trim().toCharArray();
				if ( alleles.length > 1){
					a1 = alleles[0] + "";
					a2 = alleles[1] + "";
				}
			}	else	{
				System.out.println(user[i].genotype);
				a1 = user[i].genotype;
				a2 = "-";
			}
			
			
			if ( a1.equals("0")){	a1 = "-";	}
			if ( a2.equals("0")){	a2 = "-";	}
			if (chromTemp.equals("24")){	chromTemp = "Y";	}
			if (chromTemp.equals("25")){	chromTemp = "Y";	}
			if (chromTemp.equals("23")){	chromTemp = "X";	}
			if (chromTemp.equals("26")){	chromTemp = "MT";	}
			
			/*
			 * AncestryDNA uses 0 for no-calls, while 23andMe uses a dash (-).	
				Ancestry DNA uses the number 23 for X chromosome SNPs, 24 for Y chromosome SNPs, and 
				25 for XY SNPs. 23andMe includes XY SNPs with the X portion of the file.	
			 */
			bw.write(rsTemp + "," + chromTemp+ "," + position+ "," + a1 + a2 + "\n");
			System.out.println("out line = " + i + "\t\t" + rsTemp + "," + chromTemp+ "," + position+ "," + a1 + a2);
		}
	}

}
