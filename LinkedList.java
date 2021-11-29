/**
 * @author Leonardo Framba
 */
public class LinkedList<E> implements List<E> {

    //LinkedList node
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    Node<E> head;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
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

    @Override
    public Object[] toArray() {
        Object arr[] = new Object[size];
        Node<E> node = head;
        for (int i = 0; i < size; i++, node = node.next) {
            arr[i] = node.data;
        }
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object arr[] = new Object[size];
        Node<E> node = head;
        for (int i = 0; i < size; i++, node = node.next) {
            arr[i] = node.data;
        }
        return (T[]) arr;
    }

    @Override
    public void add(E e) {
        if (isEmpty()) {
            head = new Node<>(e);
        } else {
            Node<E> node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(e);
        }
        ++size;
    }

    @Override
    public boolean remove(Object o) {
        remove(indexOf(o));
        return true;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E data = node.data;
        node.data = element;
        return data;
    }

    @Override
    public void add(int index, E element) {
        //TODO validate index needs to account for the adding ot the end
        validateAddIndex(index);
        //Empty List 
        Node<E> node = new Node<>(element);
        if (isEmpty()) {
            //if the list is empty we set the added value as the head
            head = node;
        } else {
            if (index == 0) {
                node.next = head;
                head = node;
            } else {
                Node<E> prev = head;
                for (int i = 0; i < index - 1; i++) {
                    prev = prev.next;
                }
                node.next = prev.next;
                prev.next = node;
            }
        }
        ++size;
    }

    @Override
    public E remove(int index) {
        validateRemoveIndex(index);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        //Store return data
        E data = node.data;

        //Pointers
        //Head case
        if (index == 0) {
            head = head.next;
        } else {
            Node<E> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            prev.next = node.next;
        }

        --size;
        return data;

    }

    @Override
    public int indexOf(Object o) {
        Node<E> node = head;
        for (int i = 0; node != null; i++, node = node.next) {
            if (o == node.data) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> node = head;
        int index = -1;
        for (int i = 0; node != null; i++, node = node.next) {
            if (o == node.data) index = i;
        }
        return index;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        validateRemoveIndex(fromIndex);
        validateRemoveIndex(toIndex);
        List<E> subList = new LinkedList<>();
        Node<E> node = head;

        for (int i = 0; i < fromIndex; i++) {
            node = node.next;
        }

        for (int i = 0; i < toIndex - fromIndex + 1; i++) {
            subList.add(node.data);
            node = node.next;
        }
        return subList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<E> node = head;
        while (node != null) {
            result.append(node.data).append("->");
            node = node.next;
        }
        result.append("NULL");
        return result.toString();
    }
}
