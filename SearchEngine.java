import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String ram = "Start";
    String after = "";
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) { //check if the path is /.
            return String.format("String: "+ ram);//shows the value of ram
        } else {//in the other cases
            System.out.println("Path: " + url.getPath());//shows Path: the path we have.
            if (url.getPath().contains("/add")) {//check if the path contains /add
                String[] parameters = url.getQuery().split("=");//split the query at =.
                if (parameters[0].equals("s")) {//check if stuff at the beginning of query is s
                    ram = ram + " " + parameters[1];//add the String in the query to ram.
                    return String.format("String added:" + parameters[1] + "! It's now " + ram);
                }
            }else if (url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    String check[] = ram.split(" ");
                    for(int i = 1; i < check.length ; i++) {
                        if (check[i].contains(parameters[1])) {
                            after = after + check[i] + " ";
                        }
                    }
                    return String.format("In the String:" + after + "contains " + parameters[1]);
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
