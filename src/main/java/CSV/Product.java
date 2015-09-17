package CSV;

//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
import java.math.BigDecimal;
import java.time.*;

/**
 * Created by Bogdan Kukharskiy on 24.08.2015.
 *
 * Класс отображающий строку CSV файла
 */
public class Product implements Comparable {

    private int upn;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private LocalDate manufactureDate;
    private LocalDate bestBeforeDate;

    public Product() {
        this.upn = 0;
        this.name = "N_A";
        this.manufacturer = "N_A";
        this.price = new BigDecimal("0.0"); //где-то вычитал, что так лучше работать с финансовыми значениями
        this.manufactureDate = LocalDate.now(); //берем текущую дату
        this.bestBeforeDate = LocalDate.now().plusYears(1);     //берем +1 год от текущей даты
    }
    public Product(int upn, String name, String manufacturer, BigDecimal price, LocalDate manufactureDate, LocalDate bestBeforeDate) {
        this.upn = upn;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.bestBeforeDate = bestBeforeDate;
    }

    @Override
    public int compareTo(Object o) {
        Product p = (Product) o;
        try {
            //сначала сравниваем по имени товара
            int result = name.compareTo(p.name);
            if (result != 0) {
                return result;
            }
            //затем сравниваем по UPN
            result = upn - p.upn;
            if (result != 0) {
                return result / Math.abs(result);
            }
        }
        catch (ClassCastException c){
            System.out.println("Ошибка приведения класса в методе сравнения." + c.toString());
        }
        return 0;
    }

    @Override
    public String toString() {
        String separator = ";";
        StringBuilder c = new StringBuilder().append(upn).append(separator).append(name).append(separator);
        c.append(manufacturer).append(separator).append(price).append(separator);
        c.append(manufactureDate).append(separator).append(bestBeforeDate);
        return c.toString();
    }
}
