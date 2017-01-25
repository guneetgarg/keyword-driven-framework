/*package com.dish.util;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;






public class Customercreate_ExecuteTest 

{
	
	

//	private static org.testng.log4testng.Logger log = Logger.getLogger(Logger.class);
	
	
	
	 public WebDriver driver;
	// String BrowserType="Chrome";
	  int[] index = new int[300];
	  int count=0;
	  int counttemp=-1;
	 @Parameters("browser")
	 
	 @BeforeClass
	 
	 public void setup(String browser)
	 
	 {
		 
		 
		// System.out.println(tr.getName());
		

	   //   log.trace("Trace Message!");
	   //   log.debug("Debug Message!");
	  //    log.info("Info Message!");
	  //    log.warn("Warn Message!");
	  //    log.error("Error Message!");
	  //    log.fatal("Fatal Message!");
	     
	    
 
	 
	 @Test(dataProvider="hybridData")
	     public void Dataproviderfun (
	    		 String Notes,
	    		 String Execution ) throws Exception {
	         // TODO Auto-generated method stub
		
		 
		 
	     if(Execution.equalsIgnoreCase("Test")&& TestResult.contains("Fail")&& AccountNumber.equalsIgnoreCase("") ||Execution.equalsIgnoreCase("Test")&& TestResult.equalsIgnoreCase("")&& AccountNumber.equalsIgnoreCase(""))
	     
	     {
	    	 
	    	 
	    	 
	    	//System.out.println(result.getName());
	    	
	    		readExcelFile file = new readExcelFile();
				
				
				Sheet	axiomSheet = file.readExcel(System.getProperty("user.dir")+"\\","DataXVTVROAxiom_Regression.xlsx" , "AxiomExecution");
				
				 counttemp++;	
	     
			 int xrow=axiomSheet.getLastRowNum();
			 int yrow=-axiomSheet.getFirstRowNum();
			 
			 Row row = axiomSheet.getRow(index[counttemp]);
		
	     TestListener scrn=new TestListener ();
	     
	  // scrn.onConfigurationFailure();
			// result.setStatus(ITestResult.SUCCESS);
	    
	   
	        // Customer creation script 
	     
	    	// System.out.println(SSN);
	    //	 System.out.println(ZipCode);			
	     
	    	
	    ReadObject x=new ReadObject();
	    Properties p= x.getObjectRepository();
	    	 
	
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());	
	    	 
String URL= p.getProperty("url");


Logger log = Logger.getLogger("devpinoyLogger");
	   

log.debug("Starting Axiom test");


log.debug("Starting Login test");
axiom_reusable.Login x1=new Login();
x1.login(driver,URL,UserName,Password,log,row,scrn);
log.debug("Ending Login test");

log.debug("Starting Account Type Selection test");
axiom_reusable.AccTypeSelection y1=new AccTypeSelection();
y1.AccTypeSel(driver,PromoCode,FName,LName,BusinessName,AddLine1,City,State,ZipCode,FlowType,Products_Services,UserName,ALaCarte_ProductSelection,Install_Type,Module,TestCase,log,row,scrn);
log.debug("Ending Account Type Selection test");

log.debug("Starting Programming test");
axiom_reusable.Programming z1=new Programming();
z1.Prog(driver,Products_Services, UserName,FlowType,Core_Credit_Description_Pricer,Core_ProgramSelection,ALaCarte_ProductSelection,Prem_ProductSelection_Common,HD_ProductSelection_Common,Int_Description_Pricer,Adult_ProductSelection,WildBlue_Selection,WildBlue_Type,ALaCarte_Description_Pricer,Adult_Description_Pricer,WildBlue_Description_Pricer,WildBlue_Charges_Pricer,Adult_Price_Pricer, ALaCarte_Price_Pricer,log,row,scrn);
log.debug("Ending Programming test");
//Thread.sleep(9000);
log.debug("Starting Reciever test");

axiom_reusable.Receiver a1=new Receiver();
a1.recieve(driver,Products_Services, UserName,FlowType,Install_Type,Core_Description_Pricer,Core_Price_Pricer,NoOfTV,HD_DVR, HD,SD_DVR,SD, DVR_4K,Plan,Rec_Solution,Rec1,Customized_Solution,Cust_Rcvr_Solution,RecvUpgrade_Description_pricer,WildBlue_Description_Pricer, WildBlue_Charges_Pricer,log,row,scrn);
log.debug("Ending Reciever test");

log.debug("Starting Appointment test");

axiom_reusable.Appointment b1=new Appointment();

b1.appoint(driver,UserName,Install_Type,log,row,scrn);
log.debug("Ending Appointment test");

log.debug("Starting Household test");

axiom_reusable.HouseHold c1=new HouseHold();
c1.HouseInfo(driver, FName, LName, AddLine1, City, State, ZipCode, UserName, Password, FlowType, BusinessName, Install_Type, Email, Core_Credit_Description_Pricer, Core_Description_Pricer, Core_Price_Pricer, log,row,scrn);
//c1.HouseInfo(driver,FName,LName,AddLine1,City,State,ZipCode,UserName,Password,FlowType,BusinessName,Install_Type,Email,Core_Credit_Description_Pricer,Core_Description_Pricer,Core_Price_Pricer,log,row);
log.debug("Ending Household test");


log.debug("Starting Eligibility test");

axiom_reusable.Eligibility d1=new Eligibility();

d1.eligible(driver,UserName,FName,LName,FlowType,Plan,Products_Services,DOB,ClubDish,Core_ProgramSelection, Core_Credit_Description_Pricer,Prem_ProductSelection_Common,Prem_Credit_Description_Pricer,HD_ProductSelection_Common,ALaCarte_ProductSelection,ALaCarte_Credit_Description_Pricer,Module,Tax_ID,BusinessName,DataPlan,Core_Description_Pricer,SSN,log,row,scrn);
		
log.debug("Ending Eligibility test");	

log.debug("Starting Account Info test");


axiom_reusable.AccountInfo e1=new AccountInfo();

e1.AcccountInf(driver,UserName,FlowType,Plan,DataPlan,OneTimeCost,Email,Products_Services,Core_Description_Pricer,Core_Price_Pricer,Int_Description_Pricer,Prem_Description_Pricer,Prem_Price_Pricer,ALaCarte_Description_Pricer,ALaCarte_Price_Pricer,Adult_Description_Pricer,Adult_Price_Pricer,WildBlue_Description_Pricer,WildBlue_Charges_Pricer,Core_Credit_Description_Pricer,Core_Credit_Pricer,HD_Description_Pricer,HD_Price_Pricer,Prem_Credit_Description_Pricer,Prem_Credit_Pricer,ALaCarte_Credit_Description_Pricer,ALaCarte_Credit_Pricer,Adult_Credit_Description_Pricer,Adult_Credit_Pricer,Rec_Solution,Customized_Solution,Cust_Rcvr_Solution,RecvUpgrade_Description_pricer,RecvUpgrade_Charges_pricer,RecvCredit_Description_pricer,RecvUpgrade_Credits_pricer,RecvOther_Description_Pricer,RecvOther_Charges_Pricer,Prem_ProductSelection_Common,Email,log,row,scrn);

log.debug("Ending Account Info test");



log.debug("Starting Payment test");


axiom_reusable.Payment f1=new Payment();

f1.paym(driver,UserName,FlowType,Plan,RecvUpgrade_Charges_pricer,PaymentMethod,HD_ProductSelection_Common,Core_Description_Pricer,Core_Price_Pricer,FName,LName,Payment_Type,log,row,scrn);

log.debug("Ending Payment test");


log.debug("Starting CheckOut test");


axiom_reusable.CheckOut g1=new CheckOut();

g1.Check(driver,Plan,UserName,Password,Products_Services,HD_ProductSelection_Common,DataPlan,FlowType,Core_Description_Pricer,Core_Price_Pricer,Int_Description_Pricer,log,row,scrn);

log.debug("Ending CheckOut test");


log.debug("Starting Complete Order Test");
		


axiom_reusable.CompleteOrder h1=new CompleteOrder();
h1.completeord(driver,Products_Services,AccountNumber,Video_DishNET_ID,ClaimID,Plan,Install_Type,PromoCode,PaymentMethod,log,row,scrn);


log.debug("Ending Complete Order Test");

log.debug("Ending Axiom test");

row.getCell(2).setCellValue("Pass-"+timestamp);

	     
	    
	     
	     
	     
	     } else
	     {
	    	 
	    	 
	     }
	     
	   
              
	                  
	                
	     }
	 
	 
	 @DataProvider(name="hybridData")
	 
	 
     public Object[][] getDataFromDataprovider() throws IOException
	 
	 {
		 
		 
     Object[][] object = null;
     Object[][] objectTemp = null;
     readExcelFile file = new readExcelFile();
 //Read keyword sheet
 Sheet axiomSheet = file.readExcel(System.getProperty("user.dir")+"\\","DataXVTVROAxiom_Regression.xlsx" , "AxiomExecution");
 
 int x=axiomSheet.getLastRowNum();
 int y=-axiomSheet.getFirstRowNum();
 //Find number of rows in excel file
     int rowCount = axiomSheet.getLastRowNum()-axiomSheet.getFirstRowNum();
     object = new Object[rowCount][176];
  //   int count=0;
    // int   [] index = new int[300] ;
     
   
     for (int i = 0; i < rowCount; i++)
     
     {
         //Loop over all the rows
         Row row = axiomSheet.getRow(i+1);
         
     //    System.out.println("last cell no"+row.getLastCellNum());
         //Create a loop to print cell values in a row
     //    Execution.equalsIgnoreCase("Test")&& TestResult.contains("Fail")&& AccountNumber.equalsIgnoreCase("") ||Execution.equalsIgnoreCase("Test")&& TestResult.equalsIgnoreCase("")&& AccountNumber.equalsIgnoreCase(""))
	     
         if(row.getCell(1).toString().equalsIgnoreCase("Test")&&row.getCell(2).toString().contains("Fail")&& row.getCell(30).toString().equalsIgnoreCase("") ||row.getCell(1).toString().equalsIgnoreCase("Test")&& row.getCell(2).toString().equalsIgnoreCase("")&& row.getCell(30).toString().equalsIgnoreCase(""))
         {
         for (int j = 0; j < row.getLastCellNum(); j++) 
         
         {
             //Print excel data in console
        	 
             object[i][j] = row.getCell(j).toString();
             
             //.toString();
         
             
           //  System.out.println("object retrieved values "+object[i][j]);
         }
         index[count]=i+1;
         count++;
         }
         
     }
  //   System.out.println("");
     
     
     objectTemp = new Object[count][176];
     
     
     
     //Need to fetch row index and pass in the below code : as normal  1,2,3,4 sequence
     for (int i = 0; i < count; i++)
         
     {
         //Loop over all the rows
         Row row = axiomSheet.getRow(index[i]);
         
      //   System.out.println("last cell no"+row.getLastCellNum());
         //Create a loop to print cell values in a row
         
         
         for (int j = 0; j < row.getLastCellNum(); j++) 
         
         {
             //Print excel data in console
        	 
             objectTemp[i][j] = row.getCell(j).toString();
            
             //.toString();
           //  count++;
           //  System.out.println("object retrieved values "+objectTemp[i][j]);
         }
         
         
     }
     
     
     
     
  
      return objectTemp;    
     }
	 
	 
 @AfterClass
	 
	 public void teardown(){
		 
		driver.close();
	
		
	 
	 }
	 
	 
	 
	 
	 
	 
	

}
*/