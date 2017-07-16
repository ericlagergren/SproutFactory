package models;

public class Recipe {
    public String name;
    public String imagePath;

    public Recipe() {
    }

    public Recipe(String recipesName, String imagePath) {
        this.name = recipesName;
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        return imagePath != null ? imagePath.equals(recipe.imagePath) : recipe.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}