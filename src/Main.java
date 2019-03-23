public class Main {
    public static void main(String[] args) {
        MyLinkedList<Character> linkedList = new MyLinkedList<>();
        linkedList.addFirst('a');
        linkedList.addFirst('b');
        linkedList.addLast('c');
        linkedList.add(2, 'd');
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.contains('x'));
        System.out.println(linkedList.indexOf('c'));
        linkedList.set(3, 'z');
        System.out.println(linkedList);
        linkedList.removeFirst();
        linkedList.addFirst('x');
        linkedList.removeLast();
        linkedList.addLast('y');
        linkedList.remove('x');
        System.out.println(linkedList.size());
        System.out.println(linkedList);
        for (Character c : linkedList) {
            System.out.print(c);
        }
    }
}
