package mindustry.world.blocks;

/**
 * This interfaces serves as a template for any building to implement making it an upgradable building,
 * i.e. allowing the player to upgrade any type of building, each building can have its specific upgrades and costs.
 */
public interface Upgradable {

    /**
     * This method calculates the building's cost of upgrade to the next level.
     * @return an integer value representing the amount of copper needed to upgrade the building to the next level.
     */
    int upgradeCost();

    /**
     * This method upgrades the building to the next level iff the player's core has the necessary materials
     * and the turret's level is at least 1 below its max.
     */
    void upgrade();

    /**
     * This method consumes the materials needed for the upgrade on this building made by the player, removing them from his core.
     * @param cost - The amount of copper needed for the upgrade.
     */
    void consumeMaterials(int cost);
}
