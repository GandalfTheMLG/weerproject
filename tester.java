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
/**
 *
 * @author Jesse
 */
public class tester extends DefaultHandler{
    
    
    @Override
    public void startDocument(){
     System.out.println("Opening XML file");   
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts){
        System.out.println(qName);
    }
    @Override
    public void endElement(String uri, String localName,String qName) throws SAXException{
        if(qName.equals("WEATHERDATA")){
            System.out.println(qName);
            //Send DATA.remove to server worry about this later
            //error(new SAXParseException("end of data", "cheap", "solution", 0, 0));
            
        }
    }
    @Override
    public void endDocument(){
        System.out.println("End of XML file");
    }
    
    @Override
    public void characters(char[] ch, int start, int length){
        if(length == 1){
            return;
        }
        if(ch[0] == '\n' || ch[0] == '\t' || ch[0] == ' '){
            return;
        }
        System.out.println(new String(ch,start,length));
    }
}
