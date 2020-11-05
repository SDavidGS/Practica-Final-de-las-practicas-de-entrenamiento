
package condicionescompetencias;
import static condicionescompetencias.CondicionesCompetencias.nAlgoritmo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hilo extends Thread {
    private JTextArea area;
    private RCompartido rc;
    private JLabel nAlgoritmo;
    private final static int inicio=995;
    private final static int fin=1000;
    private boolean dead=false;
    private Mutex m;
    private Lock mapi;
    
    
    Hilo(JTextArea area,RCompartido rc, JLabel nAlgoritmo){
        this.area=area;
        this.rc=rc;
        this.nAlgoritmo=nAlgoritmo;
        m = new Mutex();
        mapi = new ReentrantLock();
    }
    //
    public void run(){
        switch(getAlgoritmo()){
            case 0://Condiciones de Competencia
                while(true){
                    try{
                        if(!isdead()){
                            rc.setDatoCompartido(this.getName());
                            area.append(rc.getDatoCompartido() + "\n");
                        }
                        Thread.sleep(500);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            case 1://Desactivacion de Interrupciones
                while(true){
                    try{
                        if(rc.isEntra_DI()){
                            rc.bloquear_DI();
                            if(!isdead()){
                                rc.setrc_DI(this.getName());
                                area.append(rc.getrc_DI()+": Eats\n");
                                rc.desbloquear_DI();
                            }
                        }
                        else{
                            if(!isdead())
                                area.append("En espera...\n");
                        }
                        Thread.sleep((int) (inicio+Math.random()*fin));
                    }catch(Exception e){e.printStackTrace();}
                }
            case 2://Peterson(VC)
                while(true){
                    try{
                        if(rc.isEntra_VC()){//Verifica si el RC esta siendo utilizado
                            rc.bloquear_VC();//Bloquea el RC
                            if(!isdead()){//Verifica si el hilo esta muerto
                                rc.setrc_VC(this.getName());
                                area.append(rc.getrc_VC()+": Eats\n");
                                rc.desbloquear_VC();//Desbloquea el RC antes de su salida
                            }
                        }
                        else{//Si esta siendo utilizado manda un mensaje de espera
                            if(!isdead())
                                area.append("En espera...\n");
                        }
                        Thread.sleep((int) (inicio+Math.random()*fin));
                    }catch(Exception e){e.printStackTrace();}
                }
            case 3://Dekker(AE)
                while(true){
                    try{
                        if(!isdead()){
                            if(Integer.parseInt(this.getName().substring(4))%2 == rc.getTurno_AE()){
                            if((Integer.parseInt(this.getName().substring(4))/2) %2 == rc.getTurno_AE2()){
                                
                                System.out.println(rc.getTurno_AE()+"--"+rc.getTurno_AE2());
                                rc.setDatoCompartido(this.getName());
                                area.append(rc.getDatoCompartido() + "\n");
                                rc.setTurno();
                            }
                            }
                        }
                        else{
                            area.append("En espera...\n");
                        }
                        Thread.sleep((int) (inicio+Math.random()*fin));
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            case 4://Dijkstra
                break;
            case 5://Mutex
                while(true){
                    try{
                        if(!m.getpase()){
                            m.lock();
                            if(!isdead()){
                                rc.setrc_M(this.getName());
                                area.append(rc.getrc_M()+": Eats" + "\n");
                                m.unlock();
                            }
                        }
                        else{
                            if(!isdead())
                                area.append("En espera...");
                        }
                        Thread.sleep((int) (inicio+Math.random()*fin));
                    }catch(Exception e){e.printStackTrace();}
                }
            case 6:
                while(true){
                    try{
                        if(mapi.tryLock()){
                            mapi.lock();
                            if(!isdead()){
                                rc.setrc_M(this.getName());
                                area.append(rc.getrc_M()+": Eats" + "\n");
                                mapi.unlock();
                            }
                        }
                        else{
                            if(!isdead()){
                                area.append("En espera...");
                            }
                        }
                        Thread.sleep((int) (inicio+Math.random()*fin));
                    }catch(Exception e){e.printStackTrace();}
                }       
        }
    }
    
    public boolean isdead(){
        return dead;
    }
    public void setdead(boolean dead){
        this.dead=dead;
    }
    
    public int getAlgoritmo(){
        int num=0;
        if(nAlgoritmo.getText().equals("Condiciones de Competencia")){
            num=0;
        }
        if(nAlgoritmo.getText().equals("Desactivacion de Interrupciones")){
            num=1;
        }
        if(nAlgoritmo.getText().equals("Peterson(VCerradura)")){
            num=2;
        }
        if(nAlgoritmo.getText().equals("Dekker(Alternancia Estricta)")){
            num=3;
        }
        if(nAlgoritmo.getText().equals("Dijkstra")){
            num=4;
        }
        if(nAlgoritmo.getText().equals("Mutex")){
            num=5;
        }
        if(nAlgoritmo.getText().equals("Mutex(API)")){
            num=6;
        }
        return num;
    }
    
}
