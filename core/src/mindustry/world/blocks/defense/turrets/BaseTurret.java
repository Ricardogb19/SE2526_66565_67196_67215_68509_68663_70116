package mindustry.world.blocks.defense.turrets;

import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.Gamemode;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class BaseTurret extends Block{
    public float range = 80f;
    public float placeOverlapMargin = 8 * 7f;
    public float rotateSpeed = 5;
    public float fogRadiusMultiplier = 1f;
    public boolean disableOverlapCheck = false;

    /** Effect displayed when coolant is used. */
    public Effect coolEffect = Fx.fuelburn;
    /** How much reload is lowered by for each unit of liquid of heat capacity. */
    public float coolantMultiplier = 5f;
    /** If not null, this consumer will be used for coolant. */
    public @Nullable ConsumeLiquidBase coolant;

    public BaseTurret(String name){
        super(name);

        update = true;
        solid = true;
        outlineIcon = true;
        attacks = true;
        priority = TargetPriority.turret;
        group = BlockGroup.turrets;
        flags = EnumSet.of(BlockFlag.turret);
    }

    @Override
    public void init(){
        if(coolant == null){
            coolant = findConsumer(c -> c instanceof ConsumeCoolant);
        }

        //just makes things a little more convenient
        if(coolant != null){
            //TODO coolant fix
            coolant.update = false;
            coolant.booster = true;
            coolant.optional = true;

            //json parsing does not add to consumes
            if(!hasConsumer(coolant)) consume(coolant);
        }

        if(!disableOverlapCheck){
            placeOverlapRange = Math.max(placeOverlapRange, range + placeOverlapMargin);
        }
        fogRadius = Math.max(Mathf.round(range / tilesize * fogRadiusMultiplier), fogRadius);
        super.init();
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.placing);

        if(fogRadiusMultiplier < 0.99f && state.rules.fog){
            Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range * fogRadiusMultiplier, Pal.lightishGray);
        }
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.shootRange, range / tilesize, StatUnit.blocks);
    }

    public class BaseTurretBuild extends Building implements Ranged, RotBlock, Upgradable{
        public float rotation = 90;
        /** This represents the turret's max level. */
        public final static int MAX_LEVEL = 5;
        /** This represents the turret's current level. */
        public int level = 1;
        /** This represents the turret's max ammunition. */
        private int maxAmmunition = 30;


        public int upgradeCost() {
            return 2 + 10 * level / MAX_LEVEL;
        }


        public void upgrade() {
            if (level < MAX_LEVEL) {
                switch (level) {
                    case 1 -> maxAmmunition+=5;
                    case 2 -> maxAmmunition+=4;
                    case 3 -> maxAmmunition+=3;
                    case 4 -> maxAmmunition+=2;
                }
                int materialsNeededForUpgrade = upgradeCost();
                if (hasEnoughMaterials(materialsNeededForUpgrade)) {
                    level++;
                    consumeMaterials(materialsNeededForUpgrade);
                    ui.showInfoFade("Upgraded this turret to level " + level + ".", 4);
                    ui.showInfoPopup("Increased max ammo to " + maxAmmunition + ".", 3, Align.top, 30, 0, 0, 0);
                }
                else
                    ui.showInfoFade("Insufficient materials.");
            }
            else
                ui.showInfoFade("Already on max level.", 3);
        }

        /**
         * This method checks weather or not the player has enough copper to upgrade this turret.
         * @param materialsNeeded - The amount of copper needed for this upgrade.
         * @return True if the player has enough copper, False otherwise.
         */
        private boolean hasEnoughMaterials(int materialsNeeded) {
            CoreBlock.CoreBuild core = player.core();
            int coreCopper = core.items.get(Items.copper);
            return coreCopper >= materialsNeeded;
        }


        public void consumeMaterials(int cost) {
            if (state.rules.mode() != Gamemode.sandbox) {
                CoreBlock.CoreBuild core = player.core();
                if (core != null && core.items.has(Items.copper, cost))
                    core.items.remove(Items.copper, cost);
            }
        }

        @Override
        public float range(){
            return range;
        }

        @Override
        public float buildRotation(){
            return rotation;
        }

        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range(), team.color);
        }

        public float estimateDps(){
            return 0f;
        }
    }
}
