package condicionescompetencias;

public class Mutex {
    private boolean pase; //True ocupado, false desocupado
    
    Mutex(){
        this.pase = false;
    }
    
    public synchronized void lock(){
       while(pase){
           try{
               wait();
           }catch(InterruptedException e){
               System.out.println("ERROR" + e.toString());
           }
       }
    }
    
    public synchronized void unlock(){
        this.pase = false;
        notify();
    }
    
    public synchronized boolean getpase(){
        return pase;
    }
}
