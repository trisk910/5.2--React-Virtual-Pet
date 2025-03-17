package cat.itacademy.s05.t02.Models;

import cat.itacademy.s05.t02.Exceptions.RoboTypeNotFoundException;
import cat.itacademy.s05.t02.Models.Enums.RoboType;
import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "Robos")
public class Robo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private RoboType type;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int happiness;
    private Long userId;

    private int originalHealth;
    private int originalAttack;
    private int originalDefense;
    private int originalSpeed;

    public Robo(String name, RoboType type, Long userId) {
        this.name = name;
        this.type = type;
        setStats(type);
        happiness = 100;
        this.userId = userId;
    }

    public Robo() {
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoboType getType() {
        return type;
    }

    public void setType(RoboType type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    private void setStats(RoboType roboType) {
        Random random = new Random();
        switch (roboType) {
            case melee:
                this.health = this.originalHealth = 50 + random.nextInt(51);
                this.attack = this.originalAttack = 70 + random.nextInt(31);
                this.defense = this.originalDefense = 30 + random.nextInt(21);
                this.speed = this.originalSpeed = 85 + random.nextInt(16);
                break;
            case ranged:
                this.health = this.originalHealth = 60 + random.nextInt(41);
                this.attack = this.originalAttack = 60 + random.nextInt(41);
                this.defense = this.originalDefense = 40 + random.nextInt(21);
                this.speed = this.originalSpeed = 60 + random.nextInt(41);
                break;
            case tank:
                this.health = this.originalHealth = 100 + random.nextInt(51);
                this.attack = this.originalAttack = 50 + random.nextInt(31);
                this.defense = this.originalDefense = 70 + random.nextInt(31);
                this.speed = this.originalSpeed = 30 + random.nextInt(21);
                break;
            default:
                throw new RoboTypeNotFoundException("Invalid pet type: " + roboType);
        }
    }

    // Getters and setters for the new fields
    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public int getOriginalAttack() {
        return originalAttack;
    }

    public void setOriginalAttack(int originalAttack) {
        this.originalAttack = originalAttack;
    }

    public int getOriginalDefense() {
        return originalDefense;
    }

    public void setOriginalDefense(int originalDefense) {
        this.originalDefense = originalDefense;
    }

    public int getOriginalSpeed() {
        return originalSpeed;
    }

    public void setOriginalSpeed(int originalSpeed) {
        this.originalSpeed = originalSpeed;
    }

    public void reduceStats(boolean wonBattle) {
        if (wonBattle) {
            this.health -= this.health * 0.02;
            this.attack -= this.attack * 0.02;
            this.defense -= this.defense * 0.02;
            this.speed -= this.speed * 0.02;
            if(this.happiness < 100) {
                this.happiness += 4;
            } else if (this.happiness > 100) {
                this.happiness = 100;
            }
        } else {
            this.health -= this.health * 0.10;
            this.attack -= this.attack * 0.10;
            this.defense -= this.defense * 0.10;
            this.speed -= this.speed * 0.10;
            if(this.happiness > 0) {
                this.happiness -= 8;
            } else if (this.happiness < 0) {
                this.happiness = 0;
            }
        }
    }
}