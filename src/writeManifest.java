package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com

* 
* @description: write initial header content to manifest file
*/
public class writeManifest {
	public static String lable;
	public static int number_of_manifestFiles;
	public static String manifestName;
	public writeManifest()
	{
		
	}
	public static void start(String source_path,String destination_path,int checkin_checkout,String manifestName1) throws IOException
    {
		manifestName=manifestName1;
		File sourceFolder = new File(source_path);
		String finalpath=destination_path+"\\"+sourceFolder.getName().toString();
        File destinationFolder = new File(finalpath);
        if(checkin_checkout==1)
        {
        	   number_of_manifestFiles=countManifest(destinationFolder.getParent()+"\\"+"activity");
        }
        else
        {
        	number_of_manifestFiles=countManifest(sourceFolder.getParent()+"\\"+"activity");
        }
     
        number_of_manifestFiles=number_of_manifestFiles+1;
        File f2 = new File(destinationFolder.getParent()+"\\"+"activity"+"\\"+manifestName+"-"+number_of_manifestFiles+".txt");
       // File f3 = new File(destinationFolder.getParent()+"\\"+"activity"+"\\"+"lable"+".txt");
        
        
       
                    if (!destinationFolder.exists()) 
                        {
                            destinationFolder.mkdirs();
                                                     
                            String activity;
                            activity=destinationFolder.getParent()+"\\"+"activity";
                            File activityFolder=new File(activity);
                                if(!activityFolder.exists())
                                {
                                    activityFolder.mkdir();
                                }
                                writemanifesto(sourceFolder,destinationFolder,activityFolder,source_path,destination_path,checkin_checkout);
                        }
                   
                    if(!f2.exists())
                    {
                    	System.out.println("in file creation");
                    	String activity1;
                    	 activity1=destinationFolder.getParent()+"\\"+"activity";
                         File activityFolder=new File(activity1);
                    	 writemanifesto(sourceFolder,destinationFolder,activityFolder,source_path,destination_path,checkin_checkout);
                    }
                    if(checkin_checkout==1)
                    {
                    	 copyfolder c=new copyfolder(1,number_of_manifestFiles,manifestName1);
             		    c.copy(sourceFolder, destinationFolder, destination_path);
             		    
                    }
                    if(checkin_checkout==2||checkin_checkout==4)
                    {
                    	 Scanner scan = new Scanner(System.in);
                    	System.out.println("Enter manifest file path to refer including file name or Enter Lable");
                        String manifestPath_lable=scan.nextLine();
                        File manifestFile=new File(manifestPath_lable);
                        String manifestPath;
                        if(!manifestFile.exists())
                        {
                        	manifestPath=getPath(sourceFolder.getParent()+"\\"+"activity",manifestPath_lable);
                        }
                        else {
                        	manifestPath=manifestPath_lable;
                        }
                        
                        readManifest manifest_read=new readManifest();
                               try {
                            	   manifest_read.read(manifestPath);
                    		} catch (IOException e) {
                    			e.printStackTrace();
                    		}
                    	copyfolder c=new copyfolder(checkin_checkout,number_of_manifestFiles,manifest_read.getFilenames(),manifestName1);
            		    c.copy(sourceFolder, destinationFolder, destination_path);
            		    writecheckOutManifesto(destinationFolder,sourceFolder);
            		    File f=new File(manifestPath);
            		    
            		    writeXml w=new writeXml(f.getName(),manifestName+"-"+number_of_manifestFiles+".txt",sourceFolder.getParent()+"\\"+"activity"+"\\"+"tree.xml");
            		    w.start();
                    }
                   
                
                     
    }
	private static void writecheckOutManifesto(File destinationFolder, File sourceFolder) {
		String path1=destinationFolder.getParent()+"\\"+"activity"+"\\"+manifestName+"-"+number_of_manifestFiles+".txt";
		String path2=sourceFolder.getParent()+"\\"+"activity"+"\\"+manifestName+"-"+number_of_manifestFiles+".txt";
		InputStream inStream = null;
		OutputStream outStream = null;
		try{

    	    File afile =new File(path1);
    	    File bfile =new File(path2);

    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);

    	    byte[] buffer = new byte[1024];

    	    int length;
    	    //copy the file content in bytes
    	    while ((length = inStream.read(buffer)) > 0){

    	    	outStream.write(buffer, 0, length);

    	    }

    	    inStream.close();
    	    outStream.close();

    	}catch(IOException e){
    	    e.printStackTrace();
    	}
		
	}
	private static String getPath(String path, String lable) {

		 try {
			FileReader fileReader = new FileReader(path+"\\"+"lable.txt");
			BufferedReader br = new BufferedReader(fileReader);
			
			
			String line = "";
			
				while( (line = br.readLine()) != null){
				 if(line.contains(lable))
				 {
					String fileName=line.split(":")[0]; 
					String manifestfilepath=path+"\\"+fileName;
					return manifestfilepath;
				 }
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
	/**
	 * Creates manifest file with the header info with the commands given by the user
	 *
	 */

		 public static void writemanifesto(File oldFolder,File folder,File activity,String path,String despath,int checkin_checkout)
         {
			 DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
             Date dateobj = new Date();
			 String date=df.format(dateobj);
             if(checkin_checkout==1)
             {
                 try
                 {
                	
                     FileWriter fw=new FileWriter(activity.getPath()+"\\"+manifestName+"-"+number_of_manifestFiles+".txt");    
                     //fw.write("Label: "+lable+System.lineSeparator()+System.lineSeparator());
                     fw.write("Folder Name and its Creation Time"+System.lineSeparator());
                     fw.write(folder.getParent()+ " Date and Time: "+ date+System.lineSeparator() );
                     fw.write("Commands entered by User"+System.lineSeparator());
                     fw.write(path+System.lineSeparator()+despath+System.lineSeparator()+System.lineSeparator());
                     fw.write("FULL SOURCE PATH TO ORIGINAL PROJECT TREE:"+" "+oldFolder.getPath()+System.lineSeparator());
                     fw.write("TARGET REPO PATH:"+folder.getParent()+System.lineSeparator()+System.lineSeparator());
                     fw.write("--------------------------------"+System.lineSeparator());
                     fw.write("-----------Files Copied with its details-----------------"+System.lineSeparator());
                     fw.close();
                 }
                 catch(IOException e)
                 {
                     e.printStackTrace();
                 }
             }
             else if(checkin_checkout==2)
             {
                 try
                 {
                    
                     FileWriter fw=new FileWriter(activity.getPath()+"\\"+manifestName+"-"+number_of_manifestFiles+".txt");    
                    // fw.write("Label: "+lable+System.lineSeparator()+System.lineSeparator());
                     fw.write("Folder Name and Date Created"+System.lineSeparator());
                     fw.write(folder.getParent()+"  Date and Time Created: "+date+System.lineSeparator()+System.lineSeparator());
                     fw.write("COMMANDS GIVEN BY THE USER:"+System.lineSeparator()+path+System.lineSeparator()+despath+System.lineSeparator()+System.lineSeparator());
                     fw.write("Source Path to Original Tree"+System.lineSeparator()+oldFolder.getPath()+System.lineSeparator()+System.lineSeparator());
                     fw.write("TARGET REPO PATH:"+"\n"+folder.getParent()+System.lineSeparator()+System.lineSeparator());
                     fw.write("-----------------------------------------"+System.lineSeparator());
                     fw.write("---------------Files Copied details---------------"+System.lineSeparator());
                     fw.close();
                 }
                 catch(IOException e)
                 {
                     e.printStackTrace();
                 }
             }
         }
	private static int countManifest(String path) {
		
		File folder = new File(path);
		int length=0;
		if(folder.exists())
		{
		File[] listOfFiles = folder.listFiles();
		
           
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		      
		        if(listOfFiles[i].getName().contains(manifestName))
		        {
		        	length++;
		        }
		      } else if (listOfFiles[i].isDirectory()) {
		        
		      }
		    }
		}
		return length;
	}
	
}
