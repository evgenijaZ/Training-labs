package edu.training.task10;

import java.lang.reflect.Field;

class Injector {
    public static void inject(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Sum.class)) {
                Sum sum = field.getAnnotation(Sum.class);
                field.setAccessible(true); // should work on private fields
                try {
                    field.set(instance, sum.a() + sum.b());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}