package com.fmont.recipe.controllers;

import com.fmont.recipe.model.Recipe;
import com.fmont.recipe.repositories.RecipeRepository;
import com.fmont.recipe.services.RecipeService;
import com.fmont.recipe.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {


    @Mock
    RecipeService recipeService;

    IndexController indexController;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndex() {

        String indexPage = indexController.getIndex(model);
        assertEquals(indexPage,"index");
        verify(model,times(1)).addAttribute(eq("recipes"),anySet());
        verify(recipeService,times(1)).getRecipes();
    }
}