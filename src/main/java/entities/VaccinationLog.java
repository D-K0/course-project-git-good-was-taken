package entities;

import constants.BookingConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the VaccinationLog entity that keeps track of all previous vaccinations
 * It contains only a list of VaccinationRecord records, which keep track of the data itself
 * Note that items in the list may be from either drop-in or appointment-based vaccinations
 */
//TODO: finish the java doc for this class.
public class VaccinationLog {
    private final List<VaccinationRecord> log;

    /**
     * [description]
     */
    public VaccinationLog() {
        this.log = new ArrayList<>();
    }

    /**
     * [description]
     *
     * @param a [description]
     */
    // For appointments
    public void addToLog(Appointment a){
        int appointmentId = a.getAppointmentId();
        User client = a.getClient();
        LocalDateTime dateTime = a.getTimePeriod().getDateTime();
        String vaccineBrand = a.getVaccineBrand();

        VaccinationRecord dataObj =
                new VaccinationRecord(BookingConstants.APPOINTMENT_BASED_PREFIX + appointmentId, client, dateTime, vaccineBrand);

        log.add(dataObj);
    }

    /**
     * [description]
     *
     * @param vaccinationId [description]
     * @param client [description]
     * @param dateTime [description]
     * @param vaccineBrand [description]
     */
    // For non-appointments
    public void addToLog(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand){
        VaccinationRecord dataObj =
                new VaccinationRecord(BookingConstants.NON_APPOINTMENT_BASED_PREFIX + vaccinationId, client, dateTime, vaccineBrand);

        log.add(dataObj);
    }

    /**
     * [description]
     *
     * @param id [description]
     * @return [description]
     */
    // Return the VaccinationRecord record by ID. Return null if it cannot be found
    public VaccinationRecord getVaccinationRecord(String id) {
        for(VaccinationRecord record: log) {
            if(id.equals(record.getVaccinationId())) {
                return record;
            }
        }
        return null;
    }

    // Getters

    /**
     * getter.
     *
     * @param id [description]
     * @return [description]
     */
    public User getClientByVaccinationId(String id) {
        try{
            return getVaccinationRecord(id).getClient();
        }catch(Exception ex) {
            return null;
        }
    }

    /**
     * getter.
     *
     * @param id [description]
     * @return [description]
     */
    // Return the dateTime based on the vaccination id
    public LocalDateTime getDateTimeByVaccinationId(String id) {
        try{
            return getVaccinationRecord(id).getDateTime();
        }catch(Exception ex) {
            return null;
        }
    }

    /**
     * getter.
     *
     * @param id [description]
     * @return [description]
     */
    // Return the vaccine brand based on the vaccination id
    public String getVaccineBrandByVaccinationId(String id) {
        try{
            return getVaccinationRecord(id).getVaccineBrand();
        }catch(Exception ex) {
            return null;
        }
    }

    /**
     * getter.
     *
     * @param id [description]
     * @return [description]
     */
    // Return the vaccine toString based on the vaccination id
    public String getRecordString (String id) {
        try{return getVaccinationRecord(id).toString();}
        catch(Exception ex) {return null;}
    }

    /**
     * [description]
     *
     * @param id [description]
     * @return [description]
     */
    public boolean containsId(String id) {
        for(VaccinationRecord record : log) {
            if(record.getVaccinationId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    // VaccinationRecord inner class for storing info for a specific vaccination
    private static class VaccinationRecord {
        //vaccinationId is of the form "A123" for appointment-based vaccinations, or "V123" otherwise
        private final String vaccinationId;
        private final User client;
        private final LocalDateTime dateTime;
        private final String vaccineBrand;

        /**
         * construct a vaccination record object.
         *
         * @param vaccinationId [description]
         * @param client [description]
         * @param dateTime [description]
         * @param vaccineBrand [description]
         */
        public VaccinationRecord(String vaccinationId, User client, LocalDateTime dateTime, String vaccineBrand) {
            this.vaccinationId = vaccinationId;
            this.client = client;
            this.dateTime = dateTime;
            this.vaccineBrand = vaccineBrand;
        }

        /**
         * [description]
         *
         * @return [description]
         */
        public String getVaccinationId() {
            return vaccinationId;
        }

        /**
         * [description]
         *
         * @return [description]
         */
        public User getClient() {
            return client;
        }

        /**
         * [description]
         *
         * @return [description]
         */
        public String getVaccineBrand() {
            return vaccineBrand;
        }

        /**
         * [description]
         *
         * @return [description]
         */
        public LocalDateTime getDateTime() {
            return dateTime;
        }

        /**
         * Return a string of the information of this record.
         *
         * @return a string representation of the object.
         */
        @Override
        public String toString(){
            return "----------------VACCINATION #" + vaccinationId + "----------------" +
                    "\nCLIENT: " + client.getName() +
                    "\nTIME: " + dateTime.toString() +
                    "\nBRAND: " + vaccineBrand;
        }
    }
}
