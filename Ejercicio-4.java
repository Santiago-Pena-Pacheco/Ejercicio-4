//Clase administrador

class Administrador {
    private String nombre;
    private int documento;
    private Producto producto;

    public Administrador(String nombre, int documento) {
        this.nombre = nombre;
        this.documento = documento;
    }
    
    public void cambiarPrecio(Producto producto, double precio){
        producto.setPrecio(precio);
    }
    
    public void cambiarStock(Producto producto, int stock){
        producto.setStock(stock);
    }

    public void borrarVenta(Venta venta){
        venta.borrarVenta();
    }
}
//Clase cliente

class Cliente {
    private String nombre;
    private int documento;

    public Cliente(String nombre, int documento) {
        this.nombre = nombre;
        this.documento = documento;
    }
    public String getNombre() {
        return nombre;
    }
}

//clase compra

class Compra {
    private Producto producto;
    private int cantidad;
    private double precio;
    
    public Compra( Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = producto.getPrecio();
    }

    public double calcularTotal(){
        return cantidad * precio;
    }

    public void mostrarInformacion(){
        System.out.println("Producto: "+ producto.getNombre());
        System.out.println("Cantidad: "+ cantidad);
        System.out.println("Precio: "+ precio);
        System.out.println("Total: "+ calcularTotal());
    }
}

//Clase producto

class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int codigo, String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setPrecio(double precio) {
        if(precio > 0){
            this.precio = precio;
        }else{
             System.out.println("El precio para el producto "+ '"'+nombre+'"' + " debe ser mayor a cero");
        }
    }

    public void setStock(int stock) {
        if(stock >= 0){
            this.stock = stock;
        }else{
            System.out.println("La cantidad para el producto " +'"'+nombre+'"' + " no puede ser negativa");
        }
    }  
}

// clase venta
import java.util.ArrayList;

class Venta {
    private Cliente cliente;
    private ArrayList<Compra> compra;
    private int cantidad;
    private double total;

    public Venta(Cliente cliente) {
        this.cliente = cliente;
        this.compra = new ArrayList<>();
    }

    public void agregarCompra(Producto producto, int cantidad){
        Compra compra = new Compra(producto, cantidad);
        this.compra.add(compra);
    }
    
    public double calcularTotal(){
        double total = 0;
        for(Compra compra : compra){
            total += compra.calcularTotal();
        }
        return total;
    }
    
    public void mostrarDetalle(){
        if (cliente == null) {
            System.out.println("Venta eliminada");
            return;
        }else{
            System.out.println("Cliente: "+ cliente.getNombre());
            for (Compra compra: compra) {
                compra.mostrarInformacion();
            }
            System.out.println("Venta total: "+ calcularTotal());
        }
    }
    public void borrarVenta(){
        this.compra.clear();
        this.total = 0;
        this.cantidad = 0;
        this.cliente = null;
    }
    
}


//main

class tienda {
    public static void main(String[] args) {
        
        Producto pro1 = new Producto (1,"Entre canibales",50000.0, 4);
        Producto pro2 = new Producto (2,"Iris",60000.0, 6);
        Producto pro3 = new Producto (3,"Please don't let it go",70000.0, 5);
       
        
        Cliente cli1= new Cliente ("Juan", 12345);
        Cliente cli2 = new Cliente ("Maria", 65432);
        Administrador admin = new Administrador ("Pedro", 123445);
        
        Venta venta = new Venta(cli1);
        Venta venta2 = new Venta(cli2);
        Venta venta3 = new Venta(cli1);
        
        venta.agregarCompra(pro1, 2);
        venta.agregarCompra(pro2, 1);
        venta.mostrarDetalle();
        System.out.println();
        
        venta2.agregarCompra(pro3, 4);
        venta2.mostrarDetalle();
        System.out.println();
        
        venta3.agregarCompra(pro2, 1);
        venta3.mostrarDetalle();
        System.out.println();

        admin.cambiarPrecio(pro1, 10000.0);
        admin.cambiarStock(pro3, -5);
        admin.cambiarPrecio(pro3, 0);
        System.out.println();

        admin.borrarVenta(venta);
        venta.mostrarDetalle();
    }
}
