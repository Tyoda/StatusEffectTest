package org.tyoda.wurm.statuseffecttest;

import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.players.Player;
import javassist.ClassPool;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.*;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusEffectTest implements WurmServerMod, ServerStartedListener, PreInitable, PlayerLoginListener {
    public static final Logger logger = Logger.getLogger(StatusEffectTest.class.getName());

    @Override
    public void preInit() {
        ModActions.init();
    }

    @Override
    public void onServerStarted() {
        ModActions.registerAction(new ShortEffectAction());
        ModActions.registerAction(new LongEffectAction());
    }

    @Override
    public void onPlayerLogin(Player player) {
        // will not appear on client-side, unless the client is modified
        // to reapply modded statuseffects after modpacks are loaded
        StatusEffectClass.sendStatusEffect(player, 60);
    }
}
