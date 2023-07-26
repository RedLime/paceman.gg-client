package gg.paceman.client.impl;

import com.google.common.collect.Lists;
import com.redlimerl.speedrunigt.api.OptionButtonFactory;
import com.redlimerl.speedrunigt.api.SpeedRunIGTApi;
import com.redlimerl.speedrunigt.option.SpeedRunOption;
import gg.paceman.client.PacemanGG;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.util.Collection;
import java.util.List;

public class SRIGTImpl implements SpeedRunIGTApi {

    @Override
    public Collection<OptionButtonFactory> createOptionButtons() {
        List<OptionButtonFactory> list = Lists.newArrayList();
        list.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, Text.method_30163("Open paceman.gg"),
                                (ButtonWidget button) -> Util.getOperatingSystem().open("https://paceman.gg/"))
                )
                .setCategory("paceman.gg")
        );

        list.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new LiteralText("Toggle paceman.gg Live").append(" : ").append(SpeedRunOption.getOption(PacemanGG.ENABLE_PACEMAN_GG_LIVE) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(PacemanGG.ENABLE_PACEMAN_GG_LIVE, !SpeedRunOption.getOption(PacemanGG.ENABLE_PACEMAN_GG_LIVE));
                                    button.setMessage(new LiteralText("Toggle paceman.gg Live").append(" : ").append(SpeedRunOption.getOption(PacemanGG.ENABLE_PACEMAN_GG_LIVE) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> "Send timer data to paceman.gg live.\nSo your live runs will be public on paceman.gg live.")
                .setCategory("paceman.gg")
        );
        return list;
    }
}
