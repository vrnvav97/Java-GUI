This is the Final Year Project (CIS 162 Project 4
Baby Names Database) from Green Valley State University, Michigan. This Project consists of a database which is a collection of baby names from U.S. The U.S. Social Security Administration has a database that contains all births since 1880. This database has over one million records. Each record contains a name, gender, number of births
with that name, and a year. The data has been simplified and cleaned up and also this database doesn't contains records with fewer than 100 births in a year. 

The description about the whole project is as follows:

A particular baby is represented with a class called BabyName which has certain required instance variables defined in it. 

There is a class defined as BabyNamesDatabase which contains various methods to perform operations on the database.

	List of Methods defined in this class are:
		1. readBabyNameData(String filename) :  To read all the data from the file and load it in arraylist of defined in class/

		2.  countAllNames() : To count total number names contained in the file.

		3. countAllGirls() : To count total number of girls that are there in the list

		4. countAllBoys() : To count total number of boys that are there in the list

		5. searchForYear(int year) : To return the list of all the babies born in the year given through parameter

		6.  mostPopularGirl(int year) : To find the name of most popular girl in a year given through input parameters

		7. mostPopularBoy(int year) : To find the name of most popular boy in a year given through input parameters

		8. 	searchForName(String name) : To search for a particular name in the list and the output will be the list of all the years in which the given named baby was born

		9. searchForName(String name) : To generate a list of top ten names of babies in a year given through parameter

The Project also contains the GUI version of the project with the BabyNamesDatabaseGUI as class name. This represents all the methods defined in BabyNamesDatabase class with the graphical version so that it is easy and friendly for a non technical person to use it. The GUI version is properly build with all the messages and alert boxes implemented at places whereever necessary, ex. 

		 if the database has not been loaded,
		 if the name field is empty when attempting a search,
		 if the year field is empty or an invalid integer when displaying other results,
		 Most Popular if no year is provided or it is an invalid integer, and many more errors as such

For a technical person he can run the program through command line.