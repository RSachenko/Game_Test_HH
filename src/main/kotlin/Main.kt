import kotlin.system.exitProcess

fun main() {
    println("================================================================")
    println("Добро пожаловать в игру! Вы готовы сразиться с монстрами?")
    println("1. Войти в игру")
    println("2. Выйти")
    print("Введите цифру для продолжения: ")
    val startChoice = readLine()?.toIntOrNull()

    if (startChoice == 1) {
        var player: Player? = null
        var monster: Monster? = null

        mainMenu@ while (true) {
            println("================================================================")
            println("1. Просмотр персонажа")
            println("2. Создать нового персонажа")
            println("3. Сразиться!")
            println("4. Выход")
            print("Введите цифру для продолжения: ")

            when (readLine()?.toIntOrNull()) {
                1 -> {
                    if (player != null) {
                        println("================================================================")
                        player.showStatus()
                    } else {
                        println("================================================================")
                        println("Персонаж не создан. Создайте нового персонажа с помощью опции 2.")
                    }
                }

                2 -> {
                    println("================================================================")
                    println("Новый персонаж создан.")
                    player = Player.createPlayer()
                    player.showStatus()
                }

                3 -> {
                    if (player != null) {
                        monster = Monster.createMonster()
                        println("================================================================")
                        monster.showStatus()
                        battleLoop@ while (true) {
                            println("================================================================")
                            println("1. Начать атаку")
                            println("2. Сбежать")
                            println("3. Выпить зелье")
                            print("Введите цифру: ")

                            when (readLine()?.toIntOrNull()) {
                                1 -> {
                                    if (player != null) {
                                        val attackModifierPlayer = player.attackModifier(monster.defense)
                                        val rollDicePlayer = player.rollDice(attackModifierPlayer)
                                        if (rollDicePlayer) {
                                            println("================================================================")
                                            println("Удар по монстру успешно совершен")
                                            player.attack(monster, player.damageRange.random())
                                            if (monster.health <= 0) {
                                                monster.health = 0
                                                monster.showStatus()
                                                println("Вы победили монстров и спасилт Мир! Добавлено 100 монет!")
                                                Player.money(player, 100)
                                                break@battleLoop
                                            } else println("================================================================")
                                            monster.showStatus()

                                        } else println("Защита монстра оказалась сильной, урон не был нанесен")
                                        println("================================================================")
                                        println("Монстр начинает атаку:")
                                        val attackModifierMonster = monster.attackModifier(player.defense)
                                        val rollDiceMonster = monster.rollDice(attackModifierMonster)
                                        if (rollDiceMonster) {
                                            println("================================================================")
                                            println("Монст смог нанести удар по игроку!")
                                            monster.attack(player, monster.damageRange.random())
                                            if (player.health <= 0) {
                                                player.health = 0
                                                player.showStatus()
                                                println("Вы проиграли! Ваш персонаж умер.")
                                                player = null
                                                break@battleLoop
                                            } else println("================================================================")
                                            player.showStatus()
                                        } else println("Вы успешно отбили атаку монстра, он не смог нанести вам урон!")
                                    }
                                }

                                2 -> break@battleLoop // Вернуться к основному меню
                                3 -> {
                                    if (player != null)
                                        player!!.point()

                                }
                                else -> println("Недопустимый вариант")

                            }
                        }
                    } else {
                        println("================================================================")
                        println("Персонаж не создан. Создайте нового персонажа с помощью опции 2.")
                    }
                }

                4 -> return

            }
        }
    } else if (startChoice == 2) exitProcess(0)
    else TODO("Недопустимый вариант")
}