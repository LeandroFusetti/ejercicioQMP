package ar.edu.utn.frba.dds.motorBusqueda;


import ar.edu.utn.frba.dds.prenda.Categoria;
import ar.edu.utn.frba.dds.prenda.Prenda;
import ar.edu.utn.frba.dds.sastre.Atuendo;
import ar.edu.utn.frba.dds.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public abstract class MotorSugerencia {
  private boolean estaActivado;

  public MotorSugerencia(){
    this.estaActivado = true;
  }

  public void desactivar(){
    this.estaActivado = false;
  }

  public boolean isEstaActivado() {
    return estaActivado;
  }

  public List<Prenda> filtrarPrendasPor(Categoria categoria, List<Prenda> prendas){
    return prendas.stream().filter(p->p.getCategoria() == categoria).toList();
  }


  public List<Atuendo> combinatoriaDeAtuendos(List<Prenda> prendas){
    List<Prenda> superiores = filtrarPrendasPor(Categoria.PARTE_SUPERIOR,prendas);
    List<Prenda> inferiores = filtrarPrendasPor(Categoria.PARTE_INFERIOR,prendas);
    List<Prenda> calzados = filtrarPrendasPor(Categoria.CALZADO,prendas);

    List<Atuendo> sugerencias = new ArrayList<>();
    for (Prenda superior : superiores) {
      for (Prenda inferior : inferiores) {
        for (Prenda calzado : calzados) {
          sugerencias.add(new Atuendo(superior, inferior, calzado));
        }
      }
    }
    return sugerencias;
  }

  public List<Atuendo> generarSugerencias(Usuario usuario){
    return this.combinatoriaDeAtuendos(this.aplicarFiltro(usuario));
  }

  public List<Prenda> aplicarFiltro(Usuario usuario){
    return this.isEstaActivado() ? this.filtrarSegunCriterio(usuario) : usuario.getPrendas();
  }
  abstract List<Prenda> filtrarSegunCriterio(Usuario usuario);
}
