

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


class GFTC_03_ImportUserFile {

	private static int positionIndex = 0;
	private static int chromIndex = 0;
	private static boolean rsIndexFound = false;
	private static int rsIndex = 0;
	private static int genoInfIndex = 0;
	private static int altIndex = 0;
	private static int qualIndex = 0;
	private static int refIndex = 0;
	//private static int filterIndex = 0;
	
	static UserEntry[] userImport( boolean isInputType23andMe, boolean isInputTypeAncestryDNA, 
											boolean isInputTypeFamilyTreeDNA , boolean isInputTypeVCF, 
											boolean isInputTypeDECODE, boolean isInputTypePathway, 
											boolean isInputTypeInterleukin, boolean isInputTypeLumigenix,
											boolean isInputTypeDiagnomics,
											String userFileName ) throws IOException{
		
		
		GFTC_02_General2.systemOut("GFTC_02_General2", "userImport", 
										Thread.currentThread().getStackTrace()[1].getLineNumber(), 
										"Importing User File");
		
		if ( isInputType23andMe){
			GFTC_02_General2.systemOut("GFTC_02_General2", "userImport", 
					Thread.currentThread().getStackTrace()[1].getLineNumber(), 
					"isInputType23andMe = " + isInputType23andMe);
		}
		if ( isInputTypeAncestryDNA){
			GFTC_02_General2.systemOut("GFTC_02_General2", "userImport", 
					Thread.currentThread().getStackTrace()[1].getLineNumber(), 
					"isInputTypeAncestryDNA = " + isInputTypeAncestryDNA);
		}
		if ( isInputTypeFamilyTreeDNA){
			GFTC_02_General2.systemOut("GFTC_02_General2", "userImport", 
					Thread.currentThread().getStackTrace()[1].getLineNumber(), 
					"isInputTypeFamilyTreeDNA = " + isInputTypeFamilyTreeDNA);
		}
		
		
		UserEntry[] user = new UserEntry[1];
		 ArrayList<String> userList = new ArrayList<String>();
		 if (isInputType23andMe){
				userList = import23AndMe(userFileName);
				user = userSplit_23andMe(userList);
			}
			if (isInputTypeAncestryDNA){
				userList = importAncestryDNA(userFileName);
				user = userSplit_AncestryDNA(userList);
			}
			if (isInputTypeFamilyTreeDNA){
				userList = importftDNA(userFileName);
				user = userSplit_FamilyFinder(userList);
			}
			if (isInputTypeVCF){
				userList = importVCF(userFileName);
				user = userSplit_VCF(userList);
			}
			if (isInputTypeDiagnomics){
				userList = importDiagnomics(userFileName);
				user = userSplit_Diagnomics(userList);
			}
			if (isInputTypeDECODE){
				// user_List = NutriMethods.(userFileName);
				//TODO: find this format (eventually)
				// user_List = NutriMethods.(userFileName);
				userList = importLines(userFileName);
							//TODO: 
			}
			if (isInputTypePathway){
				// user_List = NutriMethods.(userFileName);
				//TODO: find this format (eventually)
				// user_List = NutriMethods.(userFileName);
							//TODO: 
			}
			if (isInputTypeInterleukin){
				// user_List = NutriMethods.(userFileName);
				//TODO: find this format (eventually)
				// user_List = NutriMethods.(userFileName);
							//TODO: 
			}
			if (isInputTypeLumigenix){
				// user_List = NutriMethods.(userFileName);
				//TODO: find this format (eventually)
				// user_List = NutriMethods.(userFileName);
							//TODO: 
			}

		return user;
	}
	
	
	/**
	 * Generic line import
	 * @param inputName
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<String> importLines ( String inputName) throws IOException{
		ArrayList<String> lines = new  ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputName));
		String brLine = br.readLine();
		//int brLineNumber = 0;
		while (brLine != null ) {
			if (brLine.length() > 0) { // skip blank lines		
			//	int line_number_matcher = brLineNumber + 1 ;// used for boolean if statements to reduce parens confusion
				lines.add(brLine);	
				//brLineNumber++;
			}
			brLine = br.readLine();
		}
		br.close();
		return lines;
	}
	
	/**
	 * import 23AndMe file
	 * @param inputName
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<String> import23AndMe ( String inputName ) throws IOException{
		ArrayList<String> lines = new  ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputName));
		String brLine = br.readLine();
		//int brLineNumber = 0;
		while (brLine != null ) {
			if (brLine.length() > 0) { // skip blank lines		
				if ( !brLine.startsWith("#")){
					//	int line_number_matcher = brLineNumber + 1 ;// used for boolean if statements to reduce parens confusion
						lines.add(brLine);	
						//brLineNumber++;
				}
			}	
			brLine = br.readLine();
		}
		br.close();
		return lines;
	}
	
	
	
	
	/**
	 * import AncestryDNA
	 * @param inputName
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<String> importAncestryDNA ( String inputName ) throws IOException{
		ArrayList<String> lines = new  ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputName));
		String brLine = br.readLine();
		//int brLineNumber = 0;
		while (brLine != null ) {
			if (brLine.length() > 0) { // skip blank lines		
				if ( !brLine.startsWith("#")){
					if ( !brLine.startsWith("rsid")){
					//	int line_number_matcher = brLineNumber + 1 ;// used for boolean if statements to reduce parens confusion
						lines.add(brLine);	
						//brLineNumber++;
					}
				}
			}	
			brLine = br.readLine();
		}
		br.close();
		return lines;
	}
	
	
	/**
	 * import ftDNA
	 * @param inputName
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<String> importftDNA ( String inputName ) throws IOException{
		ArrayList<String> lines = new  ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputName));
		String brLine = br.readLine();
		//int brLineNumber = 0;
		while (brLine != null ) {
			if (brLine.length() > 0) { // skip blank lines		
				if ( !brLine.startsWith("#")){
					if ( !brLine.startsWith("rsid") && !brLine.startsWith("RSID")){
					//	int line_number_matcher = brLineNumber + 1 ;// used for boolean if statements to reduce parens confusion
						lines.add(brLine);	
						//brLineNumber++;
					}
				}
			}	
			brLine = br.readLine();
		}
		br.close();
		return lines;
	}
	
	
	/**
	 * import VCF
	 * @param inputName
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<String> importVCF ( String inputName ) throws IOException{
		ArrayList<String> lines = new  ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputName));
		String brLine = br.readLine();
		//int brLineNumber = 0;
		while (brLine != null ) {
			if (brLine.length() > 0) { // skip blank lines		
				//if ( !brLine.startsWith("#")){
					//	int line_number_matcher = brLineNumber + 1 ;// used for boolean if statements to reduce parens confusion
						lines.add(brLine);	
						//brLineNumber++;
				//}
			}	
			brLine = br.readLine();
		}
		br.close();
		return lines;
	}


	/**
	 * userSplit_23andMe
	 * @param user_List
	 * @return
	 */
	private static UserEntry[] userSplit_23andMe (ArrayList<String> user_List){
		int userNum = user_List.size();
		UserEntry[] userArray = new UserEntry[userNum];
		// 23andMe
		// rsid	chromosome	position	genotype
			// tab separated
		rsIndexFound = true;
		for ( int i = 0; i < userNum; ){
			String linetemp = user_List.get(i);
			String[] split = linetemp.split("\t");
			UserEntry userTEMP = new UserEntry();
			userTEMP.rsNum = split[0];
				if (!userTEMP.rsNum.startsWith("i")){
					userTEMP.rsNum_Num = Integer.parseInt(userTEMP.rsNum.replace("rs", ""));
				}
				userTEMP.chrom_String = split[1];
				userTEMP.position = Integer.parseInt(split[2]);
				userTEMP.genotype = split[3];
				
				//System.out.println(userTEMP.genotype);
				
				
				userTEMP.isRSFound = true;
				userArray[i] = userTEMP;	
			i++;
		}
	
		return userArray;
	}


