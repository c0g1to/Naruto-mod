package net.minecraft.client.audio;

import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BeeAngrySound extends BeeSound {
   public BeeAngrySound(BeeEntity p_i226058_1_) {
      super(p_i226058_1_, SoundEvents.ENTITY_BEE_LOOP_AGGRESSIVE, SoundCategory.NEUTRAL);
      this.repeatDelay = 0;
   }

   protected TickableSound getNextSound() {
      return new BeeFlightSound(this.beeInstance);
   }

   protected boolean shouldSwitchSound() {
      return !this.beeInstance.isAngry();
   }
}