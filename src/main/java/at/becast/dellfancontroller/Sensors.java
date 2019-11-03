package at.becast.dellfancontroller;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Temperature;

import java.util.List;

public class Sensors {

    public void Sensors(){

    }

    public double getAverageTemp(){
    Components components = JSensors.get.components();
    List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            int cpucount = 0;
            double[] ctemp = new double[cpus.size()];
            for (final Cpu cpu : cpus) {
                ctemp[cpucount] = 0;
                if (cpu.sensors != null) {
                    //Print temperatures
                    List<Temperature> temps = cpu.sensors.temperatures;
                    int ccount = 0;
                    for (final Temperature temp : temps) {
                        ctemp[cpucount] += temp.value;
                        ccount++;
                    }
                    ctemp[cpucount] = ctemp[cpucount] / ccount;
                    //System.out.println("Average Temp (CPU): " + ctemp[cpucount]);
                }
                cpucount++;
            }
            double systemp = 0;
            for (double ctems : ctemp) {
                systemp += ctems;
            }
            return systemp / cpucount;
        }
        return -1;
    }
}
