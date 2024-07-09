/**Klasa drzewa string */
public class StringTree{
    /**Klasa wezla string */
    class Node{
        String key;
        Node left;
        Node right;
    }
    Node root;
    String res="";
    /**Wstawienie elementu do drzewa string*/
    Node insert(String x,Node n){
        if(n == null){
            n = new Node();
            n.key = x;
            n.left = null;
            n.right = null;
        }
        else if(x.compareTo(n.key)<0){
            n.left = insert(x,n.left);
        }
        else{
            n.right = insert(x,n.right);
        }
        return n;
    }
    /**Rysowanie drzewa string*/
    void draw(Node n) {
        if(n == null){return;}
        draw(n.left);
        res+=(n.key+" ");
        draw(n.right);
    }
    /**Wyszukiwanie w drzewie string*/
    Node search(String x,Node n){
        if(n==null || x.compareTo(n.key)==0){
            return n;
        }
        if(x.compareTo(n.key)<0){
            return search(x,n.left);
        }
        else{
            return search(x,n.right);
        }
    }
    /**Usuniecie elementu z drzewa string*/
    Node remove(String x,Node n){
        if (n == null){return n;}
        if (x.compareTo(n.key)<0) {
            n.left = remove(x,n.left);
            return n;
        }
        else if (x.compareTo(n.key)>0) {
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