	/**
	 * userSplit_AncestryDNA
	 * @param user_List
	 * @return
	 */
	private static UserEntry[] userSplit_AncestryDNA (ArrayList<String> user_List){
		int userNum = user_List.size();
		UserEntry[] userArray = new UserEntry[userNum];
		// ancestyDNA
		//	rsid	chromosome	position	allele1	allele2
			// tab separated
		rsIndexFound = true;
		for ( int i = 0; i < userNum; i++){
			String linetemp = user_List.get(i);
			String[] split = new String[4];
			if (linetemp.contains("\t")){split = linetemp.split("\t");	}
			if (linetemp.contains(",")){	split = linetemp.split(",");}
			UserEntry userTEMP = new UserEntry();
			userTEMP.rsNum = split[0];
			userTEMP.rsNum_Num = Integer.parseInt(userTEMP.rsNum.replace("rs", ""));
			userTEMP.chrom_String = split[1];
			userTEMP.position = Integer.parseInt(split[2]);
			String alleleTemp1 = split[3];
			String alleleTemp2 = split[4];
			userTEMP.genotype = alleleTemp1 + alleleTemp2;
			userTEMP.isRSFound = true;
			userArray[i] = userTEMP;	
			
		}
	
		return userArray;
	}


	/**
	 * userSplit_FamilyFinder
	 * @param user_List
	 * @return
	 */
	private static UserEntry[] userSplit_FamilyFinder (ArrayList<String> user_List){
		int userNum = user_List.size();
		UserEntry[] userArray = new UserEntry[userNum];
		// familyfinder
		// rsid	chromosome	position	result (genotype)
			// csv
		rsIndexFound = true;
		for ( int i = 0; i < userNum; ){
			String linetemp = user_List.get(i);
			String[] split = linetemp.split(",");
			UserEntry userTEMP = new UserEntry();
			userTEMP.rsNum = split[0];
		//	userTEMP.rsNum_Num = Integer.parseInt(userTEMP.rsNum.replace("rs", ""));
			userTEMP.chrom_String = split[1];
			userTEMP.position = Integer.parseInt(split[2]);
			userTEMP.genotype = split[3];
			userTEMP.isRSFound = true;
			userArray[i] = userTEMP;	
			i++;
		}
	
		return userArray;
	}


