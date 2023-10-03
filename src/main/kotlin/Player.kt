import kotlin.random.Random
import kotlin.random.nextInt

class Player(attack: Int, defense: Int, health: Int, damageRange: IntRange, var money: Int) :
    Creature(attack, defense, health, damageRange) {
    private var maxPoint = 4
    private val maxHealth = 100

    fun point() {
        if (maxPoint > 0) {
            val addPoint = (maxHealth * 0.3).toInt()

            if (health >= 70) {
                health = 100
                println("Вы использовали зелье, ваше здоровье $health HP")

            } else {

                println("Вы использовали зелье, ваше здоровье повышено на $addPoint")
                health += addPoint
                maxPoint--
            }

        } else
            println("У вас закончились зелья!")
    }

    fun showStatus() {
        println("Статистика персонажа:")
        println("Здоровье: $health")
        println("Атака: $attack")
        println("Защита: $defense")
        println("Монеты: $money")
    }

    companion object {
        fun createPlayer(): Player {
            val attack = Random.nextInt(1..30)
            val defense = Random.nextInt(1..30)
            val health = 100
            val damageRange: IntRange = 1..30
            val money = 0
            return Player(attack, defense, health, damageRange, money)
        }

        fun money(player: Player, addMoney: Int) {
            player.money += addMoney
        }


    }
}