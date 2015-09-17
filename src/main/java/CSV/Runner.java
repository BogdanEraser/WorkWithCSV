package CSV;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bogdan Kukharskiy on 24.08.2015.
 */
public class Runner {
    public static void main(String[] args) {
        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(new Product());
        productList.add(new Product(111, "asaa", "babab", new BigDecimal("4.4"), LocalDate.now(), LocalDate.now().plusYears(1)));
        productList.add(new Product(222, "qwea", "zszsz", new BigDecimal("98.123"), LocalDate.now(), LocalDate.now().plusYears(1)));
        productList.add(new Product(333, "zxcv", "kkkjjh", new BigDecimal("0.864"), LocalDate.now(), LocalDate.now().plusYears(1)));

        MyCSVFileWriter fileWriter = new MyCSVFileWriter();
        fileWriter.writeProductListToCSV("D:\\1.csv", productList, true); //пишем в файл заданный список
        fileWriter.writeToCSVRandomData("D:\\2.csv", 10);       //пишем в файл 10 строк случаных данных
        fileWriter.writeToCSVRandomDataToFileSize("D:\\3.csv", 30000);    //пишем в файл случаные данные, при заданном размере файла

        MyCSVFileReader fileReader = new MyCSVFileReader();
        List<Product> productsFromFile = fileReader.readFromFile("D:\\1.csv");    //читаем из файла

        for (int i = 0; i < productsFromFile.size(); i++) {
            System.out.println(productsFromFile.get(i));        //выводим на экран
        }
    }
}
