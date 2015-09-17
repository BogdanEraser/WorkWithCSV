package Reflection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Bogdan Kukharskiy on 15.09.15.
 */
public class Runner {
    public static void main(String[] args) {
        System.out.println("- просто информация о полях класса -");
        ClassCreator classCreator = new ClassCreator();
        ClassCreator.getObject("Reflection.MyClass");    //пример без задания полей, только инфа о классе

        Map<String,Object> fl = new HashMap<String, Object>();
        fl.put("i",15);
        fl.put("name", "Bob");
        fl.put("age", (byte)34);

        Object object = ClassCreator.getObject("Reflection.MyClass", fl);        //создание экземпляра и задание полей

    }

}
