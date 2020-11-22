package com.fmont.recipe.controllers;

import com.fmont.recipe.services.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {


    @Mock
    RecipeService recipeService;

    @InjectMocks // As it needs a recipe service
    IndexController indexController;

    @Mock
    Model model;

    @Test
    public void getIndex() {
        String indexPage = indexController.getIndex(model);
        assertEquals(indexPage,"index");
        verify(model,times(1)).addAttribute(eq("recipes"),anySet());
        verify(recipeService,times(1)).getRecipes();
    }
}