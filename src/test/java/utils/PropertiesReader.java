package utils;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {

    //private final String propertyFilePath = System.getProperty("user.dir") + "/env/default/browser.properties";

    static Properties prop = new Properties();
    public static void load(String propertyFilePath)
    {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(propertyFilePath);
        } catch (Exception e) {
            System.out.println("Configuration file is not found. terminating Process !!!");
            System.exit(0);
        }


        try {
            prop.load(fis);
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static String get(String key) {

        String PropertyFileValue = null;
        /*FileInputStream fis = null;

        try {
            fis = new FileInputStream(propertyFilePath);
        } catch (Exception e) {
            System.out.println("Configuration file is not found. terminating Process !!!");
            System.exit(0);
        }

        Properties prop = new Properties();
        try {
            prop.load(fis);
        } catch (IOException e) {

            e.printStackTrace();

        }
*/

        Boolean isFileEmpty = prop.isEmpty();


        if (isFileEmpty == false) {
            PropertyFileValue = prop.getProperty(key);

        } else {
            System.out.println("Configuration file is empty. Processing is terminated");
            System.exit(0);
        }

        return PropertyFileValue;


    }
}
