import java.io.*;
import java.net.*;
/**Watek serwera */
public class TreeThread extends Thread {
    private Socket socket;
    public TreeThread(Socket socket) {
        this.socket = socket;
    }
    /**Glowna funkcja watku */
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);
            String command;
            command=in.readLine();
            //Drzewo int
            if(command.equals("int")){
                IntTree tree=new IntTree();
                System.out.println("creating int tree");
                out.println("creating int tree");
                while (true){
                    command = in.readLine();
                    if(command.equals("exit")){
                        out.println("client closed");
                        System.out.println("client disconnected");
                        break;
                    }
                    else if(command.equals("draw")){
                        tree.draw(tree.root);
                        out.println(tree.res);
                        tree.res="";
                    }
                    else if(command.length()>=6){
                        if((command.substring(0,6)).equals("search")){
                            if(tree.search(Integer.parseInt(command.substring(7,command.length())),tree.root)==null){
                                out.println("element not found");
                            }
                            else{
                                out.println("element found");
                            }
                        }
                        else if(command.substring(0,6).equals("insert")){
                            tree.root=tree.insert(Integer.parseInt(command.substring(7,command.length())),tree.root);
                            tree.draw(tree.root);
                            out.println(tree.res);
                            tree.res="";
                        }
                        else if((command.substring(0,6)).equals("delete")){
                            tree.root=tree.remove(Integer.parseInt(command.substring(7,command.length())),tree.root);
                            tree.draw(tree.root);
                            out.println(tree.res);
                            tree.res="";
                        }
                        else{out.println("invalid command");}
                    }
                    else{out.println("invalid command");}
                }
            }
            //Drzewo double
            else if(command.equals("double")){
                DoubleTree tree=new DoubleTree();
                System.out.println("creating double tree");
                out.println("creating double tree");
                while (true){
                    command = in.readLine();
                    if(command.equals("exit")){
                        out.println("client closed");
                        System.out.println("client disconnected");
                        break;
                    }
                    else if(command.equals("draw")){
                        tree.draw(tree.root);
                        out.println(tree.res);
                        tree.res="";
                    }
                    else if(command.length()>=6){
                        if((command.substring(0,6)).equals("search")){
                            if(tree.search(Double.parseDouble(command.substring(7,command.length())),tree.root)==null){
                                out.println("element not found");
                            }
                            else{
                                out.println("element found");
                            }
                        }
                        else if(command.substring(0,6).equals("insert")){
                            tree.root=tree.insert(Double.parseDouble(command.substring(7,command.length())),tree.root);
                            tree.draw(tree.root);
                            out.println(tree.res);
                            tree.res="";
                        }
                        else if((command.substring(0,6)).equals("delete")){
                            tree.root=tree.remove(Double.parseDouble(command.substring(7,command.length())),tree.root);
                            tree.draw(tree.root);
                            out.println(tree.res);
                            tree.res="";
                        }
                        else{out.println("invalid command");}
                    }
                    else{out.println("invalid command");}
                }
            }
            //Drzewo string
            else if(command.equals("string")){
                StringTree tree=new StringTree();
                System.out.println("creating string tree");
                out.println("creating string tree");
                while (true){
                    command = in.readLine();
                    if(command.equals("exit")){
                        out.println("client closed");
                        System.out.println("client disconnected");
                        break;
                    }
                    else if(command.equals("draw")){
                        tree.draw(tree.root);
                        out.println(tree.res);
                        tree.res="";
                    }
                    else if(command.length()>=6){
                        if((command.substring(0,6)).equals("search")){
                            if(tree.search(command.substring(7,command.length()),tree.root)==null){
                                out.println("element not found");
                            }
                            else{
                                out.println("element found");
                            }
                        }
                        else if(command.substring(0,6).equals("insert")){
                            tree.root=tree.insert(command.substring(7,command.length()),tree.root);
                            tree.draw(tree.root);
                            out.println(tree.res);
                            tree.res="";
                        }
                        else if((command.substring(0,6)).equals("delete")){
                            tree.root=tree.remove(command.substring(7,command.length()),tree.root);
                            tree.draw(tree.root);
                            out.println(tree.res);
                            tree.res="";
                        }
                        else{out.println("invalid command");}
                    }
                    else{out.println("invalid command");}
                }
            }
            else if(command.equals("exit")){
                out.println("client closed");
                System.out.println("client disconnected");
            }
            else{out.println("Invalid data type");}
            socket.close();
        }
        catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}