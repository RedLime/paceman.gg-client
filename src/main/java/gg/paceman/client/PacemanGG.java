package gg.paceman.client;

import com.redlimerl.speedrunigt.option.OptionArgument;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class PacemanGG {

    public static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer("paceman_gg").orElseThrow(RuntimeException::new);
    public static final OptionArgument<Boolean> ENABLE_PACEMAN_GG_LIVE = new OptionArgument<Boolean>(new Identifier(MOD_CONTAINER.getMetadata().getId(), "paceman_gg_live"), false) {
        @Override
        public Boolean valueFromString(String string) {
            return Objects.equals(string, "true");
        }

        @Override
        public String valueToString(Boolean value) {
            return String.valueOf(value);
        }
    };
}
