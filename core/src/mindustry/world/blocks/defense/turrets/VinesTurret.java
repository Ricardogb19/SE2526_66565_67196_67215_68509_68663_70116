package mindustry.world.blocks.defense.turrets;

import mindustry.world.blocks.Upgradable;

public class VinesTurret extends BaseTurret {

    public VinesTurret(String name) {
        super(name);
    }

    public class VinesTurretBuild extends BaseTurretBuild implements Upgradable {

        /** This represents the turret's max level. */
        public final static int MAX_LEVEL = 15;
        /** This represents the turret's current level. */
        public int level = 1;

        @Override
        public int upgradeCost() {
            return 0;
        }

        @Override
        public void upgrade() {

        }

        @Override
        public void consumeMaterials(int cost) {

        }
    }
}