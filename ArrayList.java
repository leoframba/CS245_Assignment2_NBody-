import java.util.*;

public class ArrayList<E> implements List<E> {

    private int size; //Number of elements in the array
    private E[] arr;

    public ArrayList() {
        size = 0;
        arr = (E[]) new Object[10];
    }

    /**
     * Check for a valid index in remove/set/get functions.
     *
     * @param index - index to validate
     */
    private void validateRemoveIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Check for a valid index in add functions.
     *
     * @param index - index to validate
     */
    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Grows the base array by a factor of x2 when it runs out of size
     */
    private void growArray() {
        E[] newArr = (E[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(arr, size);
    }

    @Override
    public void add(E e) {
        if (size == arr.length) growArray();
        arr[size++] = e;
    }

    @Override
    public boolean remove(Object e) {
        int index = indexOf(e);
        if (index >= 0) {
            remove(index);
            return true;
        } else return false;
    }

    @Override
    public void clear() {
        arr = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public E get(int index) {
        validateRemoveIndex(index);
        return arr[index];
    }

    @Override
    public E set(int index, E element) {
        validateRemoveIndex(index);
        E data = arr[index];
        arr[index] = element;
        return data;
    }

    @Override
    public void add(int index, E element) {
        if (size == arr.length) growArray();
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = element;
        ++size;
    }

    @Override
    public E remove(int index) {
        validateRemoveIndex(index);
        E data = arr[index];
        if (size - index >= 0) System.arraycopy(arr, index + 1, arr, index, size - index);
        --size;
        return data;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(arr[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object e) {
        int lastIndex = -1;
        for (int i = 0; i < size; i++) {
            if (e.equals(arr[i])) lastIndex = i;
        }
        return lastIndex;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> subList = new ArrayList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            subList.add(arr[i]);
        }
        return subList;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
}
