package mindustry.world.blocks;
public interface Upgradable {

    void upgrade();

    int upgradeCost();

    void consumeMaterials(int cost);

    int getLevel();

    int maxLevel();
}
