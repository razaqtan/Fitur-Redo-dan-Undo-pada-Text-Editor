
public class Stack {
    private String[] kata;
    private int top;
    
    public Stack(int size) {
        kata = new String[size];
        top = -1;
    }
    public void push (String input){
        if (top < kata.length - 1) {
            kata[++top] = input;
        }else {
            System.out.println("Stack penuh, tidak bisa menambah data lagi.");
        }
    }
    public String pop (){
        if (top >= 0) {
            return kata[top--];
        }
        return null;
    }
    public boolean isEmpty() {
        return (top == -1);
    }
    public void clear() {
        top = -1;
    }
}
