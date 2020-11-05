package condicionescompetencias;

public class Interrupcion{
    private boolean interrupcion;
    
    public Interrupcion(){
        interrupcion = true;
    }

    public boolean isInter() {
        return interrupcion;
    }

    public void setInter(boolean bool) {
        this.interrupcion = bool;
    }
}