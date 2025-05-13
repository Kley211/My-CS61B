package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>
{
    public class Node
    {
        public T item;
        public Node prev;
        public Node next;

        public Node(T item,Node prev,Node next)
        {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    private int size;
    private Node sentinel;

    public LinkedListDeque()
    {
        size = 0;
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item)
    {
        Node i = new Node(item,sentinel,sentinel.next);
        sentinel.next.prev = i;
        sentinel.next = i;
        size += 1;
    }

    @Override
    public void addLast(T item)
    {
        Node i = new Node(item,sentinel.prev,sentinel);
        sentinel.prev.next = i;
        sentinel.prev = i;
        size += 1;
    }


    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void printDeque()
    {
        System.out.print("(");
        for(int i = 0; i < this.size();i++)
        {
            System.out.print(get(i) + " ");
        }
        System.out.print(")");
        System.out.println();
    }

    @Override
    public T removeFirst()
    {
        if(size <= 0){return null;}

            Node Frist = new Node(sentinel.next.item, sentinel, sentinel.next.next);
            T removeitem = Frist.item;
            Frist.next.prev = sentinel;
            sentinel.next = Frist.next;

            Frist.next = Frist.prev = null;
            size -= 1;
            return removeitem;


    }

    @Override
    public T removeLast()
    {
        if(size <= 0) {return null;}
        Node Last = sentinel.prev;
        T removeitem = Last.item;
        sentinel.prev = Last.prev;
        Last.prev.next = sentinel;

        Last.prev = Last.next = null;
        size -= 1;
        return removeitem;
    }

    @Override
    public T get(int index)
    {
        if(index > size - 1 || index < 0)
        {
            return null;
        }
        Node iterate = sentinel.next;
        for(int i = 0; i < index;i++)
        {
            iterate = iterate.next;
        }
        return iterate.item;
    }

    public Iterator<T> iterator()
    {
        return new LinkedListDequeIterator();
    }


    private T getRecursive(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursive(start.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    //why LinkedListDequeIterator haven't <T>???
    public class LinkedListDequeIterator implements Iterator<T>
    {
        private int i;

        public LinkedListDequeIterator()
        {
            i = 0;
        }

        @Override
        public boolean hasNext()
        {
            return i < size;
        }

        @Override
        public T next()
        {
            T returnitem = get(i);
            i += 1;
            return returnitem;
        }


    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o){ return true;}

        if(o == null) {return false;}

        Deque<T> To = (Deque<T>) o;
        if(this.size() != To.size())
        {
            return false;
        }

        if(!(o instanceof Deque))
        {
            return  false;
        }

        for(int i =0 ; i < this.size();i++)
        {
            if(!get(i).equals(To.get(i)))
            {
                return false;
            }
        }

        return true;
    }

}