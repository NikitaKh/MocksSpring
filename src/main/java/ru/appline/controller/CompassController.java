package ru.appline.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Compass;
import ru.appline.logic.CompassModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class CompassController {

    private static final CompassModel compassModel = CompassModel.getCompassInstance();

    @PostMapping(value = "/createCompass", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> createCompass(@RequestBody Compass compass) {
        compassModel.cleanCompass();
        compassModel.addCompass(1, compass);

        HashMap<String, Object> map = new HashMap<>();
        map.put("success", "true");
        map.put("message", "Поздравляем вы задали диапазоны сторон!");

        return map;
    }

    @GetMapping(value = "/getCompass", produces = "application/json")
    public Map<Integer, Compass> getCompass() {
        return compassModel.getCompass();
    }

    @GetMapping(value = "/getSide", consumes = "application/json", produces = "application/json")
    public ResponseEntity findSide(@RequestBody HashMap<String, Integer> side) {
        String parsedSide = compassModel.parsCompass(side.get("degree"));
        if(parsedSide.equals("Invalid value!")){
            Map<String, String> map = new HashMap<>();
            map.put("message", "Invalid value!");
            return new ResponseEntity<>(
                    map,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("side", parsedSide);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.OK);
        }
    }
}
