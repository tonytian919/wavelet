import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String ram = "here";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) { //check if the path is /.
            return String.format("String: %d", ram);//then shows Number: num's value
        } else if (url.getPath().equals("/increment")) {// check if the path is /increment
            num += 1;//if so add 1 to num's value.
            return String.format("Number incremented!");//then shows Number incremented! on the screen.
        } else {//in the cases other than the above 2 cases.
            System.out.println("Path: " + url.getPath());//shows Path: the path we have.
            if (url.getPath().contains("/add")) {//check if the path contains /add
                String[] parameters = url.getQuery().split("=");//split the path at =.
                if (parameters[0].equals("s")) {//check if the first parameter is count
                    ram = ram + parameters[1];//add the number in the path to num.
                    return String.format("Number increased by %s! It's now %d", parameters[1], s);
                }
            }
            return "404 Not Found!";
        }
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
