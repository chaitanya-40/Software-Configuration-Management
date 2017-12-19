package Repository;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
* 
* @description: Creates Repository for the source folder selected
*/
/**
 * @author voora
 *
 */
public class checkin {
	/**
	 * Create repository by taking source path and destination path from user
	 * 
	 * 
	 * @return Nothing
	 */
	public void start()
	{
		String source_path="",destination_path="";
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Source Path");
        source_path = scan.nextLine();
        System.out.println("Enter the target path");
        destination_path = scan.nextLine();
     		            writeManifest w=new writeManifest();
        try {
			
			File sourceFolder = new File(source_path);
			String finalpath=destination_path+"\\"+sourceFolder.getName().toString();
		    File destinationFolder = new File(finalpath);
		    w.start(source_path,destination_path,1,"manifest");
		    String path=sourceFolder.getParent().toString()+"\\"+"activity";
		    File f = new File(path);
		    File destination_acivity_File=getLatestFilefromDir(destination_path+"\\"+"activity");
		    if(f.exists())
		    {
		    	File source_activity_file=getLatestFilefromDir(path);
		    	String sourse_activity_latest_manifest=source_activity_file.getName();
		    	
		    	writeXml w1=new writeXml(sourse_activity_latest_manifest,destination_acivity_File.getName(),destination_path+"\\"+"activity"+"\\"+"tree.xml");
		    	w1.start();
		    }
		    else {
		    	
		    	writeXml w1=new writeXml(destination_acivity_File.getName(),destination_path+"\\"+"activity"+"\\"+"tree.xml");
		    	w1.start();
		    	
		    }
						} catch (IOException e) {
			e.printStackTrace();
		}      
	}
	/**
	 * Returns latest file
	 * 
	 * 
	 * @return latest file in Directory
	 */
	private File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
}
