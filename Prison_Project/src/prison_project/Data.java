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
    
    public boolean TestVoid(){
        return(this._LastName == "" && this._FirstName == "" && this._Birthday == "" && this._Birthplace == "" && this._CaseNumber == "" && this._NameOfOriginCourt == "" && this._ExactNameJuridiction == "" && this._DayOfImprisonment == "" && this._Reason == "" && this._DayOfFact == "");
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
