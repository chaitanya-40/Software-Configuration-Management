package Repository;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
* 
* @description: Creates backup for Repository files in manifest file
*/

public class checkOut {
	/**
	 * Backup the repository files declared in manifest file
	 * inputs manifest file path including file name
	 * @return Nothing
	 */
	public void start()
	{
	String sourcePath="",destination_Path="";
    Scanner scan = new Scanner(System.in);
   	
    System.out.println("Enter the source path");
    sourcePath = scan.nextLine();
        System.out.println("Enter the target folder path including folder name:");
        destination_Path = scan.nextLine();
        
       // String lable=manifest_read.getLabel();
        writeManifest w1=new writeManifest();
        try {
        	File sourceFolder = new File(sourcePath);
			String finalpath=destination_Path+"\\"+sourceFolder.getName().toString();
		    File destinationFolder = new File(finalpath);
			w1.start(sourcePath,destination_Path,2,"checkOutManifest");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //scan.close();
	}

}
