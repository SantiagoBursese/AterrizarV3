package controladorVistas;

import aterrizarv2.usuarios.Usuario;

public class controladorBienvenida {
    Usuario usuario;
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String nombreUsuario(){
        return usuario.getNombre();
    }
}
