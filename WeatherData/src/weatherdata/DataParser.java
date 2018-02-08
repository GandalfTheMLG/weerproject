
package weatherdata;

import java.net.*;
import java.io.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class DataParser {
    Socket Sock;
    
    
    DataParser(int port){
        try{
            Sock = new Socket("localhost", port);        
            InputStream is = Sock.getInputStream();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXworker parser = new SAXworker();
            saxParser.parse(is, parser);
            
            
            
        }catch(Exception e){}
    }
    
    
}
