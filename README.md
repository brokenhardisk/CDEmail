# CDEmail
This program assumes the following:
1. There would be 'n' number of processes running (instances of this program)
2. All the instances would be available till the program finishes.
3. The DB already has a number of rows and will remain static during the execution of the code

Prerequisites:
1. Modify the config.properties file before you run this program.
For Example: If the program will be run across 3 different machines, P1, P2, P3
And for P1, you want to run 3 threads, for P2 4 threads, for P3 5 threads.

Modify the config file to have Process ID different for all the three instances starting from 1 onwards, number of process as the sum of all instances running.
The number of threads is to set the total threads you want to run for the current process and set them accordingly.

P.S. The process Ids should be in natural increasing order 1,2,3.. and so on. Please refrain from randomly assigning any number to the process ID.
SMTP:
I have used FakeSMTP Server, the jar of which is included in the project.
Before you run the program, just double click the FakeSMTP jar available in the jars folder and start the server

The main class is the SendMail.java
It spawns the threads, gets the email rows from the db and sends the mails.

I have used MySQL db for this program.
The Folder 'SQL' has the sql script to create the database 'test' and in it the 'emailqueue' table which would have a few dummy records in it.