	/**
	 * userSplit_VCF
	 * @param user_List
	 * @return
	 */
	private static UserEntry[] userSplit_VCF (ArrayList<String> user_List){
		int userNum = user_List.size();
		
		ArrayList<UserEntry> splitList = new ArrayList<UserEntry>();
		
		// vcf
		//	chrom	pos		id (rsid)		ref	alt
		
		boolean rsIndexFound = GFTC_03_ImportUserFile.rsFinderTrue(user_List);
	
			for ( int i = 0; i < userNum; i++){
				
				String linetemp = user_List.get(i);
				if ( !linetemp.startsWith("#") ){
				String[] split = linetemp.split("\t");
				UserEntry userTEMP = new UserEntry();
				userTEMP.isRSFound = rsIndexFound;
				userTEMP.rsNum = split[ rsIndex ];			
				if ( !userTEMP.rsNum.contains(".")){
			//		userTEMP.rsNum_Num = Integer.parseInt(userTEMP.rsNum.replace("rs", ""));
				}
				if (userTEMP.rsNum.contains(".")){
			//		userTEMP.rsNum_Num = 0;
				}
				userTEMP.chrom_String = split[ chromIndex ];
				userTEMP.position = Integer.parseInt(split[positionIndex]);
				String genoInf = split[ genoInfIndex ];
				String alt = split[altIndex ];
				double qual = Double.parseDouble(split[ qualIndex ]);
				boolean qualPass = false;
			//	if ( qual >= Nutrigen_01_Properties.vcfFileQualFilter){
					qualPass = true;
			//	}
				
				String ref = split[refIndex];
				char refAllele = 'n';
				char altAllele = 'n';
				
				
				int alleleCount = GFTC_03_ImportUserFile.alleleCounter( genoInf);
			//	Methods_General2.systemOut("Methods_FileSplit", "vcf", Thread.currentThread().getStackTrace()[1].getLineNumber(), "alleleCount =" + alleleCount);
				if ( ref.contains("A") || ref.contains("T") || ref.contains("C") || ref.contains("G")){
					char[] splitChar = ref.toCharArray();
					refAllele = splitChar[0];
				}
				
				if ( alt.contains("A") || alt.contains("T") || alt.contains("C") || alt.contains("G")){
					char[] splitChar = alt.toCharArray();
					altAllele = splitChar[0];
				}
				
				
				if ( alleleCount == 1){
					userTEMP.genotype = refAllele + "" + altAllele;
				}
				
				if ( alleleCount == 2){
					userTEMP.genotype = altAllele + "" + altAllele;
				}
				if ( alleleCount == 0){
					userTEMP.genotype = "";
				}
				if ( alleleCount > 2){
					userTEMP.genotype = altAllele + "" + altAllele;
				}
	
		//		Methods_General2.systemOut("Methods_FileSplit", "vcf", Thread.currentThread().getStackTrace()[1].getLineNumber(), "genotype =" + userTEMP.genotype);
				if ( qualPass == true){
					splitList.add(userTEMP );
				}
	
				
				}
			}
	
		
		
			
			UserEntry[] userArray = new UserEntry[splitList.size()];
			
			for ( int i = 0 ; i < splitList.size(); i++){
				
				userArray[i] = splitList.get(i);
				
			}
		
	
		return userArray;
	}



	
	
