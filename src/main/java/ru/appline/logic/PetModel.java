package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {

    private static final PetModel petInstance = new PetModel();

    private final Map<Integer, Pet> petModel;

    public PetModel() {
        petModel = new HashMap<Integer, Pet>();
    }

    public static PetModel getPetInstance(){
        return petInstance;
    }

    public void add(int id, Pet pet){
        petModel.put(id, pet);
    }

    public Pet getFromList(int id){
        return petModel.get(id);
    }

    public Map<Integer, Pet> getAll(){
        return petModel;
    }

    public void delete(int id){
        petModel.remove(id);
    }

    public void edit(int id, Pet pet){
        petModel.replace(id, pet);
    }
}
