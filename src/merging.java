package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
* 
* @description: Merge files taking input and target directory
*/
public class merging {
		public static String manifestPath;
	public static String treePath;
	public static File destinationFolder;
	public void start() throws IOException
	{
	String sourcePath="",destination_Path="";
    Scanner scan = new Scanner(System.in);
   	
    System.out.println("Enter the source path");
    sourcePath = scan.nextLine();
        System.out.println("Enter the target folder to merge");
        destination_Path = scan.nextLine();
    
      
        	File sourceFolder = new File(sourcePath);
			String finalpath=destination_Path+"\\"+sourceFolder.getName().toString();
		    destinationFolder = new File(finalpath);
		   // System.out.println("Enter the manifest File to refer");
	         //manifestPath = scan.nextLine();
	   getManifestPath(sourceFolder);
	        treePath=manifestPath.substring(0, manifestPath.lastIndexOf("\\"))+"\\"+"tree.xml";
	        copyFile c=new copyFile(manifestPath,sourceFolder.getParent().toString()+"\\"+"activity"+"\\"+getParentManifest());
		    c.copy(sourceFolder, destinationFolder);
		  
	}
	 private void getManifestPath(File sourceFolder) {
		 Scanner scan = new Scanner(System.in);
		  	System.out.println("Enter manifest file path to refer including file name or Enter Lable");
            String manifestPath_lable=scan.nextLine();
            File manifestFile=new File(manifestPath_lable);
            
            if(!manifestFile.exists())
            {
            	manifestPath=getPath(sourceFolder.getParent()+"\\"+"activity",manifestPath_lable);
            }
            else {
            	manifestPath=manifestPath_lable;
            }
            File f2=new File(manifestPath);
            if(!f2.exists())
            {
            	System.out.println("Entered path or lable is incorrect");
            	getManifestPath(sourceFolder);
            }
		
	}
	private String getParentManifest() {
	    	File f=new File(manifestPath);
		String manifest1=f.getName();
		File f3=getLatestFilefromDir(destinationFolder.getParent().toString()+"\\"+"activity");
		
		String manifest2=f3.getName();

		findParent findP=new findParent();
		return findP.getParent(manifest1, manifest2, treePath);
	}
		private static String getPath(String path, String lable) {

			 try {
				FileReader fileReader = new FileReader(path+"\\"+"lable.txt");
				BufferedReader br = new BufferedReader(fileReader);
				
				
				String line = "";
				
					while( (line = br.readLine()) != null){
					
						String fileName=line.split(":")[0]; 
						String lables=line.split(":")[1];
						String listOflables[]=lables.split(",");
						for(int i=0;i<listOflables.length;i++)
						{
							//System.out.println(listOflables[i]+"lable"+ lable);
							if(lable.equals(listOflables[i].trim()));
							{
								String manifestfilepath1=path+"\\"+fileName;
								//System.out.println(manifestfilepath1);
								return manifestfilepath1;
							}
							
						}
						
						String manifestfilepath=path+"\\"+fileName;
						return manifestfilepath;
					 
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (Exception e) {
				 e.printStackTrace();
			}
			return null;
		}
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
