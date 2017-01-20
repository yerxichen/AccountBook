package com.softwise.Util;

/**
 * Created by softwise on 2017/01/20.
 */

public class SingletonPic {
    private static SingletonPic instance=null;

    public synchronized static SingletonPic getInstance(){
        if (instance==null){
            instance=new SingletonPic();
        }
        return instance;
    }
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
