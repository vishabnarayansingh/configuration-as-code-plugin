package io.jenkins.plugins.casc.model;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */

public final class Scalar implements CNode, CharSequence {

    private String value;
    private Format format;
    private boolean raw;
    private Source source;

    public enum Format { STRING, MULTILINESTRING, BOOLEAN, NUMBER, FLOATING }

    public Scalar(String value, Source source) {
        this(value);
        this.source = source;
    }

    public Scalar(String value) {
        this.value = value;
        this.format = value.contains("\n") ? Format.MULTILINESTRING : Format.STRING;
        this.raw = false;
    }

    public Scalar(Enum instance) {
        this.value = instance.name();
        this.format = Format.STRING;
        this.raw = true;
    }

    public Scalar(Boolean instance) {
        this.value = String.valueOf(instance);
        this.format = Format.BOOLEAN;
        this.raw = true;
    }

    public Scalar(Number instance) {
        this.value = String.valueOf(instance);
        this.raw = true;
        if (instance instanceof Float || instance instanceof Double) {
            this.format = Format.FLOATING;
        } else {
            this.format = Format.NUMBER;
        }
    }

    @Override
    public Type getType() {
        return Type.SCALAR;
    }

    public Format getFormat() {
        return format;
    }

    public boolean isRaw() {
        return raw;
    }

    @Override
    public Scalar asScalar() {
        return this;
    }

    public String getValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }

    @Override
    public IntStream chars() {
        return value.chars();
    }

    @Override
    public IntStream codePoints() {
        return value.codePoints();
    }

    @Override
    public int length() {
        return value.length();
    }

    @Override
    public char charAt(int index) {
        return value.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return value.subSequence(start, end);
    }

    public Source getSource() {
        return source;
    }

    @Override
    public CNode clone() {
        return new Scalar(this);
    }

    private Scalar(Scalar it) {
        this.value = it.value;
        this.format = it.format;
        this.raw = it.raw;
        this.source = it.source;
    }
}
