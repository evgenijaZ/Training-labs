package edu.training.task05;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class aClass = TextWindow.class;
        var window = (TextWindow) aClass.getConstructor().newInstance();
        var allMethods = aClass.getMethods();
        for (var m : allMethods) {
            if (m.isAnnotationPresent(Marked.class))
                m.invoke(window);
        }

        for (var m : allMethods) {
            System.out.println("=========================================");
            System.out.println("name: " + m.getName());
            System.out.println("modifiers: " + Modifier.toString(m.getModifiers()));
            System.out.println("parameters: " + Arrays.stream(m.getParameterTypes()).map(Class::getName).collect(Collectors.toList()));
            System.out.println("return type: " + m.getReturnType());
        }
    }
}
