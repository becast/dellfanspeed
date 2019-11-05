# dellfanspeed - A Java based R710 Fan Control Script

## Requisites

- A Java runtime (works with Open-JDK and Java 8 - 13)
- impitool installed

## Installation

From Source:
Clone the Repo and compile it with your preferred IDE.
Upload the dellfancontrol.jar, /config/ and /lib/ to /opt/dellfanspeed
Copy the dellfanspeed.service to your /etc/systemd/system folder and run systemctl enable dellfanspeed.service

Binary:
Download the package and extract it to /opt/dellfanspeed
Copy the dellfanspeed.service to your /etc/systemd/system folder and run systemctl enable dellfanspeed.service

## Configuration

The config file in /config/fanspeed.conf allows you to change various settings.

```text
dellfanspeed{
    #Checktime in Seconds
    refresh = 35
    # Temperatures
	temperatures = ["30","35","40","50","60"]
	# Fan Speed at temperature -1 = return automatic control
	speed = ["15","20","30","38","-1"]
}
```

refresh = 35 - Time between temperature checks in seconds.  
temperatures = ["30","35","40","50","60"] - Array of temperatures to correspond with the set fan speeds below. You can have as many values as possible, the only caveat is that the same amount of Speeds are configured.    

speed = ["15","20","30","38","-1"] - Array of Fan speeds in %, it must have the same amount of values as the temperature array above. e.g. at 30°C CPU the Fanspeed is 15%. -1 returns control to IPMI, if the temperature sinks below that treshold again, it will return to your configured values.  


## Credits

The idea comes from [nmaggioni's Fan Controller](https://github.com/nmaggioni/r710-fan-controller) I just transplanted the idea into Java and added some features I wanted.