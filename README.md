# TestAutomation-Photon
TestAutomation-Photon.

Framework details

After importing the project to eclipse you can able to see the multiple folders which listed below

src/test/java ---> folder
	
	com.base ----------> package which contains reporting class,loggerclass and CapabilitiesAndWebDriverUtils class
		             inside you can able to see the reusable methods,this will be common for all the platforms(mob,web)
	
	com.pom  ----------> package which contains all the object repositories for both mobile platforms (maintained page wise)
			     it also contains user activity resuable method inside the pom classes wherever it is applicable
		
	com.pom_RWD -------> package which contains all the object repositories for both web (maintained page wise)
 			     it also contains user activity resuable method inside the pom classes wherever it is applicable	

	com.run -----------> Which contains runner clas for both mobile and web 
			     1.RunnerClass ----> to execute mobile platforms
			     2.RunnerClassWeb ----> to execute Web platforms
	cucumber-html-reports
	com.stepdefinition ---> which contains all the step definition file for mobile
	
	com.stepdefinitionWeb----> which contains all the step definition file for web

Smoke --- > folder

	Under this you can able to find the feature file for all the platforms(mob and web)
	Web related feature file will contain RWD as prefix
	Unique Tags are provided for each and every scenario inside the feature file 
	Using this Tags you can able to run the specific scenarios

Configuation---> Folder
	
	configuration ---> Which contains Capabilities value which is required to run our scripts in cloud platforms (Browserstack),Local web and real devices
			  only need to change the platformName parameter to run the scripts in respective device and os platform . 
			  To run the test in real device you need to modify the remaining parameters based on your mobile ex : UDID, OS Version, Device Name

                            PlatformName's :

                            To run on Brower Stack Web: Web_Bstack
                            To run on Browser Stack Android: BrowserStackAndroid
			   To run on Browser Stack IOS: BrowserStackIOS
			   To run on Real Android device: Android
			   To run on Real IOS device: iOS


Target -----> Folder

	Path: ResultsMobile >> cucumber-html-reports >> js >> overview-failures.html
	Here you can able to see the execution report (feature wise,scenatio wise,tag wise,failure wise along with failure step screenshot)
	
Screenshots ----> All the screen shots will be stored here

Log -------> AutomationExecutionStatus.log   Logs will be written here

pom.xml ---- > Where we maintain our dependencies


Steps to execute the sample script:

	1. Open the project 
	2. Open com.run package
	3. Open RunnerClass.java file
	4. There you can able to see the tags option,need to change that alone (Sample tags provided below)
	   Sample Tags: @SmokeMobRC,@55561,@55562,@55566 
  5.  Make sure needed platformName is given in config file. 
	6. And run as junitTest
