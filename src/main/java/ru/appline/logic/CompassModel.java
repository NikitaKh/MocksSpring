package ru.appline.logic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompassModel implements Serializable {

    private static final CompassModel compassInstance = new CompassModel();

    private final Map<Integer, Compass> compassModel;

    public CompassModel() {
        compassModel = new HashMap<Integer, Compass>();
    }

    public static CompassModel getCompassInstance(){
        return compassInstance;
    }

    public void addCompass(int id, Compass compass){
        compassModel.put(id, compass);
    }

    public Map<Integer, Compass> getCompass(){
        return compassModel;
    }

    public void cleanCompass(){
        compassModel.clear();
    }

    public String parsCompass(int value){
        if(isTrue(compassModel.get(1).getEast(), value)){
            return "East";
        } else if((isTrue(compassModel.get(1).getNorth(), value)) || (value == 0)){
            return "North";
        } else if(isTrue(compassModel.get(1).getSouth(), value)){
            return "South";
        } else if(isTrue(compassModel.get(1).getWest(), value)){
            return "West";
        } else if(isTrue(compassModel.get(1).getNorthEast(), value)){
            return "NorthEast";
        } else if(isTrue(compassModel.get(1).getNorthWest(), value)){
            return "NorthWest";
        } else if(isTrue(compassModel.get(1).getSouthEast(), value)){
            return "SouthEast";
        } else if(isTrue(compassModel.get(1).getSouthWest(), value)){
            return "SouthWest";
        } else {
            return "Invalid value!";
        }
    }

    private boolean isTrue(String range, int value){
        int[] rangeArray = Arrays.stream(range.split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return (rangeArray[0] < value) && (rangeArray[1] > value);
    }
}
