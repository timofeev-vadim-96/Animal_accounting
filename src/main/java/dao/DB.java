package dao;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DB<T> {
    private final List<T> animals;
    private static final String jsonDataBaseName = "jsonDataBase.txt";

    public DB() {
        this.animals = new LinkedList<>();
    }

    public DB<T> add(T animal) {
        animals.add(animal);
        return this;
    }

    public DB<T> remove(int index) {
        animals.remove(index);
        return this;
    }

    /**
     * Создание БД в случае, если она отсутствует в корневой папке проекта
     */
    public boolean createJsonDataBase() {
        File jsonDataBase = new File(jsonDataBaseName);
        if (!jsonDataBase.exists()) {
            try {
                return jsonDataBase.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create a file with a local data base...");
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<T> getAnimals() {
        return animals;
    }

    /**
     * Сохранение объектов списка в текстовый файл в json-формате построчно
     */
    public void saveChanges() {
        try (FileWriter writer = new FileWriter(jsonDataBaseName, false)) { //будет перезаписывать
            Gson gson = new Gson();
            for (T animal : animals) {
                String jsonAnimal = gson.toJson(animal);
                writer.write(jsonAnimal + "\n");
            }
        } catch (IOException e) {
            System.out.println("Программа упала при попытке сохранить данные в локальную БД...");
            e.printStackTrace();
        }
    }

    public String getPath() {
        return jsonDataBaseName;
    }
}
