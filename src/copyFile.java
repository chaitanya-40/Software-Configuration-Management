package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
* 
* @description: copy files to merge folder and creates file with extension MT, MR and MG for files differed
*/
public class copyFile {
	public static int count=0;
	public static String manifestPath;
	public static ArrayList<String> manifestFiles;
	public static String parentManifest;
	
	public copyFile(String manifestPath, String parentManifest) {
		
		this.manifestPath=manifestPath;
		this.parentManifest=parentManifest;
	}
	 public void copy(File sourceFolder, File destinationFolder) throws IOException
	    {
			 readManifest manifest_read=new readManifest();
         try {
      	   manifest_read.read(manifestPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
         manifestFiles=manifest_read.getFilenames();
		 if (sourceFolder.isDirectory()) 
        {
            count=0;
            if (!destinationFolder.exists()) 
            {
                destinationFolder.mkdir();
            }
            String files[] = sourceFolder.list();
            for (String file : files) 
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                copy(srcFile, destFile);
            }
        }
    else
    {
        //int i=0;
      
        //To know whether there are multiple files
        File temp=new File(sourceFolder.getParent());
        File[] listOfFiles = temp.listFiles();
        int n_file=listOfFiles.length;
            if(n_file>1 && count==0)
            {
            	String selectedFile="";
                   for(int j=0;j<n_file;j++)
                   {
                	   String a[]=listOfFiles[j].toString().split("\\\\");
                       String file_name_end=a[a.length-1];
                       if(manifestFiles.contains(file_name_end))
                       {
                       	selectedFile=listOfFiles[j].toString();
                       }
                   }
                  
                    File filetocopy=new File(selectedFile);
                    checkoutwriter(filetocopy,destinationFolder);
                    count++;
            }
            else
            {
                if(count==0)
                    checkoutwriter(sourceFolder,destinationFolder);

            }
        }
 
}
	   		public static void checkoutwriter(File sourceFolder,File destinationFolder)
	    {
	        try
	        {
	            String line="",copiedstring="";
	            File f3=new File(sourceFolder.getAbsolutePath());
	            String checksum=f3.getName().substring(0, f3.getName().lastIndexOf("."));
	            FileReader f=new FileReader(sourceFolder);
	                        BufferedReader reader= new BufferedReader(f);
	                        while((line=reader.readLine())!=null)
	                        {
	                            copiedstring=copiedstring+line;   
	                        }
	                        reader.close();
	                        String prevfol=destinationFolder.getParentFile().getParent(); 
	                        String path=destinationFolder.getParent();                     
	                        String newfolder=destinationFolder.getName();      
	                        File test=new File(path);
	                        String parentname=test.getName();
	                        String finalfol=parentname+"."+newfolder.substring(newfolder.lastIndexOf(".")+1);
	                        String newpath=prevfol+"\\"+finalfol;  
	                        checkout_writer(sourceFolder,copiedstring,newpath,finalfol,checksum);
	                            copiedstring="";
	                            newpath="";
	                           	   test.delete();
	                        		   	                  
	        }
	        catch(IOException e)
	        {
	                e.printStackTrace();
	        }
	    }
	    public static String calculateChecksum(String sourceFolder) throws IOException
		   {
			   String line="";
			   int checksum1=0;
			   int artifact[]={1,3,7,11,17};
			   File f21=new File(sourceFolder);
			   if(f21.exists())
			   {
			   FileReader f=new FileReader(sourceFolder); 
	           BufferedReader reader= new BufferedReader(f);
	           //long filelength=sourceFolder.length(); 
	           while((line=reader.readLine())!=null)
	           {
	         
	               char[] charArray = line.toCharArray();
	                    for(int i=0;i<charArray.length;i++)
	                    {  
	                    	checksum1=(checksum1+((byte)charArray[i]*artifact[i%4]));
	             
		                }
	           }
	           reader.close();
			   }
	           return Integer.toString(checksum1);
		   }
	
	public static void checkout_writer(File srcfile,String copiedstring,String newpath,String finalfol,String checksum)
     {
		String line="";
        	try
         {
        		File f=new File(newpath);
        		String checksum1=calculateChecksum(f.getAbsolutePath().toString())+"-"+f.length();
        		
        		if(!checksum.trim().equals(checksum1.trim())&&f.exists())
        		{
        			
        			String filePath=newpath.substring(0, newpath.lastIndexOf("\\"));
        			String fileName=f.getName().substring(0, f.getName().lastIndexOf("."));
        			String extension=f.getName().substring(f.getName().lastIndexOf("."), f.getName().length());
        			
        			String fileNameTarget=filePath+"\\"+fileName+"_MT"+extension;
        			File f2=new File(fileNameTarget);
        			f2.createNewFile();
        			String copiedstring1="";
        			        			FileReader fr=new FileReader(f.getAbsolutePath());
                    BufferedReader reader= new BufferedReader(fr);
                    while((line=reader.readLine())!=null)
                    {
                        copiedstring1=copiedstring1+line;   
                    }
                    FileWriter fw1=new FileWriter(f2.getAbsolutePath());
                    fw1.write(copiedstring1);
                    fw1.close();
                   reader.close();
                    
                    String fileNameRepo=filePath+"\\"+fileName+"_MR"+extension;
                    FileWriter fw=new FileWriter(fileNameRepo);
                    		fw.write(copiedstring);
                    fw.close();
                    
                    
                    String repoFile=getFileName(f.getName());
                    String filepath1=srcfile.getParent()+"\\"+repoFile.trim();
                                        FileReader fr1=new FileReader(filepath1);
                    BufferedReader reader1= new BufferedReader(fr1);
                    String copiedString2="";
                    while((line=reader1.readLine())!=null)
                    {
                        copiedString2=copiedString2+line;   
                    }
                    fr1.close();
                    String fileNameRepo1=filePath+"\\"+fileName+"_MG"+extension;
                    FileWriter fw2=new FileWriter(fileNameRepo1);
                    		fw2.write(copiedString2);
                    		fw2.close();
                    fw.close();
                    File f5=new File(newpath);
                    f5.deleteOnExit();
                    f5.delete();
        		}
        		else
        		{
        			 FileWriter fw=new FileWriter(newpath);    
                     fw.write(copiedstring);	                   
                     fw.close();
        		}
        	            }
         catch(IOException e)
         {
             e.printStackTrace();
         }
     }
	private static String getFileName(String name) throws IOException {
		String line1="";
		
		FileReader fr=new FileReader(parentManifest);
        BufferedReader reader= new BufferedReader(fr);
        while((line1=reader.readLine())!=null)
        {
           if(line1.contains(name))
           {
        	   String k[]=line1.split("Original_File_Name:");
          	 String m[]=k[0].split("File_Name:");
          	           	 reader.close();
          	 return m[1];
           }
        }
        reader.close();
		return null;
	}

}
