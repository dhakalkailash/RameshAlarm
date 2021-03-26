package com.example.medicinealarm;

public class UserMedicineClass {

    String userName, dName, mName, mQuantity, mFor, mTime;

    public UserMedicineClass() {

    }

    public UserMedicineClass(String userName, String doctorName, String hospitalName, String lastVisit, String wentFor, String visitAgain ) {
        this.userName = userName;
        this.dName = doctorName;
        this.mName = hospitalName;
        this.mQuantity = lastVisit;
        this.mFor = wentFor;
        this.mTime = visitAgain;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getdcotorName() {
        return dName;
    }

    public void setdoctorName(String dName) {
        this.dName = dName;
    }

    public String gethospitalName() {
        return mName;
    }

    public void sethospitalName(String mName) {
        this.mName = mName;
    }

    public String getlastVisit() {
        return mQuantity;
    }

    public void setlastVisit(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String gethospitalFor() {
        return mFor;
    }

    public void sethospitalFor(String mFor) {
        this.mFor = mFor;
    }

    public String getvisitAgain() {
        return mTime;
    }

    public void setvisitAgain(String mTime) {
        this.mTime = mTime;
    }


}
