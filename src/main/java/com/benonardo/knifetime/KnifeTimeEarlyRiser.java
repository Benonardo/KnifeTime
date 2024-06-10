package com.benonardo.knifetime;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class KnifeTimeEarlyRiser implements Runnable {
    public static final String THROWING_KNIFE = "KNIFE_TIME$THROWING_KNIFE";

    @Override
    public void run() {
        var useAction = FabricLoader.getInstance()
                .getMappingResolver()
                .mapClassName("intermediary", "net.minecraft.class_1839");
        ClassTinkerers.enumBuilder(useAction).addEnum(THROWING_KNIFE).build();

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            var armPose = FabricLoader.getInstance()
                    .getMappingResolver()
                    .mapClassName("intermediary", "net.minecraft.class_572$class_573");
            ClassTinkerers.enumBuilder(armPose, boolean.class).addEnum(THROWING_KNIFE, false).build();
        }
    }
}