	/**
	 * rsFinderTrue
	 * @param user_List
	 * @return
	 */
	private static boolean rsFinderTrue ( ArrayList<String> user_List ){
		for ( int i = 0; i < user_List.size(); i++){
			if( !user_List.get(i).startsWith("#")){
				String linetemp = user_List.get(i);
				String[] split = linetemp.split("\t");
				for ( int j = 0; j < split.length; j++){
					if ( split[j].startsWith("rs")){	
						rsIndex = j;
						rsIndexFound = true;
						break;
					}
				}
			}
			if ( rsIndexFound == true){
				break;
			}			
		}
		
		for ( int i = 0; i < user_List.size(); i++){
			if( user_List.get(i).startsWith("#CHROM")){
				
				String headerLineTemp = user_List.get(i);
				String[] split = headerLineTemp.split("\t");
				
				for ( int j = 0 ; j < split.length; j++){
					
					if ( split[j].contains("CHROM")){
						chromIndex = j;
					}
					if ( split[j].contains("POS")){
						positionIndex = j;
					}
					
					if ( split[j].contains("INFO")){
						genoInfIndex = j;
					}
					if ( split[j].contains("REF")){
						refIndex = j;
					}
					if ( split[j].contains("ALT")){
						altIndex = j;
					}
					
					if ( split[j].contains("FILTER")){
						//filterIndex = j;
					}	
					if ( split[j].contains("QUAL")){
						qualIndex = j;
					}
					
				}	
				break;
			}	
		}
		return rsIndexFound;
	}



	
	/**
	 * alleleCounter
	 * @param inf
	 * @return
	 */
	private static int alleleCounter ( String inf ){
		int count = 0;
		if ( inf.contains("AC=")){
			int acIndexStart = inf.indexOf("AC=");
			int countIndex = acIndexStart + 3;
			count = Integer.parseInt(inf.substring(countIndex, countIndex+1));	
		} 
		return count;
	}
	
	
	
	
	private static ArrayList<String> importDiagnomics( String inputName ) throws IOException{
		ArrayList<String> lines = new  ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputName));
		String brLine = br.readLine();
		//int brLineNumber = 0;
		while (brLine != null ) {
			if (brLine.length() > 0) { // skip blank lines		
				
				if ( !brLine.startsWith("#") && !brLine.startsWith("rsID")){
					//	int line_number_matcher = brLineNumber + 1 ;// used for boolean if statements to reduce parens confusion
						lines.add(brLine);	
						//brLineNumber++;
				}	
			brLine = br.readLine();
			}
		}
		br.close();
		return lines;
	}
	
	
	
	private static UserEntry[] userSplit_Diagnomics (ArrayList<String> user_List){
		ArrayList<UserEntry> splitList = new ArrayList<UserEntry>();
		UserEntry[] userArray = new UserEntry[user_List.size()];
		for ( int i = 0; i < user_List.size(); i++){
			
			String linetemp = user_List.get(i);
			String[] split = linetemp.split("\t");
			UserEntry userTEMP = new UserEntry();
			userTEMP.isRSFound = rsIndexFound;
			userTEMP.rsNum = split[ 0 ];			
			if ( !userTEMP.rsNum.contains(".")){
				userTEMP.rsNum_Num = Integer.parseInt(userTEMP.rsNum.replace("rs", ""));
			}
			if (userTEMP.rsNum.contains(".")){
				userTEMP.rsNum_Num = 0;
			}
			userTEMP.chrom_String = split[ 1 ];
			userTEMP.position = Integer.parseInt(split[2]);
			userTEMP.genotype = split[ 3 ];
			
			userArray[i] = userTEMP;
		}
		return userArray;
	}

	
	
	
	
	
	
}
