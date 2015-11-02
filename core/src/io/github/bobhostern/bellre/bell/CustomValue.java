package io.github.bobhostern.bellre.bell;

public interface CustomValue<T> extends Cloneable {
    public T getValue();

    /**
     * Returns a string to specify the implementation class of CustomValue
     *
     * @return String type name
     */
//	public String getType();
    CustomValue<T> clone();
}
