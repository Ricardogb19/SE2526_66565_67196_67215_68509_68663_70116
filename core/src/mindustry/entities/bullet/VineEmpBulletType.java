package mindustry.entities.bullet;

import arc.Events;
import arc.util.Tmp;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.EventType;
import mindustry.gen.*;

public class VineEmpBulletType extends BasicBulletType{


    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        boolean wasDead = entity instanceof Unit u && u.dead;

        if(entity instanceof Healthc h){
            float damage = b.damage;
            float shield = entity instanceof Shieldc s ? Math.max(s.shield(), 0f) : 0f;
            if(maxDamageFraction > 0){
                float cap = h.maxHealth() * maxDamageFraction + shield;
                damage = Math.min(damage, cap);
                //cap health to effective health for handlePierce to handle it properly
                health = Math.min(health, cap);
            }else{
                health += shield;
            }
            if(lifesteal > 0f && b.owner instanceof Healthc o){
                float result = Math.max(Math.min(h.health(), damage), 0);
                o.heal(result * lifesteal);
            }
            if(pierceArmor){
                h.damagePierce(damage);
            }else{
                h.damage(damage);
            }
        }

        if(entity instanceof Unit unit){
            Tmp.v3.set(unit).sub(b).nor().scl(knockback * 80f);
            if(impact) Tmp.v3.setAngle(b.rotation() + (knockback < 0 ? 180f : 0f));
            unit.impulse(Tmp.v3);
            unit.apply(status, statusDuration);

            Events.fire(bulletDamageEvent.set(unit, b));
        }

        if(!wasDead && entity instanceof Unit unit && unit.dead){
            Events.fire(new EventType.UnitBulletDestroyEvent(unit, b));
        }
        if(entity instanceof Unit unit){
            if(unit.isFlying()){
                unit.apply(StatusEffects.grounded, 500f);
                unit.apply(StatusEffects.disarmed, 500f);
                unit.elevation = 0.0f;
            }
        }
        handlePierce(b, health, entity.x(), entity.y());
    }
}
