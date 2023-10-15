package org.tyoda.wurm.statuseffecttest;

import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.players.Player;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class StatusEffectClass {
    public static void sendStatusEffect(Player performer, int seconds) {
        // needed to avoid freezing the player class ...
        sendStatusEffect((Creature) performer, seconds);
    }

    public static void sendStatusEffect(Creature performer, int seconds) {
        try {
            // send status effect 5 secs
            StatusEffectTest.logger.info("Sending status effect to "+performer.getName()+" for "+seconds+" seconds");
            ByteBuffer bb = performer.getCommunicator().getConnection().getBuffer();
            bb.put((byte) -47);
            bb.put((byte) 0);
            bb.putLong(150);
            bb.putInt(150);
            bb.putInt(seconds);
            byte[] tempStringArr = "".getBytes(StandardCharsets.UTF_8);
            bb.put((byte) tempStringArr.length);
            bb.put(tempStringArr);
            performer.getCommunicator().getConnection().flush();
        } catch (IOException e) {
            StatusEffectTest.logger.log(Level.SEVERE, "Thing failed.", e);
        }
    }
}
