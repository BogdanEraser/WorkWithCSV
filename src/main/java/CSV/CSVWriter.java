package CSV;

import java.util.List;

/**
 * Created by Bogdan Kukharskiy on 24.08.2015.
 */
public interface CSVWriter {
    void writeProductListToCSV(String destinationFileName, List<Product> newData, boolean appendToFile);
    void writeToCSVRandomData(String destinationFileName,  int lineValue);
}
