package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
* 
* @description: Copies source folder to destination folder
*/

/**
 * @author vooradi
 *
 */
public class copyfolder {
	public static int count=0;
	public static int checkin_checkout;
	public static int number_of_manifestFiles;
	public static ArrayList<String> manifestFiles;
	public static String manifestName;
	
	
	public copyfolder(int value, int number_of_manifestFiles, ArrayList<String> listFiles,String manifestName1) {
		checkin_checkout=value;
		this.number_of_manifestFiles=number_of_manifestFiles;
		manifestFiles=listFiles;
		manifestName=manifestName1;
	}
	public copyfolder(int value, int number_of_manifestFiles,String manifestName1)
	{
		checkin_checkout=value;
		this.number_of_manifestFiles=number_of_manifestFiles;
		manifestName=manifestName1;
	}
	/**
	*
	* 
	
	* 
	* copy source folder files to destination folder
	* for checkin creating files with artifact id
	* for checkout taking parent folder names as file name
	*/
	   public void copy(File sourceFolder, File destinationFolder, String updateFile) throws IOException
	    {
		 
	      	        if(checkin_checkout==1)
	        {
	                if (sourceFolder.isDirectory()) 
	                {
	                    count=0;
	                    //create destination folder if not exist
	                   if (!destinationFolder.exists()) 
	                    {
	                        destinationFolder.mkdir();
	                    }
	                    String files[] = sourceFolder.list();
	                    for (String file : files) 
	                    {
	                        File srcFile = new File(sourceFolder, file);
	                        File destFile = new File(destinationFolder, file);
	                        copy(srcFile, destFile, updateFile);
	                    }
	                }
	                else
	                {
	                    int artifact[]={1,3,7,11,17};
	                    String line="",copiedstring="";
	                    int checksum=0;
	                    try
	                    {
	                            FileReader f=new FileReader(sourceFolder); 
	                            BufferedReader reader= new BufferedReader(f);
	                            long filelength=sourceFolder.length(); 
	                            while((line=reader.readLine())!=null)
	                            {
	                                copiedstring=copiedstring+line;
	                                char[] charArray = line.toCharArray();
	                                     for(int i=0;i<charArray.length;i++)
	                                     {  
	                                         checksum=(checksum+((byte)charArray[i]*artifact[i%4]));
	                                     }
	                            }
	                            String path=destinationFolder.getParent();
	                            String newfolder=destinationFolder.getName().substring(0, destinationFolder.getName().lastIndexOf("."));
	                            String newpath=path+"\\"+newfolder;
	                            	                            File newFolder = new File(newpath);
	                            if (!newFolder.exists()) 
	                            {
	                                newFolder.mkdir();
	                                writer(checksum,filelength,newFolder,sourceFolder,copiedstring,updateFile);
	                                copiedstring="";
	                            }
	                            else
	                            {
	                               // String newfilename=newFolder.getPath()+"\\"+checksum+"-"+filelength+getFileExtension(sourceFolder);
	                               // File forcheck=new File(newfilename);
	                                
	                                  	 writer(checksum,filelength,newFolder,sourceFolder,copiedstring,updateFile);
	                                
	                            }
	                    }
	                    catch(IOException e)
	                    {
	                        e.printStackTrace();
	                    }
	                }
	        }
	        else
	        {
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
	                        copy(srcFile, destFile, updateFile);
	                    }
	                }
	            else
                {
                    int i=0;
                  
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
                                checkoutwriter(filetocopy,destinationFolder,updateFile);
                                count++;
                        }
                        else
                        {
                            if(count==0)
                                checkoutwriter(sourceFolder,destinationFolder,updateFile);

                        }
                    }
	         
	        }
	    }
	   public int calculateChecksum(String sourceFolder) throws IOException
	   {
		   String line="";
		   int checksum1=0;
		   int artifact[]={1,3,7,11,17};
		   FileReader f=new FileReader(sourceFolder); 
           BufferedReader reader= new BufferedReader(f);
           long filelength=sourceFolder.length(); 
           while((line=reader.readLine())!=null)
           {
         
               char[] charArray = line.toCharArray();
                    for(int i=0;i<charArray.length;i++)
                    {  
                    	checksum1=(checksum1+((byte)charArray[i]*artifact[i%4]));
             
	                }
           }
           return checksum1;
	   }

	    public static void writer(int checksum,long filelength,File file,File srcfile,String copiedstring,String updateFile)
	            {
	               try
	                {
	                    String ex=getFileExtension(srcfile);
	                    String duppath=file.getPath()+"\\"+checksum+"-"+filelength+ex;
	                    FileWriter fw=new FileWriter(duppath);    
	                    fw.write(copiedstring); 
	                    fw.close();
	   	                updatemanifesto(checksum,filelength,file,srcfile,ex,updateFile);
	                }
	                catch(IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }

/**
 * @param file name-name of the file to get extension
 * @return extension of the file name
 *
 */
	    
	    private static String getFileExtension(File file)
	    {
	    	String extension=file.getName();
	    	if(extension.lastIndexOf(".") != -1 && extension.lastIndexOf(".") != 0)
	    		return "."+extension.substring(extension.lastIndexOf(".")+1);
	    	else return "";
	    }
	    public static void updatemanifesto(int checksum,long filelength,File newFolder,File oldFolder,String ex,String updateFile)
	            {
	                try
	                {
	                    String content = checksum+"-"+filelength+ex; 
	                    String name=oldFolder.getName();
	                    String path=oldFolder.getPath();
	                    File fdate=new File(updateFile);
	                    String f=updateFile+"\\"+"activity"+"\\"+manifestName+"-"+number_of_manifestFiles+".txt";
	                    File file=new File(f);
	                    FileWriter fw=new FileWriter(file,true);
	                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	                    fw.write(System.lineSeparator()+"File_Name: "+content);
	                    fw.write("  Original_File_Name:"+name +", Original path: "+path);
	                    fw.write("File Created at"+sdf.format(fdate.lastModified())+System.lineSeparator());
	                    fw.close();                        
	                }
	                catch(IOException e)
	                {}
	            }
	    
	    public static void checkoutwriter(File sourceFolder,File destinationFolder,String updateFile)
	    {
	        try
	        {
	            String line="",copiedstring="";
	            //System.out.println(sourceFolder);
	            FileReader f=new FileReader(sourceFolder);
	                        BufferedReader reader= new BufferedReader(f);
	                        while((line=reader.readLine())!=null)
	                        {
	                            copiedstring=copiedstring+line;   
	                        }
	                        String prevfol=destinationFolder.getParentFile().getParent(); 
	                        String path=destinationFolder.getParent();                     
	                        String newfolder=destinationFolder.getName();      
	                        File test=new File(path);
	                        String parentname=test.getName();
	                        String finalfol=parentname+"."+newfolder.substring(newfolder.lastIndexOf(".")+1);
	                        String newpath=prevfol+"\\"+finalfol;  
	                        
	                        checkout_writer(sourceFolder,copiedstring,updateFile,newpath,finalfol);
	                            copiedstring="";
	                            newpath="";
	                           	   test.delete();
	                           		     	                  
	        }
	        catch(IOException e)
	        {
	                e.printStackTrace();
	        }
	    }
	  	    public static void checkout_writer(File srcfile,String copiedstring,String updateFile,String newpath,String finalfol)
	            {
	               	try
	                {
	               	    FileWriter fw=new FileWriter(newpath);    
	                    fw.write(copiedstring);	                   
	                    fw.close();
	                   
	                    updatecheckoutmanifesto(srcfile,finalfol,updateFile);
	                }
	                catch(IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	 	    public static void updatecheckoutmanifesto(File Fullpath,String newname,String updateFile)
	            {
	                try
	                {
	                    String originalname=Fullpath.getName();
	                    String originalpath=Fullpath.getPath();
	                    File fdate=new File(updateFile);
	                    String f=updateFile+"\\"+"activity"+"\\"+manifestName+"-"+number_of_manifestFiles+".txt";
	                    File file=new File(f);
	                    FileWriter fw=new FileWriter(file,true);
	                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	                    fw.write(System.lineSeparator()+"File Name: "+newname);
	                    fw.write("Original File Name: "+originalname);
	                    fw.write("Path Copied From: "+originalpath+" ");
	                    fw.write(" Date and Time File Created "+sdf.format(fdate.lastModified())+System.lineSeparator());
	                    fw.close();                        
	                }
	                catch(IOException e)
	                {}
	            }
}
