/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import javafx.beans.property.StringProperty;

/**
 *
 * @author greg1
 */
public class Data {
    
    private String _LastName;
    private StringProperty _LastNameSP;
    private String _FirstName;
    private StringProperty _FirstNameSP;
    private String _Birthday;
    private StringProperty _BirthdaySP;
    private String _Birthplace;
    private StringProperty _BirthplaceSP;
    private String _CaseNumber;
    private StringProperty _LastCaseNumberSP;
    private String _NameOfOriginCourt;
    private StringProperty _NameOfOriginCourtSP;
    private String _DayOfImprisonment;
    private StringProperty _DayOfImprisonmentSP;
    private String _Reason;
    private StringProperty _ReasonSP;
    private String _DayOfFact;
    private StringProperty _DayOfFactSP;
    private String _Necrou;
    private StringProperty _NecrouSP;
     Data(String t1,String t2, String t3,String t4,String t5,String t6,String t8,String t9,String t10,String t11){
        //System.out.println("test2");
        this._LastName = t1;
        this._FirstName = t2;
        this._Birthday = t3;
        this._Birthplace = t4;
        this._CaseNumber = t5;
        this._NameOfOriginCourt = t6;
        this._DayOfImprisonment = t8;
        this._Reason = t9;
        this._DayOfFact = t10;
        this._Necrou = t11;
    }
         Data(){
        //System.out.println("test2");
        this._LastName = "";
        this._FirstName = "";
        this._Birthday = "";
        this._Birthplace = "";
        this._CaseNumber = "";
        this._NameOfOriginCourt = "";
        this._DayOfImprisonment = "";
        this._Reason = "";
        this._DayOfFact = "";
        this._Necrou = "";
    }
         
         
         public void setLastName(String lastname) {
             this._LastName = lastname;
         }
         
         public void setFirstName(String firstname) {
             this._FirstName = firstname;
         }
         
         public void setBirthday(String birthday) {
             this._Birthday = birthday;
         }
         
         public void setBirthplace (String birthplace) {
             this._Birthplace = birthplace;
         }
         
         public void setCaseNumber ( String casenumber ) {
             this._CaseNumber = casenumber;
         }
         
         public void setNameOfOrigin( String nameoforigin) {
             this._NameOfOriginCourt = nameoforigin;
         }
         
         public void setDayofImprisonment(String dayofimprisonment ){
             this._DayOfImprisonment = dayofimprisonment;
         }
         
         public void setReason( String reason ) {
             this._Reason = reason;
         }
         
         public void setDayOfFact(String dayoffact) {
             this._DayOfFact = dayoffact;
         }
         
         public void setNecrou ( String ecrou ){
             this._Necrou = ecrou;
         }
         
         public String getLastName() {
             return(this._LastName);
         }
         
         public String getFirstName(){
             return(this._FirstName);
         }
         
         public String getBirthday(){
             return(this._Birthday);
         }
         
         public String getBirthplace(){
             return(this._Birthplace);
         }
         
         public String getCaseNumber(){
             return(this._CaseNumber);
         }
         
         public String getNameOfOrigin(){
             return(this._NameOfOriginCourt);
         }
         
         public String getDayOfImprisonment(){
             return(this._DayOfImprisonment);
         }
         
         public String getReason(){
             return(this._Reason);
         }
         
         public String getDayOfFact(){
             return(this._DayOfFact);
         }
         
         public String getNecrou(){
             return(this._Necrou);
         }
         
    public boolean TestVoid(){
        return(this._LastName.isEmpty() && this._FirstName.isEmpty() && this._Birthday.isEmpty() && this._Birthplace.isEmpty() && this._CaseNumber.isEmpty() && this._NameOfOriginCourt.isEmpty() && this._DayOfImprisonment.isEmpty() && this._Reason.isEmpty() && this._DayOfFact.isEmpty() && this._Necrou.isEmpty());
    }
    
    public double TestError(){
        double count = 0;
        if(!this._LastName.isEmpty())
            count++;
        if(!this._FirstName.isEmpty())
            count++;
        if(!this._Birthday.isEmpty())
            count++;
        if(!this._Birthplace.isEmpty())
            count++;
        if(!this._CaseNumber.isEmpty())
            count++;
        if(!this._NameOfOriginCourt.isEmpty())
            count++;
        if(!this._DayOfImprisonment.isEmpty())
            count++;
        if(!this._Reason.isEmpty())
            count++;
        if(!this._DayOfFact.isEmpty())
            count++;
                    
        count++;
        return(count/10.0f);
        
    }
    
}
