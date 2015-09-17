package CSV;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Bogdan Kukharskiy on 24.08.2015.
 */
public class MyCSVFileWriter implements CSVWriter {

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                System.out.println("Ошибка закрытия потока");
                //LOGGER.error(String.format("Can't close %s", stream));
            }
        }
    }


    @Override
    public void writeProductListToCSV(String destinationFileName, List<Product> newData, boolean appendToFile) {
        File file = new File(destinationFileName);
        PrintWriter out = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла " + destinationFileName);
            }
        }


        try {
            if (appendToFile) {
                out = new PrintWriter(new FileOutputStream(file, true));// робимо append до файлу
            } else {
                out = new PrintWriter(file);
            }

            Iterator<Product> productIterator = newData.iterator();
            while (productIterator.hasNext()) {
                out.println(productIterator.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка записи в файл " + destinationFileName);
        } finally {
            closeStream(out);
        }
    }


    @Override
    public void writeToCSVRandomData(String destinationFileName, int lineValue) {
        File file = new File(destinationFileName);
        PrintWriter out = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла " + destinationFileName);
            }
        }
        BigDecimal price;
        try {
            out = new PrintWriter(file);
            int i = 0;
            while (i < lineValue) {
                price = new BigDecimal(Math.random()*10+(i*2)).setScale(2,BigDecimal.ROUND_HALF_DOWN);  //получаем случайную цену с округлением в финансовом формате
                out.println(new Product((i+101),"Product_"+(i+1),"Manufacturer_"+(i+1),price, LocalDate.now(),LocalDate.now().plusYears(1)));
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка записи в файл " + destinationFileName);
        } finally {
            closeStream(out);
        }
    }

    public void writeToCSVRandomDataToFileSize(String destinationFileName, long fileSize) {
        File file = new File(destinationFileName);
        PrintWriter out = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла " + destinationFileName);
            }
        }
        BigDecimal price;
        try {
            out = new PrintWriter(file);
            int i = 0;
            while (file.length() < fileSize) {
                price = new BigDecimal(Math.random()*100+(i*Math.random())).setScale(2,BigDecimal.ROUND_HALF_DOWN);  //получаем случайную цену с округлением в финансовом формате
                out.println(new Product((i+101),"Product_"+(1+i),"Manufacturer_"+(1+i),price, LocalDate.now(),LocalDate.now().plusYears(1)));
                i++;
                out.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка записи в файл " + destinationFileName);
        } finally {
            closeStream(out);
        }
    }
}
