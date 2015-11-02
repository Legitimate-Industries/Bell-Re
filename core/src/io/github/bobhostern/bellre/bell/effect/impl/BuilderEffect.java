package io.github.bobhostern.bellre.bell.effect.impl;

import io.github.bobhostern.bellre.bell.effect.Effect;
import io.github.bobhostern.bellre.bell.effect.EffectType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Bobhostern on 10/16/2015.
 * <p/>
 * Used in cases like: "Target 1 card on the field. Draw 1 card, then destroy the targeted card."
 * <p/>
 * Holds an effect class, and allows you to instantiate it later, using a call to {@link BuilderEffect#build(Class[], Object...)}
 */
public class BuilderEffect<T extends Effect> extends Effect {
    private final Class<T> targetClass;
    private Object instance = null;

    public BuilderEffect(Class<T> c) {
        targetClass = c;
    }

    public void build(Class<?>[] paramtypes, Object... params) {
        try {
            Constructor cons = targetClass.getConstructor(paramtypes);
            instance = cons.newInstance(params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public T getInstance() {
        return (T) instance;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.BUILT;
    }

    @Override
    public boolean isChainable() {
        if (instance != null)
            return ((Effect) instance).isChainable();
        return super.isChainable();
    }

    @Override
    public Effect chainability(boolean b) {
        if (instance != null)
            return ((Effect) instance).chainability(b);
        return super.chainability(b);
    }
}
