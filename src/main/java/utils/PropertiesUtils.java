package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
public class PropertiesUtils {

    String propertyFileName=null;
    public void setPropertyFile( String propertyFileName){
        this.propertyFileName=propertyFileName;
    }


    public String gettingValueOfProperty(String property) throws Exception{
        Properties prop = new Properties();
        InputStream input = null;
        String requiredPropertyValue = null ;
        try{
            input = new FileInputStream(propertyFileName);
            // load a properties file
            prop.load(input);
            // get the property value
            requiredPropertyValue=prop.getProperty(property);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(input!=null)
            {
                input.close();
            }

        }
        return requiredPropertyValue;
    }







}
