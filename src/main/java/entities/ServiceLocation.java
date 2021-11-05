package entities;

import entities.VaccineBatch;
import entities.VaccineSupply;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the ServiceLocation interface which supports methods for
 * Reading and writing vaccine and appointment details
 */
// Add comments
public interface ServiceLocation {

    boolean supplyContainsBatchId(int id);

    void addBatch(VaccineBatch batch);

    void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand);

    void setShift(LocalDate date, int num);

    void addTimePeriod(TimePeriod timePeriod, LocalDate date);

    void removeTimePeriod(LocalDateTime dateTime);

    int getServiceLocationId();

    int getShiftForDate(LocalDate date);

    boolean shiftAvailable(LocalDate date);

    boolean containsShift(LocalDate date);

    boolean checkTimePeriod(LocalDateTime dateTime);



    ArrayList<VaccineBatch> getSupply();

    VaccineSupply getSupplyObj();



    //  checkTimePeriod getShiftForDate
}
