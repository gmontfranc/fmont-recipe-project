package com.fmont.recipe.bootstrap;

import com.fmont.recipe.model.*;
import com.fmont.recipe.repositories.CategoryRepository;
import com.fmont.recipe.repositories.RecipeRepository;
import com.fmont.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository,
                           RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading recipes via bootstrap");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //getting Unit of measures
        Optional<UnitOfMeasure> eachUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> tableSpoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> teaSpoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> dashUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> pinchUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if (!pinchUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> cupUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        //Getting optionals
        UnitOfMeasure each = eachUnitOfMeasureOptional.get();
        UnitOfMeasure cup = cupUnitOfMeasureOptional.get();
        UnitOfMeasure tableSpoon = tableSpoonUnitOfMeasureOptional.get();
        UnitOfMeasure teaSpoon = teaSpoonUnitOfMeasureOptional.get();
        UnitOfMeasure pinch = pinchUnitOfMeasureOptional.get();
        UnitOfMeasure dash = dashUnitOfMeasureOptional.get();

        //Optional for categories


        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }


        //Getting categories optionals
        Category mexican = mexicanCategoryOptional.get();
        Category american = americanCategoryOptional.get();

        //Guacamole

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("Good luck");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Good luck notes");


        guacamoleRecipe.setNotes(guacNotes);

        guacamoleRecipe.addIngredient(new Ingredient("Ripe avocados", new BigDecimal(2), each));
        guacamoleRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Fresh lime juice or lemon juice", new BigDecimal(2),each));
        guacamoleRecipe.addIngredient(new Ingredient("Minced red onion or thinly sliced green onion", new BigDecimal(2),teaSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(2),tableSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoon));
        guacamoleRecipe.addIngredient(new Ingredient("Freshly grated black pepper", new BigDecimal(2), dash));
        guacamoleRecipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), each));

        guacamoleRecipe.getCategories().add(american);
        guacamoleRecipe.getCategories().add(mexican);

        recipes.add(guacamoleRecipe);

        return recipes;


    }
}
