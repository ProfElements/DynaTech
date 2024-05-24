package me.profelements.dynatech.utils;

import com.google.common.base.Preconditions;

public class TimedRecipe extends Recipe {
    private int TIME_IN_TICKS;

    protected TimedRecipe() {
        super();
    }

    public static TimedRecipe init() {
        return new TimedRecipe();
    }

    @Override
    protected TimedRecipe getInstance() {
        return this;
    }

    public final int getTimeInTicks() {
        return this.TIME_IN_TICKS;
    }

    public TimedRecipe setTimeInTicks(int ticks) {
        Preconditions.checkNotNull(ticks, "The recipe's time in ticks should not be null");
        this.TIME_IN_TICKS = ticks;
        return this;
    }

    @Override
    public TimedRecipe build() {
        Preconditions.checkNotNull(this.TIME_IN_TICKS, "The recipe's time in ticks should not be null");
        super.validate();
        return this;
    }

    @Override
    public void register(RecipeRegistry registry) {
        TimedRecipe res = this.build();
        res.getRecipeType().register(res.getInput(), res.getOutput()[0]);
        registry.addRecipe(res);
    }
}
