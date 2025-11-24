import java.util.Scanner;

public class TextEditor {
    private String text = "";
    private Stack undoStack = new Stack(100);
    private Stack redoStack = new Stack(100);

    public void type(String newText) {
        undoStack.push(text);
        text += newText;
        redoStack.clear();
        displayText();
    }
    public void delete(int count) {
        if (text.isEmpty()){
            System.out.println("Tidak ada yang bisa dihapus sekarang");
            return;
        }
        if (count > text.length()) {
            count = text.length();
        }
            undoStack.push(text);
            text = text.substring(0, text.length() - count);
            redoStack.clear();
            displayText();   
        }
    public void undoStack() {
        String previousText = undoStack.pop();
        if (previousText != null) {
            redoStack.push(text);
            text = previousText;
        }else {
            System.out.println("Tidak ada yang bisa di undo.");
        }   
        displayText();
    }
    public void redoStack() {
        String nextText = redoStack.pop();
        if (nextText != null) {
            undoStack.push(text);
            text = nextText;
        }else {
            System.out.println("Tidak ada yang bisa di redo.");
        }
        displayText();
    }
    public void displayText() {
        System.out.println("Teks saat ini : " + text);
    }
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner sc = new Scanner(System.in);
        String perintah;
        
        System.out.println("===== Simple Text Editor =====");
        System.out.println("Perintah yang tersedia : tambah, hapus, undo, redo, exit");
        
        while (true) {
            System.out.println("Masukkan perintah : ");
            perintah = sc.nextLine().trim().toLowerCase();

            switch (perintah) {
                case "tambah":
                    System.out.print("Masukkan teks yang ingin ditambahkan : ");
                    String input = sc.nextLine();
                    editor.type(input);
                    break;
                case "hapus":
                    if (editor.text.isEmpty()){
                        System.out.println("Tidak ada yang bisa dihapus sekarang");
                    }else {
                    System.out.print("Masukkan jumlah karakter yang ingin dihapus : ");
                    int count = Integer.parseInt(sc.nextLine());
                    editor.delete(count);
                    }
                    break;
                case "undo":
                    editor.undoStack();
                    break;
                case "redo":
                    editor.redoStack();
                    break;
                case "exit":
                    System.out.println("Keluar dari editor.");
                    sc.close();
                    return;
                default:
                    System.out.println("Perintah tidak dikenali. Silakan coba lagi.");
            }
        }
    }
}
