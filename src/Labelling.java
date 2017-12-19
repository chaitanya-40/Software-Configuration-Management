package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
/**
*
* 
* @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
* 
* @description: Creates lable for manifest files
*/
public class Labelling {
public Labelling()
{
	
}

public void start()
{
	String manifest_path;
	String result = "";
	System.out.println("Enter the Path of Manifest File to Lable");
	  Scanner scan = new Scanner(System.in);
	  manifest_path = scan.nextLine();
	  File f= new File(manifest_path);
	  //f.getName();
	  FileWriter fw;
	  try {	
	fw = new FileWriter(f.getParent()+"\\"+"lable.txt");
	fw.write(f.getName()+": ");
		System.out.println("Enter Number of Lables not more then 4");
		int n=Integer.parseInt(scan.nextLine());
	    if(n<4)
		{
		   for(int i=1;i<=n;i++)
		   {
			   System.out.println("Enter Lable "+i);
			   if(i!=1)
			   {
				result=result+", ";
			   }
			   result=result+scan.nextLine();
				
		   }
		}
		else
		{
			System.out.println("Not supported for more then 4");
		}
			fw.write(result+System.lineSeparator());
	
		 FileReader fileReader = 
	              new FileReader(manifest_path);
		FileInputStream fis = new FileInputStream(f);
		BufferedReader br = new BufferedReader(fileReader);
		
		
		String line = "";
		
			while( (line = br.readLine()) != null){
			 result = result + System.lineSeparator()+line; 
			}
		result = "Lable: " + result;
		f.delete();
		FileOutputStream fos = new FileOutputStream(f);
		
			fos.write(result.getBytes());
			fos.flush();
		
		fw.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
}

}
