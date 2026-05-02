package src.main.java;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.util.List;


public class ReadCSV {
    /**
     * Reads a CSV file and returns a list of objects
     * @param filepath
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> List<T> readCSV(Reader filepath, Class<T> clazz){
        CSVReader csvreader = new CSVReader(filepath);
        CsvToBean<T> parser = new CsvToBeanBuilder<T>(csvreader).withType(clazz).build();
        return parser.parse();
    }

    public static void handleError(FileNotFoundException error){
        System.out.println("File not found");
    }


}

