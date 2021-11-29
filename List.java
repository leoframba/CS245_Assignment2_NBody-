public interface List<E> {
    public int size();

    public boolean isEmpty();

    public boolean contains(Object e);

    public Object[] toArray();

    public <T> T[] toArray(T[] a);

    public void add(E e);

    public void add(int index, E element);

    public boolean remove(Object e);

    public E remove(int index);

    public void clear();

    public E get(int index);

    public E set(int index, E e);

    public int indexOf(Object e);

    public int lastIndexOf(Object e);

    public List<E> subList(int fromIndex, int toIndex);

}
