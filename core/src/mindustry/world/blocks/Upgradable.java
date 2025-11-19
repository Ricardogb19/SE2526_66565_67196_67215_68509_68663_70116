package mindustry.world.blocks;
public interface Upgradable {

    void upgrade();

    int upgradeCost();

    int getLevel();

    int maxLevel();
}
