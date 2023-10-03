import kotlin.random.Random
import kotlin.random.nextInt

class Monster(attack: Int, defense: Int, health: Int, damageRange: IntRange): Creature(attack, defense, health, damageRange) {
    fun showStatus() {
        println("Статистика монстра:")
        println("Здоровье: $health")
        println("Защита: $defense")
        println("Атака: $attack")
    }
    companion object {
        fun createMonster(): Monster {
            val attack = Random.nextInt(1..30)
            val defense = Random.nextInt(1..30)
            val health = 100
            val damageRange: IntRange = 1..30
            return Monster(attack, defense, health, damageRange)
        }
    }
}