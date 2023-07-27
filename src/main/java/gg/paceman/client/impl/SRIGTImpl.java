package gg.paceman.client.impl;

import com.google.common.collect.Lists;
import com.redlimerl.speedrunigt.api.OptionButtonFactory;
import com.redlimerl.speedrunigt.api.SpeedRunIGTApi;
import net.minecraft.client.gui.widget.ButtonWidget;
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
        return list;
    }
}
