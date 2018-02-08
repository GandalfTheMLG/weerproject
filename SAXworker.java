/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherdata;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;
import java.util.*;





public class SAXworker extends DefaultHandler{
    
    Queue<Dataset> Data = new LinkedList();
    Dataset CurrentData;
    int MeasurementCounter;
    String nextdata;
    
    
    
    @Override
    public void startDocument(){
        
    }
    
    public void newParseSet(){
            CurrentData = new Dataset();
            MeasurementCounter = -1;
    }
    
    public Dataset parseSend(){
        Data.add(CurrentData);
        if(Data.size()>30){
            return Data.remove();
        }
        return null;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts){
        //if(qName.equals("WEATHERDATA")){

        //}else{
        
            if(qName.equals("MEASUREMENT")){
                MeasurementCounter++;
                CurrentData.meas.add(new Measurement());
                
                
            }else{
                nextdata = qName;
            }
        //}
        
        
    }
    
    @Override
    public void endElement(String uri, String localName,String qName) throws SAXException{
        
    }
    
    private void Datacheck(){
        //TODO: check data for missing values or deviant temps
    }
    
    @Override
    public void characters(char[] ch, int start, int length){
        String str = new String(ch, start, length);
        int value;
        if(nextdata == null){
            return;
        }
        str = str.replaceAll("\\.","");
        
        switch(nextdata) {
            case "STN": 
                
                value = Integer.parseInt(str);
                //System.out.println("stn:"+str);
                CurrentData.meas.get(MeasurementCounter).stn= value;
                nextdata = null;
                break;
            case "DATE":
                //System.out.println("date:"+str);
                CurrentData.meas.get(MeasurementCounter).date = str;
                nextdata = null;
                break;
            case "TIME":
                //System.out.println("time:"+str);
                CurrentData.meas.get(MeasurementCounter).time = str;
                nextdata = null;
                break;
            case "TEMP":
                //System.out.println("temp:"+str);
                value = Integer.parseInt(str);
                
                // Checking for validity of temperature
                value = checkValidTemp(value);
                CurrentData.meas.get(MeasurementCounter).temp= value;
                nextdata = null;
                break;
            case "DEWP":
                //System.out.println("dewp:"+str);
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).dewp= value;
                nextdata = null;
                break;
            case "STP":
                //System.out.println("stp:"+str);
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).stp= value;
                nextdata = null;
                break;
            case "SLP":  
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).slp= value;
                nextdata = null;
                break;
            case "VISIB":  
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).visib= value;
                nextdata = null;
                break;
            case "WDSP":  
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).wdsp= value;
                nextdata = null;
                break;
            case "PRCP":  
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).prcp= value;
                nextdata = null;
                break;
            case "SNDP":  
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).sndp= value;
                nextdata = null;
                break;
            case "FRSHTT":  
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).frshtt= value;
                nextdata = null;
                break;
            case "CLDC":  
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).cldc= value;
                nextdata = null;
                break;
            case "WNDDIR":
                value = Integer.parseInt(str);
                CurrentData.meas.get(MeasurementCounter).wnddir= value;
                nextdata = null;
                break;
        }
    }
    
    int checkValidTemp(int temp){
        //find the current average for this station.
        int curstn = CurrentData.meas.get(MeasurementCounter).stn;
        int average = 0;
        
        float result = 0;
        ArrayList<Dataset> array = new ArrayList(Data);
        for(int i = 0; i<array.size(); i++){
            
            Dataset check = array.get(i);
            average += check.meas.get(MeasurementCounter).temp;
            
            /*
            for(int j = 0; j<check.meas.size(); j++){
                if(check.meas.get(j).stn == curstn){
                    average += check.meas.get(j).temp;
                    count++;
                }
            }
            */
        }
        if(array.isEmpty()){
            return temp;
        }
        result = average /array.size();
        if(temp< result*0.8){
            //System.out.println("Temp adjusted from "+temp+" to "+((int)(result*0.8)));
            return (int)(result*0.8);
            
        }
        if(temp > result*1.2){
            //System.out.println("Temp adjusted from "+temp+" to "+((int)(result*1.2)));
            return (int)(result*1.2);
        }
        return temp;
       
    }
        
    // /srv/share/
}
