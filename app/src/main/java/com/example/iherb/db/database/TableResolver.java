package com.example.iherb.db.database;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableResolver {


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String getColumnsNameAndType(Class<? extends Entity> entity) {
        List<String> columns = new ArrayList<>();
        List<String> references = new ArrayList<>();
        for (Field field : entity.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Column.class) && field.getAnnotation(Column.class).include()) {
                String item = field.getName() + " " + field.getAnnotation(Column.class).type();
                columns.add(item);
                String ref = field.getAnnotation(Column.class).reference();
                if (!ref.isEmpty()) {
                    references.add(ref);
                }
            }

        }
        String result = String.join(", ", columns);
        if (references.size() != 0) {
            result += ", " + String.join(", ", references);
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getSqlCreateTableString(Class<? extends Entity> table) {


        return "create table " +
                getTableName(table) +
                "(" +
                getColumnsNameAndType(table) +
                ");";
    }


    public static String getTableName(Class<? extends Entity> table) {
        String name = table.getSimpleName();
        StringBuilder result = new StringBuilder().append(name.substring(0, 1).toLowerCase());
        for (Character character : name.substring(1).toCharArray()) {
            if (Character.isUpperCase(character))
                result.append("_").append(Character.toLowerCase(character));
            else
                result.append(character);
        }
        return result.toString();
    }
}
