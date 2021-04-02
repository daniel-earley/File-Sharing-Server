# File Sharing Server #

Made by Daniel Earley for CSCI 2040 Assignment 2.

### Project Information ###
This project uses sockets and multithreading to create a file sharing 
application. The application allows the user to upload and download files from 
a user-specified location. On the left side of the application is the client's 
(user's) shared folder, this folder holds a list of files that they may upload 
to the server. The right side of the application shows the files stored on the 
server. Above the file lists are 4 buttons; Upload, Download, Upload All and 
Download All. The Upload button will upload only the selected file whereas the 
Upload All button will upload all files in the shared folder. Likewise, the 
Download button will download only the selected file from the server, and the 
Download All button will download all files from the server. An image of the UI 
is below. ![Alt text](./resources/Running%20Application.png).

#### TL:DR #### 
File Sharing Server: User selects a shared folder and can upload files from the 
folder to the server, or download files from the server to the shared folder.

### Improvements ###
- Added Upload All and Download All buttons (upload or download all files)

### How To Run ###
1) Copy the link to the repository by clicking the green button that says 
**Code**, or by copying this link 
(https://github.com/daniel-earley/File-Sharing-Server.git). Alternatively 
   you could click the green button that says **Code** and then click 
**Download Zip**.
   
2) Next if you have copied the repository link, open a terminal and 
   navigate to a desired location to clone the repository to.
   
3) Type into the terminal: git clone https://github.com/daniel-earley/File-Sharing-Server.git

4) Next, for the easiest way to run the program use an IDE of your choice, 
   (this project was written in IntelliJ).
   
5) Next in the IDE open the folder called **File-Sharing-Server** (or whatever the name equivalent is), 
and navigate to ./src/sample/
   
6) From here run the FileServer.java file by either opening it and hitting (when in IntelliJ) 
   **ctrl + shift + f10** or by right clicking the file and selecting **Run**
   
7) If you want to use command line arguments while running the main class then open the Main.java 
   file and open the configurations drop down menu (Fig 2.1),from here select **Main** and then select 
   **edit configurations**. From here there will be a text box that will allow you to enter command line 
   arguments (Fig 2.2)
   
8) Once you are comfortable with or without command line arguments, run the Main.java file the same way
you ran the FileServer.java file.
   
- Fig 2.1

![Alt Text](./resources/Edit%20Configurations%20Menu.png)

- Fig 2.2

![Alt Text](./resources/Edit%20Configurations.png)

### Libraries and Java Version ###
- OpenJDK version 14.0.2
- JavaFX version 11.0.2
   


