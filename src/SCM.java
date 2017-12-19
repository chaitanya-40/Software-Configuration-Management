package Repository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * 
 * @author Chaitanya Vooradi, 016230485, vooradichaitanya@gmail.com
 * 
 * @description: main program used to create new repositories and backup the repository based on user
 *               input
 *               1. Check in
 *               2. Check out
 *               3. Labeling
 *               4. Merging
 *               5. Exit
 */

public class SCM {
     public static int checkin_checkout=0;
     public static void main(String[] args) throws IOException {
     Scanner sc = new Scanner(System.in);
        while(true)
        {
        	System.out.println("Please select a number from given options");
            System.out.println("1.Checkin");
            System.out.println("2.Checkout");
            System.out.println("3.Lable Manifest");
            System.out.println("4.Merging");
            System.out.println("5.Exit");
            checkin_checkout=sc.nextInt();
            switch(checkin_checkout)
            {
                case 1:
                {      // Creates Repository                  
                		checkin cin=new checkin();
                		cin.start();           
                        break;
                }
                case 2:
                {        //creates backup for repository   
                		checkOut cout=new checkOut();
                		cout.start();
                		break;
                }
                case 3:
                {        //creates Lable for Manifest files   
                		Labelling lable=new Labelling();
                		lable.start();
                		break;
                }
                case 4:
                {        //Merge repo to target folder 
                		merging merge=new merging();
                		merge.start();
                		break;
                }
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select from the given Options");
            }
                   }
    } 
 }

