package aterrizarv2.usuarios;


public class UsuarioPaga extends Usuario{
    private double cuotaPorMes;
    
    public UsuarioPaga(String nombre, String apellido, Integer dni,double cuotaPorMes) throws DniInvalidoException {
        super(nombre, apellido, dni);
        this.cuotaPorMes = cuotaPorMes;
    }
    
    @Override
    public boolean esVip(){
        return perfil.dineroGastado() >= 100000;
    }
    
}

