/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package condicionescompetencias;

public class MutexAPI {
    private boolean pass = false;
    MutexAPI(){}
    public void lock(){
        try{
            wait();
        }catch(Exception e){
            e.printStackTrace();
        ;}
    }
    public void unlock(){
        notify();
    }
    public boolean trylock(){       
        try{
            wait();
        }catch(Exception e){}
        finally{
            unlock();
            return false;
        }
    }
}
