package io.github.bobhostern.bellre.bell.effect.impl;

import io.github.bobhostern.bellre.bell.GameCard;
import io.github.bobhostern.bellre.bell.effect.Effect;
import io.github.bobhostern.bellre.bell.effect.EffectType;

import java.util.EnumSet;

/**
 * Created by Bobhostern on 10/16/2015.
 */
public class SummonEffect extends Effect {
    public static final EnumSet<SummonType> NORMAL_SUMMON = EnumSet.of(SummonType.NORMAL);
    public static final EnumSet<SummonType> SET = EnumSet.of(SummonType.SET);
    public static final EnumSet<SummonType> XYZ_SUMMON = EnumSet.of(SummonType.XYZ, SummonType.SPECIAL_SUMMON);
    public static final EnumSet<SummonType> RITUAL_SUMMON = EnumSet.of(SummonType.RITUAL, SummonType.SPECIAL_SUMMON);
    public static final EnumSet<SummonType> SYNCHRO_SUMMON = EnumSet.of(SummonType.SYNCHRO, SummonType.SPECIAL_SUMMON);
    public static final EnumSet<SummonType> FUSION_SUMMON = EnumSet.of(SummonType.FUSION, SummonType.SPECIAL_SUMMON);

    private final GameCard[] targets;
    private final EnumSet<SummonType> summonType;

    public SummonEffect(GameCard[] cs, EnumSet<SummonType> s) {
        targets = cs;
        summonType = s;
    }

    public GameCard[] getTargets() {
        return targets;
    }

    public EnumSet<SummonType> getSummonType() {
        return summonType;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.SUMMON;
    }
}
