package com.mayab.calidad.doubles.tareaUnitTest;

public class Account {
    
    int balance;
    String holder;
    AlertListener alerts;
    int zone;
    Double comission;

    public Account(String holder, int initialBalance, int zone, AlertListener alerts){
        this.holder = holder;
        this.balance = initialBalance;
        this.alerts = alerts;
        setComission(zone);
    }
    
    public Account() {
    	this.holder="Fredy";
    	this.balance=0;
    	this.alerts=null;
    	this.zone=0;
    }
    
    public Account(String holder, int initialBalance,int zone) {
    	this.holder=holder;
    	this.balance=initialBalance;
    	this.zone=zone;
    	setComission(zone);
    }
    
    public void setZone(int zone) {
    	this.zone=zone;
    }
    public int getBalance() {
        return this.balance;
    }
    
    public String getHolder(){
        return this.holder;
    }
    public int getZone() {
    	return this.zone;
    }

    void debit(int money) {
        this.balance -= (money+balance*this.comission);
        if(this.balance < 100){
            this.alerts.sendAlert(this.holder+", your account balance is below 100");
        }
    }

    void credit(int money) {
        this.balance += (money-balance*this.comission);
    }
    
    void setComission(int zone) {
    	if(zone==1) {
    		this.comission=1/100.0;
    	}
    	else if(zone==2) {
    		this.comission=2/100.0;
    	}
    	else if(zone==3) {
    		this.comission=3/100.0;
    	}
    	else {
    		this.comission=0.0;
    	}
    }
    
    void setAlertListener(AlertListener listener){
        this.alerts = listener;
    }
    
}
