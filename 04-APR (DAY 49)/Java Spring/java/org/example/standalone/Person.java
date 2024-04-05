package org.example.standalone;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {
    private List<String> friends;
    private Map<String,Integer> feeStructure;
    private Properties properties;

    public void setProperties(Properties properties){
        this.properties = properties;
    }
    public Properties getProperties(){
        return properties;
    }
    public void setFeeStructure(Map<String, Integer> feeStructure){
        this.feeStructure = feeStructure;
    }
    public Map<String, Integer> getFeeStructure(){
        return feeStructure;
    }
    public List<String> getFriends(){
        return friends;
    }
    public void setFriends(List<String> friends){
        this.friends = friends;
    }

    public String toString(){
          return "Person [friends=" + friends + "[FeeStructure: " + feeStructure +"] and properties are=" + properties + "]";
    }
}
