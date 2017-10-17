/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

/**
 *
 * @author greg1
 */
public class Data {
    
    private String _LastName;
    private String _FirstName;
    private String _Birthday;
    private String _Birthplace;
    private String _CaseNumber;
    private String _NameOfOriginCourt;
    private String _ExactNameJuridiction;
    private String _DayOfImprisonment;
    private String _Reason;
    private String _DayOfFact;
    
     Data(String t1,String t2, String t3,String t4,String t5,String t6,String t7,String t8,String t9,String t10){
        //System.out.println("test2");
        this._LastName = t1;
        this._FirstName = t2;
        this._Birthday = t3;
        this._Birthplace = t4;
        this._CaseNumber = t5;
        this._NameOfOriginCourt = t6;
        this._ExactNameJuridiction = t7;
        this._DayOfImprisonment = t8;
        this._Reason = t9;
        this._DayOfFact = t10;
    }
         Data(){
        //System.out.println("test2");
        this._LastName = "";
        this._FirstName = "";
        this._Birthday = "";
        this._Birthplace = "";
        this._CaseNumber = "";
        this._NameOfOriginCourt = "";
        this._ExactNameJuridiction = "";
        this._DayOfImprisonment = "";
        this._Reason = "";
        this._DayOfFact = "";
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
         
         public void setExactName( String exactname ){
             this._ExactNameJuridiction = exactname;
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
         
         public String getExactName(){
             return(this._ExactNameJuridiction);
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
         
         
    public boolean TestVoid(){
        return(this._LastName.isEmpty() && this._FirstName.isEmpty() && this._Birthday.isEmpty() && this._Birthplace.isEmpty() && this._CaseNumber.isEmpty() && this._NameOfOriginCourt.isEmpty() && this._ExactNameJuridiction.isEmpty() && this._DayOfImprisonment.isEmpty() && this._Reason.isEmpty() && this._DayOfFact.isEmpty());
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
        if(!this._ExactNameJuridiction.isEmpty())
            count++;
        if(!this._DayOfImprisonment.isEmpty())
            count++;
        if(!this._Reason.isEmpty())
            count++;
        if(!this._DayOfFact.isEmpty())
            count++;
                    
        return(count/10.0f);
        
    }
    
}
