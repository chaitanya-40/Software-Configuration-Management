package Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com

* 
* @description: read manifest file and stores label, filenames, etc..
*/

public class readManifest {
	public static ArrayList<String> filenames=new ArrayList<String>();
    public static String label;
    public static String sourcePath;
	  public static ArrayList<String> getFilenames() {
		return filenames;
	}
	public static void setFilenames(ArrayList<String> filenames) {
		readManifest.filenames = filenames;
	}
	public static String getLabel() {
		return label;
	}
	public static void setLabel(String label) {
		readManifest.label = label;
	}
	public static String getSourcePath() {
		return sourcePath;
	}
	public static void setSourcePath(String sourcePath) {
		readManifest.sourcePath = sourcePath;
	}
	public void read(String manifestPath) throws IOException
	{
		 String line = null;
    	 try {
                          FileReader fileReader = 
                 new FileReader(manifestPath);
             BufferedReader bufferedReader = 
                 new BufferedReader(fileReader);

             while((line = bufferedReader.readLine()) != null) {
            	 if(line.contains("Label:"))
            	 {
            		 String a[]=line.split("Label:");
            		 label=a[1];
            		            	 }
            	 if(line.contains("TARGET REPO PATH:"))
            	 {
            		 String b[]=line.split("TARGET REPO PATH:");
            				 sourcePath=b[1];
            	 }
                 if(line.contains("File_Name:"))
                 {
                	 String k[]=line.split("Original_File_Name:");
                	 String m[]=k[0].split("File_Name:");
                	 filenames.add(m[1].trim());
                 }
             }   
                   bufferedReader.close();         
         }
         catch(FileNotFoundException ex) {
             System.out.println(
                 "Unable to opening file");                
         }
	}

}
