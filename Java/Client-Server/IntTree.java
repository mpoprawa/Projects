/**Klasa drzewa int */
public class IntTree{
    /**Klasa wezla int */
    class Node{
        int key;
        Node left;
        Node right;
    }
    Node root;
    String res="";
    /**Wstawienie elementu do drzewa int*/
    Node insert(int x,Node n){
        if(n == null){
            n = new Node();
            n.key = x;
            n.left = null;
            n.right = null;
        }
        else if(x < n.key){
            n.left = insert(x,n.left);
        }
        else{
            n.right = insert(x,n.right);
        }
        return n;
    }
    /**Rysowanie drzewa int*/
    void draw(Node n) {
        if(n == null){return;}
        draw(n.left);
        res+=(n.key+" ");
        draw(n.right);
    }
    /**Wyszukiwanie w drzewie int*/
    Node search(int x,Node n){
        if(n==null || x==n.key){
            return n;
        }
        if(x<n.key){
            return search(x,n.left);
        }
        else{
            return search(x,n.right);
        }
    }
    /**Usuniecie elementu z drzewa int*/
    Node remove(int x,Node n){
        if (n == null){return n;}
        if (n.key > x) {
            n.left = remove(x,n.left);
            return n;
        }
        else if (n.key < x) {
            n.right = remove(x,n.right);
            return n;
        }
        if (n.left == null) {
            Node temp = n.right;
            return temp;
        }
        else if (n.right == null) {
            Node temp = n.left;
            return temp;
        }
        else{
            Node parent = n;
            Node child = n.right;
            while (child.left != null) {
                parent = child;
                child = child.left;
            }
            if (parent != n){parent.left = child.right;}
            else{parent.right = child.right;}
            n.key = child.key;
            return n;
        }
    }
};