package org.tyoda.wurm.statuseffecttest;

import com.wurmonline.server.behaviours.Action;
import com.wurmonline.server.behaviours.ActionEntry;
import com.wurmonline.server.behaviours.Actions;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.items.Item;
import org.gotti.wurmunlimited.modsupport.actions.ActionPerformer;
import org.gotti.wurmunlimited.modsupport.actions.BehaviourProvider;
import org.gotti.wurmunlimited.modsupport.actions.ModAction;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class ShortEffectAction implements ModAction, ActionPerformer, BehaviourProvider {
    private final short actionId = (short) ModActions.getNextActionId();
    private final ActionEntry actionEntry;

    public ShortEffectAction() {
        this.actionEntry = ActionEntry.createEntry(this.actionId, "Short status effect", "shorting",
                new int[]{
                        Actions.ACTION_TYPE_IGNORERANGE,
                        Actions.ACTION_TYPE_NOMOVE
                }
        );
        ModActions.registerAction(this.actionEntry);
    }

    @Override
    public short getActionId() {
        return this.actionId;
    }

    @Override
    public List<ActionEntry> getBehavioursFor(Creature performer, Item object, int tilex, int tiley, boolean onSurface, int tile) {
        return Collections.singletonList(this.actionEntry);
    }

    @Override
    public List<ActionEntry> getBehavioursFor(Creature performer, int tilex, int tiley, boolean onSurface, int tile) {
        return Collections.singletonList(this.actionEntry);
    }

    @Override
    public boolean action(Action action, Creature performer, int tilex, int tiley, boolean onSurface, int tile, short num, float counter) {
        return doTheThing(performer);
    }

    @Override
    public boolean action(Action action, Creature performer, Item source, int tilex, int tiley, boolean onSurface, int heightOffset, int tile, short num, float counter) {
        return doTheThing(performer);
    }

    private boolean doTheThing(Creature performer) {
        // will appear correctly on client-side with new client-modlauncher (unless sent immediately after login)
        StatusEffectClass.sendStatusEffect(performer, 5);
        return true;
    }
}
