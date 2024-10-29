
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class GFTC_02_General2 {



	
	static String dateRetrieverSafeChar ( ){
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM_HH_mm");
		Date dateobj = new Date();
		
		String date = df.format(dateobj);
		
		return date;
	}
	
	
	
	static String dateRetriever ( ){
		DateFormat df = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
		Date dateobj = new Date();
		String date = df.format(dateobj);
		return date;
	}
	
	static void systemOut ( String className, String methodName, int lineIndex, String message){
		String date = dateRetriever();
		
		System.out.println(className + "." + methodName + " = " + lineIndex + "\t\t" + date + "\t\t" + message);
		
	}
	
	static double round(double value, int places) {
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	

	
	
	/**
	 * returns the containing path for the program / jar. used to locate config file in setting "-propE"
	 * @return
	 */
	static String getPath(){
		 File dirTEMP = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		 String directory = dirTEMP.getAbsolutePath();
		 String packageName = GFTC_02_General2.class.getPackage().getName();
		 systemOut( "Methods_General2", "getPath", Thread.currentThread().getStackTrace()[1].getLineNumber(), "directory = " + directory);
		 if ( directory.contains("bin")){
			String rem = packageName + File.separator + "bin";
			systemOut( "Methods_General2", "getPath", Thread.currentThread().getStackTrace()[1].getLineNumber(), "rem = " + rem );
			directory = directory.replace(rem, "");
			systemOut( "Methods_General2", "getPath", Thread.currentThread().getStackTrace()[1].getLineNumber(), "new directory = " + directory);
		 }
		 
		 return directory;
	}
	
}
