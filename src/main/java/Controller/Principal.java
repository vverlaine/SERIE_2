package Controller;

import Model.Producto;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.MathObservable;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    private static List<Producto> list = new ArrayList<>();

    public static void main(String[] args) {

        list.add(new Producto(200));
        list.add(new Producto(100));
        list.add(new Producto(300));
        list.add(new Producto(450));
        list.add(new Producto(2));
        list.add(new Producto(54));
        list.add(new Producto(20));

        Observable obs = Observable.from(list)
                .map((resutl) -> {
                    Producto p = resutl;
                    return p.getPrecio();
                });

        // PROMEDIO DE TODOS LOS NUMEROS
        MathObservable.averageInteger(obs).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("EL PROMEDIO ES: " + o);
            }
        });

        // FILTRANDO MAYOR QUE 20
        Observable<Producto> obs2 = Observable.from(list)
                .filter(new Func1<Producto, Boolean>() {
                    @Override
                    public Boolean call(Producto producto) {
                        return producto.getPrecio() > 20;
                    }
                });

        obs2.subscribe(new Action1<Producto>() {
            @Override
            public void call(Producto producto) {
                System.out.println("MAYOR QUE 20: " + producto.getPrecio());
            }
        });

        // FILTRANDO MAYOR QUE 50
        Observable<Producto> obs3 = Observable.from(list)
                .filter(new Func1<Producto, Boolean>() {
                    @Override
                    public Boolean call(Producto producto) {
                        return producto.getPrecio() > 50;
                    }
                });

        obs3.subscribe(new Action1<Producto>() {
            @Override
            public void call(Producto producto) {
                System.out.println("MAYOR QUE 50: " + producto.getPrecio());
            }
        });
    }

}
