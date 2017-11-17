/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.Program;

//import javafx.beans.property.StringProperty;

/**
 *
 * @author greg1
 */
public class Data {
//    
//    private String _LastName;
//    private StringProperty _LastNameSP;
//    private String _FirstName;
//    private StringProperty _FirstNameSP;
//    private String _Birthday;
//    private StringProperty _BirthdaySP;
//    private String _Birthplace;
//    private StringProperty _BirthplaceSP;
//    private String _CaseNumber;
//    private StringProperty _LastCaseNumberSP;
//    private String _NameOfOriginCourt;
//    private StringProperty _NameOfOriginCourtSP;
//    private String _DayOfImprisonment;
//    private StringProperty _DayOfImprisonmentSP;
//    private String _DayOfFact;
//    private StringProperty _DayOfFactSP;
//    private String _Necrou;
//    private StringProperty _NecrouSP;
    
    
//    public Data(String t1,String t2, String t3,String t4,String t5,String t6,String t8,String t10,String t11){
//        //System.out.println("test2");
//        this._LastName = t1;
//        this._FirstName = t2;
//        this._Birthday = t3;
//        this._Birthplace = t4;
//        this._CaseNumber = t5;
//        this._NameOfOriginCourt = t6;
//        this._DayOfImprisonment = t8;
//        this._DayOfFact = t10;
//        this._Necrou = t11;
//    }
//    public  Data(){
//        //System.out.println("test2");
//        this._LastName = "";
//        this._FirstName = "";
//        this._Birthday = "";
//        this._Birthplace = "";
//        this._CaseNumber = "";
//        this._NameOfOriginCourt = "";
//        this._DayOfImprisonment = "";
//        this._DayOfFact = "";
//        this._Necrou = "";
//    }
//         
//         
//         public void setLastName(String lastname) {
//             this._LastName = lastname;
//         }
//         
//         public void setFirstName(String firstname) {
//             this._FirstName = firstname;
//         }
//         
//         public void setBirthday(String birthday) {
//             this._Birthday = birthday;
//         }
//         
//         public void setBirthplace (String birthplace) {
//             this._Birthplace = birthplace;
//         }
//         
//         public void setCaseNumber ( String casenumber ) {
//             this._CaseNumber = casenumber;
//         }
//         
//         public void setNameOfOrigin( String nameoforigin) {
//             this._NameOfOriginCourt = nameoforigin;
//         }
//         
//         public void setDayofImprisonment(String dayofimprisonment ){
//             this._DayOfImprisonment = dayofimprisonment;
//         }
//         
//         public void setDayOfFact(String dayoffact) {
//             this._DayOfFact = dayoffact;
//         }
//         
//         public void setNecrou ( String ecrou ){
//             this._Necrou = ecrou;
//         }
//         
//         public String getLastName() {
//             return(this._LastName);
//         }
//         
//         public String getFirstName(){
//             return(this._FirstName);
//         }
//         
//         public String getBirthday(){
//             return(this._Birthday);
//         }
//         
//         public String getBirthplace(){
//             return(this._Birthplace);
//         }
//         
//         public String getCaseNumber(){
//             return(this._CaseNumber);
//         }
//         
//         public String getNameOfOrigin(){
//             return(this._NameOfOriginCourt);
//         }
//         
//         public String getDayOfImprisonment(){
//             return(this._DayOfImprisonment);
//         }
//         
//         public String getDayOfFact(){
//             return(this._DayOfFact);
//         }
//         
//         public String getNecrou(){
//             return(this._Necrou);
//         }
         
   static public boolean TestVoid(String _LastName, String _FirstName, String _Birthday,String _Birthplace, String _CaseNumber, String _NameOfOriginCourt,String _DayOfImprisonment,String _DayOfFact , String _Necrou){
        return(_LastName.isEmpty() && _FirstName.isEmpty() && _Birthday.isEmpty() && _Birthplace.isEmpty() && _CaseNumber.isEmpty() && _NameOfOriginCourt.isEmpty() && _DayOfImprisonment.isEmpty() && _DayOfFact.isEmpty() && _Necrou.isEmpty());
    }
    
    static public boolean TestVoidWithOutEcrou(String _LastName, String _FirstName, String _Birthday,String _Birthplace, String _CaseNumber, String _NameOfOriginCourt,String _DayOfImprisonment,String _DayOfFact){
        return(_LastName.isEmpty() && _FirstName.isEmpty() && _Birthday.isEmpty() && _Birthplace.isEmpty() && _CaseNumber.isEmpty() && _NameOfOriginCourt.isEmpty() && _DayOfImprisonment.isEmpty() && _DayOfFact.isEmpty());
    }
    
    static public double TestError(String _LastName, String _FirstName, String _Birthday,String _Birthplace, String _CaseNumber, String _NameOfOriginCourt,String _DayOfImprisonment,String _DayOfFact){
        double count = 0;
        if(!_LastName.isEmpty())
            count++;
        if(!_FirstName.isEmpty())
            count++;
        if(!_Birthday.isEmpty())
            count++;
        if(!_Birthplace.isEmpty())
            count++;
        if(!_CaseNumber.isEmpty())
            count++;
        if(!_NameOfOriginCourt.isEmpty())
            count++;
        if(!_DayOfImprisonment.isEmpty())
            count++;
        if(!_DayOfFact.isEmpty())
            count++;
                    
        count++;
        count++;
        return(count/10.0f);
        
    }
    
    
   public static String choiceMotif( String motif ){
       
       switch(motif){
           case "vols et delits assimiles":
               return("1");
           case "coups et blessures":
               return("2");
           case "escroquerie":
               return("3");
           case "port d’armes prohibé":
               return("4");
           case "conduite en état d’ivresse":
               return("5");
           case "viol":
                return("6");
           case "pédophilie":
                return("7");
           case "abus de confiance":
                return("12");
           case "homicide":
                return("14");
           case "proxénétisme":
                return("15");
           default:
               return("0");
       }
   }
   
   
   
   
   public static String getNmotif(String motif) {
       switch (motif){
           case "1":
               return("vols et delits assimiles");
           case "2":
               return("coups et blessures");
           case "3":
               return("escroquerie");
           case "4":
               return("conduite en état d’ivresse");
           case "5":
               return("conduite en état d’ivresse");
           case "6":
               return("viol");
           case "7":
               return("pédophilie");
           case "12":
               return("abus de confiance");
           case "14":
               return("homicide");
           case "15":
               return("proxénétisme");
           default:
               return("");
               
       }
   }
   
   
      static public boolean DateValide(String date){
        String aux[] = date.split("-");
        boolean continuer = true;
        //System.out.println("test 1 : "+!(aux[0].length() == 4) +" test 2 : "+!(aux[1].length() == 2) + " test 3 : "+!(aux[2].length() == 2));
        if(aux.length == 3){
                if(!(aux[0].length() == 4)){
                    continuer = false;
                }
                if(!(aux[1].length() == 2)){
                    continuer = false;
                }
                if(!(aux[2].length() == 2) ){
                    continuer = false;
                }
        } else {
            continuer = false;
        }
        System.out.println("continuer = "+continuer);
        return continuer;
    }

}
