package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath= "src/test/resources/test.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getBaseUrl(){
        String baseUrl = properties.getProperty("baseUrl");
        if(baseUrl!= null) {
            return baseUrl;
        }else {
            throw new RuntimeException("baseUrl not specified in the Configuration.properties file.");
        }
    }

    public String getProductsUrl() {
        String productsUrl = properties.getProperty("productsUrl");
        if(productsUrl != null){
            return productsUrl;
        }
        else{
            throw new RuntimeException("productsUrl not specified in the Configuration.properties file.");
        }
    }

    public String getCartUrl() {
        String cartUrl = properties.getProperty("cartUrl");
        if(cartUrl != null){
            return cartUrl;
        }
        else{
            throw new RuntimeException("cartUrl not specified in the Configuration.properties file.");
        }
    }

    public String getCheckoutStepOneUrl() {
        String checkoutStepOneUrl = properties.getProperty("checkoutStepOneUrl");
        if(checkoutStepOneUrl != null){
            return checkoutStepOneUrl;
        }
        else{
            throw new RuntimeException("checkoutStepOneUrl not specified in the Configuration.properties file.");
        }
    }

    public String getCheckoutStepTwoUrl() {
        String checkoutStepTwoUrl = properties.getProperty("checkoutStepTwoUrl");
        if(checkoutStepTwoUrl != null){
            return checkoutStepTwoUrl;
        }
        else{
            throw new RuntimeException("checkoutStepTwoUrl not specified in the Configuration.properties file.");
        }
    }

    public String getCheckoutCompleteUrl() {
        String checkoutCompleteUrl = properties.getProperty("checkoutCompleteUrl");
        if(checkoutCompleteUrl != null){
            return checkoutCompleteUrl;
        }
        else{
            throw new RuntimeException("checkoutCompleteUrl not specified in the Configuration.properties file.");
        }
    }
}
