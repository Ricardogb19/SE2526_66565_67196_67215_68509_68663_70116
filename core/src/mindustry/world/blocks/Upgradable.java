package mindustry.world.blocks;
public interface Upgradable {

    void upgrade();

    void upgradeCost();

    int getLevel();

    int maxLevel();
}
