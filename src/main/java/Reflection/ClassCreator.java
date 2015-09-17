package ReflectionExample;

import java.lang.reflect.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Bogdan Kukharskiy on 16.09.15.
 */
public class ClassCreator {
    public ClassCreator() {
    }

    // fields – поля , которые надо засетить в будущем экземпляре класса. Ключем выступает имя поля, значением в мапе – значение

    public static Object getObject(String className) {
        Class c = null;
        try {
            c = Class.forName(className);     //получаем объект типа Class по заданному имени
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Field[] allFields = new Field[0];
        if (c != null) {
            allFields = c.getDeclaredFields();
        }
        String s = "";
        int mod;
        for (Field field : allFields) {
            Class fieldType = field.getType();
            System.out.println("Имя: " + field.getName());
            System.out.println("Тип: " + fieldType.getName());
            mod = field.getModifiers();
            if (Modifier.isPublic(mod)) s += " public";
            if (Modifier.isPrivate(mod)) s += " private";
            if (Modifier.isFinal(mod)) s += " final";
            if (Modifier.isProtected(mod)) s += " protected";
            if (Modifier.isStatic(mod)) s += " static";
            System.out.println("Модификатор доступа: " + s);
            s = "";
            System.out.println("- - -");
        }
        return c;
    }


    //overload
    public static Object getObject(String className, Map<String, Object> fields) {
        Class c = null;
        try {
            c = Class.forName(className);     //получаем объект типа Class по заданному имени
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            object = c.newInstance();       //создаем новый объект заданного класса

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field[] allFields = new Field[0]; //получаем массив с полями
        if (c != null) {
            allFields = c.getDeclaredFields();
        }

        for (Field field : allFields) {
            Iterator<Map.Entry<String, Object>> s = fields.entrySet().iterator();
            Map.Entry<String, Object> element;
            while (s.hasNext()) {
                element = s.next();
                if (element.getKey().equals(field.getName())) {
                    try {
                        field.setAccessible(true);
                        field.set(object, element.getValue());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        System.out.println("\n- проверка созданного экземпляра класса -");
        //вызовем методы для проверки что класс создался и поля заполнены

        Method[] methods = new Method[0];   //получаем массив с методами
        if (c != null) {
            methods = c.getMethods();
        }

        for (Method method : methods) {
            for (Field field : allFields) {
                if (method.getName().equalsIgnoreCase("get" + field.getName())) {
                    try {
                        System.out.println(field.getName() + " = " + method.invoke(object, null));  //null т.к. у вызываемых методов нет параметров (здесь мы в этом уверены)
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return object;
    }

}
