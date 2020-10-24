package ru.appline.controller;


import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PetController {

    private static final PetModel petModel = PetModel.getPetInstance();
    private static final AtomicInteger id = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> createPet(@RequestBody Pet pet) {
        petModel.add(id.getAndIncrement(), pet);

        HashMap<String, Object> map = new HashMap<>();
        map.put("success", "true");
        map.put("message", "Поздравляем вы создали " + (id.get() - 1) + "го питомца!");

        return map;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }


    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/deletePet", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> deletePet(@RequestBody Map<String, Integer> id) {
        petModel.delete(id.get("id"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", "true");
        map.put("message", "Поздравляем вы удалили " + id.get("id") + "го питомца!");
        return map;
    }

    @PutMapping(value = "/editPet/{id}", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> editPet(@PathVariable int id, @RequestBody Pet pet) {
        petModel.edit(id, pet);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", "true");
        map.put("message", "Поздравляем вы изменили " + id + "го питомца!");
        return map;
    }
}
