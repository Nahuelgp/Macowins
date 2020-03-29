class Macowins {
    List<Venta> ventas = new ArrayList<>();

    void registrarVenta(Venta unaVenta) {
        ventas.add(unaVenta);
    }

    double gananciasDelDia(Date unaFecha) {
        return ventas.stream.filter(venta -> venta.getFecha() == unaFecha).count();
    }
}

class Venta{
    List<Prenda, Integer> prendas = new ArrayList<>();
    Date fecha;
    double ganancias;
    MedioDePago medioDePago;

    Venta(List<Prenda, Integer> prendas, Date fecha, MedioDePago medioDePago) {
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
    double precioBase;
    Prenda(double precioBase, Estado estado) {
        this.estado = estado;
        this.precioBase = precioBase;
    }
    
    double precio() {
        return estado.precio(precioBase);
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