package com.example.android.bakingproject;

import com.example.android.bakingproject.data.pojo.Ingredients;
import com.example.android.bakingproject.data.pojo.Steps;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public final class TypeUtil {
    private TypeUtil() {}

    public static final Type LIST_INGREDIENTS_TYPE = new TypeToken<List<Ingredients>>(){}.getType();
    public static final Type LIST_STEPS_TYPE = new TypeToken<List<Steps>>(){}.getType();
    public static final Type LIST_DISHES_TYPE = new TypeToken<List<Ingredients>>(){}.getType();

}
