class Negocio {
    List<Venta> ventas = new ArrayList<>();

    void registrarVenta(Venta unaVenta) {
        ventas.add(unaVenta);
    }

    double gananciasDelDia(Date unaFecha) {
        return ventas.stream.filter(venta -> venta.getFecha() == unaFecha).getGanancias();
    }
}

class Venta{
    List<Prenda> prendas = new ArrayList<>();
    Date fecha;
    double ganancias;
    MedioDePago medioDePago;

    Venta(List<Prenda> prendas, Date fecha, MedioDePago medioDePago) {
        this.prendas = prendas;
        this.fecha = fecha;
        this.medioDePago = medioDePago;
    }

    Date getFecha() {
        return fecha;
    }

    double costoPrendas() {
        return prendas.stream.mapToInt(prenda -> prenda.precio()).sum();
    }

    double getGanancias() {
        return medioDePago.costoTotal(this.costoPrendas());;
    }
}

interface MedioDePago {
    double costoTotal(double costo);
}

class Tarjeta implements MedioDePago {
    int cantidadDeCuotas;
    double coeficienteFijo;
    Tarjeta(int cantidadDeCuotas, double coeficienteFijo) {
        this.cantidadDeCuotas = cantidadDeCuotas;
        this.coeficienteFijo = coeficienteFijo;
    }

    public double costoTotal(double costo) {
        return costo + cantidadDeCuotas * coeficienteFijo + 0.1 * costo; 
    }
}

class Efectivo implements MedioDePago{
    public double costoTotal(double costo) {
        return costo;
    }
}

class Prenda {
    Estado estado;
    TipoDePrenda tipo;
    double precioBase;
    Prenda(double precioBase, TipoDePrenda tipo, Estado estado) {
        this.estado = estado;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }
    
    double precio() {
        return estado.precio(precioBase);
    }
    
    String tipoDePrenda() {
        return tipo.tipo();
    }
}

interface TipoDePrenda {
    String tipo();
}

class Saco implements TipoDePrenda{
    public String tipo() {
        return "Saco";
    }
}

class Pantalon implements TipoDePrenda{
    public String tipo() {
        return "Pantalón";
    }
}

class Camisa implements TipoDePrenda{
    public String tipo() {
        return "Camisa";
    }
}

interface Estado {
    double precio(double precioBase);
}

class Nueva implements Estado {
    public double precio(double precioBase) {
        return precioBase;
    }
}

class Promocion implements Estado {
    double valor;
    Promocion(double valor) {
        this.valor = valor;
    }
    public double precio(double precioBase) {
        return precioBase - valor;
    }
}

class Liquidacion implements Estado {
    public double precio(double precioBase) {
        return precioBase * 0.5;
    }
}

/*


REQUERIMIENTOS:

1) Se debe poder obtener el precio de venta de una prenda y su tipo.

2) Se debe tener que registrar las ventas para poder obtener la ganancia total de las ventas en una fecha en específico.

ACLARACIONES:

- No se mucho de java asi que hice todo basandome en todo lo que se encuentra en la guia de mumuki.

- El recargo en vez de hacerlo por el precio de cada prenda lo hice por el precio de todas sacando factor comun el 0.1.


*/