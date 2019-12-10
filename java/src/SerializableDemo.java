import java.io.*;

public class SerializableDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("tempFile"));
        objectOutputStream.writeObject(Singleton.getInstance());

        File file = new File("tempFile");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("tempFile"));
        Singleton newInstance = (Singleton) objectInputStream.readObject();

        System.out.println(newInstance == Singleton.getInstance());

        if(objectInputStream != null){
            objectInputStream.close();
        }
        if(objectOutputStream != null){
            objectOutputStream.close();
        }

    }

}
