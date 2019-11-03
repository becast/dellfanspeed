package at.becast.dellfancontroller.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


public final class Settings {
    private static Settings instance = null;
    private Config config;
    private static final Logger LOG = LoggerFactory.getLogger(Settings.class);
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
    
    private Settings(){
    	config = ConfigFactory.load(ConfigFactory.parseFile(new File(Paths.get("").toAbsolutePath().toString()+ "/config/fanspeed.conf")));
        try {
            config.checkValid(ConfigFactory.defaultReference(), "dellfanspeed");
        } catch (ConfigException.ValidationFailed ex) {
            LOG.error("Error loading Config! {}", (Object) ex.getStackTrace());
        }
    }

    public Iterator<Entry<String, ConfigValue>> getall(){
        return config.entrySet().iterator();
    }
    
    public String getString(String key){
    	return config.getString(key);
    }
    
    public Boolean getBoolean(String key){
    	return config.getBoolean(key);
    }
    
    public int getInt(String key){
    	return config.getInt(key);
    }
    
    public List<String> getStringList(String key){
    	return config.getStringList(key);
    }

    public List<Integer> getIntList(String key){
        return config.getIntList(key);
    }

}
