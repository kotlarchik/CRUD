package ru.sapteh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) return;
        String fileName = "products.txt";
        List<Product> listProducts = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))){
            while (fileReader.ready()){
              Product products = getProducts(fileReader.readLine());
              listProducts.add(products);
            }
        }
        if ("-c".equals(args[0])){
            int id = 0;
                for (Product product: listProducts) {
                    if (product.getId() > id){
                        id = product.getId();
                    }
                }
                String name = args [args.length-3];
                if (name.length() > 30) name = name.substring(8,30);
                String price = args[args.length-2];
                if (price.length() > 8) price = price.substring(38,46);
                String quantity= args[args.length-1];
                if (quantity.length() > 4) quantity = quantity.substring(46,50);
                Product products = new Product(++id, name, price, quantity);
                listProducts.add(products);
        }
        try(FileWriter fw = new FileWriter(fileName)){
            for (Product product: listProducts) {
              fw.write(product.toString());
              fw.write("\n");
            }
        }
    }
    public static Product getProducts(String redLine) {
        String id = redLine.substring(0,8).trim();
        String name = redLine.substring(8,38).trim();
        String price = redLine.substring(38,46).trim();
        String quantity = redLine.substring(46,50).trim();

        return new Product(Integer.parseInt(id), name, price, quantity);
    }
}