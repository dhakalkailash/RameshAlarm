package com.example.medicinealarm;

public class MedicineHelperClass {

    String nameOfMedicine, medicineQuantity,medicineInDay,numberOfMedicine, timeHour,timeMinute;

    public MedicineHelperClass() {
    }

    public MedicineHelperClass(String  nameOfMedicine, String medicineQuantity, String medicineInDay, String numberOfMedicine, String timeHour, String timeMinute) {
        this.nameOfMedicine   =  nameOfMedicine;
        this.medicineQuantity = medicineQuantity;
        this.medicineInDay    = medicineInDay;
        this.numberOfMedicine = numberOfMedicine;
        this.timeHour         = timeHour;
        this.timeMinute       = timeMinute;

    }

    public String getNameOfMedicine() {
        return nameOfMedicine;
    }

    public void setNameOfMedicine(String nameOfMedicine) {
        this.nameOfMedicine = nameOfMedicine;
    }

    public String getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(String medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public String getMedicineInDay() {
        return medicineInDay;
    }

    public void setMedicineInDay(String medicineInDay) {
        this.medicineInDay = medicineInDay;
    }

    public String getNumberOfMedicine() {
        return numberOfMedicine;
    }

    public void setNumberOfMedicine(String numberOfMedicine) {
        this.numberOfMedicine = numberOfMedicine;
    }

    public String getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(String timeHour) {
        this.timeHour = timeHour;
    }

    public String getTimeMinute() {
        return timeMinute;
    }

    public void setTimeMinute(String timeMinute) {
        this.timeMinute = timeMinute;
    }
}

