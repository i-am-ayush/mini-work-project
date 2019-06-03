package FormPopulator;


import bean.Member;
import dao.DatabaseConnector;
import form.Form;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FormPopulator {
    private final static Logger logger = Logger.getLogger(FormPopulator.class);

    public static <T extends Form> T populate(HttpServletRequest request, Class<T> tClass) {

        try {
            return populateForm(request, tClass);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return null;
    }

    public static <T extends Form> T populateForm(HttpServletRequest request, Class<T> tClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] variables = tClass.getDeclaredFields();
        T t = tClass.newInstance();
        for (Field f : variables) {
            System.out.println(f.getType());
            Method fieldSetter = null;
            fieldSetter = tClass.getMethod(setterMethodName(f),f.getType());
            if(f.getType()==int.class) {
                System.out.println("int1");
                int i=Integer.parseInt(request.getParameter(f.getName()));
                fieldSetter.invoke(t, i);
                System.out.println("int");
            }
            else if(f.getType()==double.class) {
                System.out.println("int2");
                fieldSetter.invoke(t, Double.parseDouble(request.getParameter(f.getName())));
                System.out.println("double");
            }
//            else if(f.getType()==Enum.class) {
//
//                if(request.getParameter(f.getName()).equals("seeker"))
//                fieldSetter.invoke(t, Member.MemberType.SEEKER);
//                else
//                    fieldSetter.invoke(t, Member.MemberType.SITTER);
//
//
//            }

            else{
                System.out.println("int3");
                fieldSetter.invoke(t, request.getParameter(f.getName()));
                System.out.println("string");
            }

        }
        return t;
    }

    private static String setterMethodName(Field f) {
        String name = f.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println("2");
        System.out.println(name);
        return "set" + name;
    }
}
