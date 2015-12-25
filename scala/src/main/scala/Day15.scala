import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

object Day15 {

  val INGREDIENT_PATTERN = """(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""".r
  case class Ingredient(name: String, capacity: Int, durability: Int, flavor: Int, texture: Int, calories: Int)

  def weakCompositions(num: Int, termCount: Int): List[List[Int]] = {
    require(termCount > 0)
    if (termCount == 1) {
      return List(List(num))
    }
    var allCompositions = ListBuffer[List[Int]]()
    (0 to num).map { i =>
      allCompositions ++= weakCompositions(num - i, termCount - 1).map(List(i) ++ _)
    }
    allCompositions.toList
  }

  def calcRecipeScoreAndCalories(ingredients: List[Ingredient], recipe: List[Int]): (Int, Int) = {
    require(recipe.length == ingredients.length)
    val totalCapacity = Math.max(0, ingredients.indices.map { i =>
      recipe(i) * ingredients(i).capacity
    }.sum)
    val totalDurability = Math.max(0, ingredients.indices.map { i =>
      recipe(i) * ingredients(i).durability
    }.sum)
    val totalFlavor = Math.max(0, ingredients.indices.map { i =>
      recipe(i) * ingredients(i).flavor
    }.sum)
    val totalTexture = Math.max(0, ingredients.indices.map { i =>
      recipe(i) * ingredients(i).texture
    }.sum)
    val totalCalories = Math.max(0, ingredients.indices.map { i =>
      recipe(i) * ingredients(i).calories
    }.sum)
    (totalCapacity * totalDurability * totalFlavor * totalTexture, totalCalories)
  }

  def calcOptimalRecipeScore(ingredients: List[Ingredient]): Int = {
    val allRecipes = weakCompositions(100, ingredients.length)
    allRecipes.map(calcRecipeScoreAndCalories(ingredients, _)).map(_._1).max
  }

  def calcOptimalRecipeScore500Calories(ingredients: List[Ingredient]): Int = {
    val allRecipes = weakCompositions(100, ingredients.length)
    allRecipes.map(calcRecipeScoreAndCalories(ingredients, _)).filter(_._2 == 500).map(_._1).max
  }

  def parseIngredients(input: String): List[Ingredient] = {
    INGREDIENT_PATTERN.findAllIn(input).map {
      case INGREDIENT_PATTERN(name, capacity, durability, flavor, texture, calories) => Ingredient(name, capacity.toInt, durability.toInt, flavor.toInt, texture.toInt, calories.toInt)
    }.toList
  }

  def solve(): (Int, Int) = {
    val ingredients = parseIngredients(DataFolder.openFile("day15.txt").mkString)
    (calcOptimalRecipeScore(ingredients), calcOptimalRecipeScore500Calories(ingredients))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part1 == " + part2)
  }

}
