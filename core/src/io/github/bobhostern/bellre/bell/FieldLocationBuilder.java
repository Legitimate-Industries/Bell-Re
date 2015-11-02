package io.github.bobhostern.bellre.bell;

/**
 * Entirely just for the hell of it. May or may not be kept.
 * Use for your laziness...
 */
public class FieldLocationBuilder {
    private FieldLocation.General gen = FieldLocation.General.LIMBO;
    private int spec = 0;

    public FieldLocationBuilder setGen(FieldLocation.General gen) {
        this.gen = gen;
        return this;
    }

    public FieldLocationBuilder setSpec(int spec) {
        this.spec = spec;
        return this;
    }

    public FieldLocation createFieldLocation() {
        return new FieldLocation(gen, spec);
    }
}