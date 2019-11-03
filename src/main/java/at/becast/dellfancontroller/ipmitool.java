package at.becast.dellfancontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ipmitool {
    private static final Logger LOG = LoggerFactory.getLogger(ipmitool.class);
    private boolean automatic = true;
    private static String command = "ipmitool";

    public void ipmitool(){

    }

    public void switchmode(boolean auto){
        if(auto == false){
            automatic = false;
            sendcommand("raw 0x30 0x30 0x01 0x00");
        }else{
            automatic = true;
            sendcommand("raw 0x30 0x30 0x01 0x01");
        }
    }

    public void setspeed(int percentage){
        if(percentage <= 0){
            if(!automatic) {
                switchmode(true);
                return;
            }
        }else if(percentage > 100){
            percentage = 100;
        }
        if(automatic){
            switchmode(false);
        }
        sendcommand("raw 0x30 0x30 0x02 0xff 0x"+String.format("%02X", percentage));
    }

    private void sendcommand(String scommand){
        LOG.debug("IPMI Command " +command + " " + scommand);
        try {
            Runtime.getRuntime().exec(command + " " + scommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
