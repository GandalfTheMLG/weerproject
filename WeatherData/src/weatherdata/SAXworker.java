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
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts){
        if(qName.equals("WEATHERDATA")){
            CurrentData = new Dataset();
            MeasurementCounter = 0;
        }else{
        
            if(qName.equals("MEASUREMENT")){
                MeasurementCounter++;
            }else{
                nextdata = qName;
            }
        }
        
        
    }
    
    @Override
    public void endElement(String uri, String localName,String qName){
        if(qName.equals("WEATHERDATA")){
            Data.add(CurrentData);
            //Send DATA.remove to server worry about this later
            Data.remove();
            
        }
    }
    
    private void Datacheck(){
        //TODO: check data for missing values or deviant temps
    }
    
    @Override
    public void characters(char[] ch, int start, int length){
        
        String str = Arrays.toString(ch);
        int value;
        
        switch(nextdata) {
            case "STN": 
                str.replaceAll("\\.","");
                value = Integer.parseInt(str);
                CurrentData.meas[MeasurementCounter].stn= value;
                break;
            case "DATE":
                CurrentData.meas[MeasurementCounter].date = str;
                break;
            case "TIME":    
                CurrentData.meas[MeasurementCounter].time = str;
            case "TEMP":    
                value = Integer.parseInt(str);
                
                CurrentData.meas[MeasurementCounter].temp= value;
            case "DEWP":  
                value = Integer.parseInt(str);
                CurrentData.meas[MeasurementCounter].dewp= value;
            case "STP":  
                value = Integer.parseInt(str);
                CurrentData.meas[MeasurementCounter].stp= value;
            case "SLP":  
                value = Integer.parseInt(str);
                CurrentData.meas[MeasurementCounter].slp= value;
            case "VISIB":  
                value = Integer.parseInt(str);
                CurrentData.meas[MeasurementCounter].visib= value;
            case "WDSP":  
                value = Integer.parseInt(str);
                CurrentData.meas[MeasurementCounter].wdsp= value;
            case "PRCP":  
                value = Integer.parseInt(str);
                CurrentData.meas[MeasurementCounter].prcp= value;
            case "SNDP":  
                
            case "FRSHTT":  
                
            case "CLDC":  
                
            case "WNDDIR":
        }
    }    
        
    
}
