CECS 543 - ADVANCED SOFTWARE ENGINEERING

Source Code Management project: Merged
Chaitanya Vooradi
Email: vooradichaitanya@gmail.com
Student ID: 016230485


Introduction: Developing a git hub type of repository in java supporting check-in, check-out, Labeling manifest file and merge operations

List of files: 1. README.txt
               2. SCM.java
	       3. checkin.java
	       4. checkOut.java
	       5. readManifest.java
	       6. writeManifest.java
               7. copyFile.java
               8. copyfolder.java
               9. findParent.java
               10. Labelling.java
               11. merging.java
               12. writeXml.java

External Requirements: 
		- Java Runtime Environment
For Windows Operating System:

Windows 10 (8u51 and above)
Windows 8.x (Desktop)
Windows 7 SP1
Windows Vista SP2
Windows Server 2008 R2 SP1 (64-bit)
Windows Server 2012 and 2012 R2 (64-bit)
RAM: 128 MB
Disk space: 124 MB for JRE; 2 MB for Java Update
Processor: Minimum Pentium 2 266 MHz processor
Browsers: Internet Explorer 9 and above, Firefox

For Mac OS X:

Intel-based Mac running Mac OS X 10.8.3+, 10.9+
Administrator privileges for installation
64-bit browser
A 64-bit browser (Safari, for example) is required to run Oracle Java on Mac.

For Linux:

Oracle Linux 5.5+1
Oracle Linux 6.x (32-bit), 6.x (64-bit)2
Oracle Linux 7.x (64-bit)2 (8u20 and above)
Red Hat Enterprise Linux 5.5+1, 6.x (32-bit), 6.x (64-bit)2
Red Hat Enterprise Linux 7.x (64-bit)2 (8u20 and above)
Suse Linux Enterprise Server 10 SP2+, 11.x
Suse Linux Enterprise Server 12.x (64-bit)2 (8u31 and above)
Ubuntu Linux 12.04 LTS, 13.x
Ubuntu Linux 14.x (8u25 and above)
Ubuntu Linux 15.04 (8u45 and above)
Ubuntu Linux 15.10 (8u65 and above)
Browsers: Firefox

IDE used: Net Beans, Eclipse, IntelliJ IDEA

Build, Installation, and Setup:
		- To compile program in terminal: 
			> javac *.java 	
Usage:
		- To run java main program in terminal:
			> java SCM	
		- To create a repository: 
			- When prompted, enter source destination, target destination.
			- source destination is the directory of the file or folder you wish to make a repository of
			- target destination is the directory you wish to put your repository
		-To checkout the files
			-When Prompted, enter source destination, target destination and manifest file path/lable
			-source destination is the directory to recover the files in it
			-target destination is the directory to backup the files.
			-manifest file to select desired files to recover.
		-Labeling
			-Enter the manifest file path to lable
                        -Enter Number of lables
			-Give unique lables to identify manifest file
                -Merge
                        - Give repo folder and manifest file to merge particular version of files to checked out folder
			- Specify the check out folder
			- if the files in check out are changed compared to repo, three files are genereated in check out folder with extension MT(Target Folder File), MR (Repo file), MT (Grand Parent File).
Features:
		-Merge R project version with T project version.
		-Handling all types of exceptions

Bugs remaining: NA
