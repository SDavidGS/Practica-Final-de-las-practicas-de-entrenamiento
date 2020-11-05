package condicionescompetencias;
import java.util.ArrayList;

public class RCompartido {
    private String rc;
    private Cerradura c;
    private ArrayList<Interrupcion> Ainterrupciones;
    
    private boolean H[] = {false, false, false, false};
    int turno2;
    int turno1;
    int con;
    
    RCompartido(){
        rc="";
        c= new Cerradura();
        Ainterrupciones = new ArrayList<Interrupcion>();
        for(int i=0; i<4; i++){
            Ainterrupciones.add(new Interrupcion());
        }
        turno1 = 1;
        turno2 = 0;
        con=0;
    }
    //CondicionesCompetencias
    public String getDatoCompartido() {
        return rc;
    }
    public void setDatoCompartido(String datoCompartido) {
        this.rc = datoCompartido;
    }
    //VCerradura
    public void bloquear_VC(){
        c.setCerradura(true);
    }
    public void desbloquear_VC(){
        c.setCerradura(false);
    }
    public String getrc_VC() {
        return rc;
    }
    public boolean isEntra_VC(){
        if(c.isCerradura() == false)
            return true;
        return false;
    }
    public void setrc_VC(String rc) {
        this.rc = rc;
    }
    
    //Desactivacion de Interrupciones
    public void bloquear_DI(){
        for(Interrupcion i:Ainterrupciones)
            i.setInter(false);
    }
    
    public void desbloquear_DI(){
        for(Interrupcion i:Ainterrupciones)
            i.setInter(true);
    }
    public String getrc_DI() {
        return rc;
    }
    public boolean isEntra_DI(){
        boolean ban = false;
        for(Interrupcion i:Ainterrupciones)
            if(i.isInter()){
                ban=true;
            }
            else{
                return false;
            }
        return ban;
    }
    public void setrc_DI(String rc) {
        this.rc = rc;
    }
    public ArrayList<Interrupcion> getAinterrupciones() {
        return Ainterrupciones;
    }
    public void setAinterrupciones(ArrayList<Interrupcion> Ainterrupciones) {
        this.Ainterrupciones = Ainterrupciones;
    }
    
    //Alternancia Estricta
    public int getTurno_AE() {
        return turno1;
    }
    public void setTurno_AE() {
        if(turno1==0){
            turno1=1;
        }
        else{
            turno1=0;
        }
    }
    public int getTurno_AE2() {
        return turno2;
    }
    public void setTurno_AE2() {
        if(turno2==0){
            turno2=1;
        }
        else{
            turno2=0;
        }
    }
    public void setTurno(){
        setTurno_AE();
        con++;
        if(con%2==1){
            setTurno_AE2();
        }
    }
    
    //MUTEX y MUTEXAPI
    public String getrc_M(){
        return rc;
    }
    public void setrc_M(String rc) {
        this.rc = rc;
    }
    
}
