package aterrizarv2.usuarios;

public class UsuarioNoPaga extends Usuario{

    public UsuarioNoPaga(String nombre, String apellido, Integer dni) throws DniInvalidoException {
        super(nombre, apellido, dni);
    }

    @Override
    public boolean esVip() {
        return false;
    }
   
}
    

