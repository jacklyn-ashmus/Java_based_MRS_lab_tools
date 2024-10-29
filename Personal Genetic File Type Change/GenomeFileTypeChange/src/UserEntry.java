

class UserEntry {

	// 23andMe
	// rsid	chromosome	position	genotype
		// tab separated
	
	// ancestyDNA
	//	rsid	chromosome	position	allele1	allele2
		// tab separated
	
	// familyfinder
	// rsid	chromosome	position	result (genotype)
		// csv
		
	// vcf
	//	chrom	pos		id (rsid)		ref	alt
	
	
	String rsNum = "";
	int rsNum_Num = 0;
	String chrom_String = "";
	int chrom = 0;
	int position = 0;
	String genotype = "";
	
	boolean isRSFound = false;
	
	
	
	
	

	UserEntry () {
		String rsNum_c = "";
		String chrom_String_c = "";
		int chrom_c = 0;
		int position_c = 0;
		String genotype_c = "";
		
		this.rsNum = rsNum_c;
		this.chrom_String = chrom_String_c;
		this.chrom = chrom_c;
		this.position = position_c;
		this.genotype = genotype_c;
		
		
		
	}
}
