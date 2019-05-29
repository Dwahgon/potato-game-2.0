package projeto1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveLoad {
    /**
     * Saves the data of an object
     * @param data The data that will be saved
     * @param filename The name of the file that the data will be saved
     * @throws Exception This method might not be able to save the data
     */
    public static void Save(Serializable data, String filename) throws Exception{
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))){
            oos.writeObject(data);
        }
        
    }
    
    /**
     * Loads a saved object
     * @param filename The name of the file that we will attempt to load from
     * @return Th object loaded
     * @throws Exception It might not be able to read a file
     */
    public static Object Load(String filename) throws Exception{
        try(ObjectInputStream oos = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))){
            return oos.readObject();
        }
    }
    
    /**
     * Attempts to delete a save
     * @param filename The location of the file we will be deleting
     */
    public static void DeleteSave(String filename){
        try{
            //Try to delete save
            Files.delete(Paths.get(filename));
        }catch(IOException ex){
            System.out.println("Error!");
        }
    }
}
