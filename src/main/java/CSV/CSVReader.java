package CSV;

import java.util.List;

/**
 * Created by Bogdan Kukharskiy on 24.08.2015.
 */
public interface CSVReader {
    List<Product> readFromFile(String fileName);
}
